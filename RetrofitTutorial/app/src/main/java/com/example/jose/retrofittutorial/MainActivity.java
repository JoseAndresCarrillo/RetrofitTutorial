package com.example.jose.retrofittutorial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jose.retrofittutorial.model.Student;
import com.example.jose.retrofittutorial.service.APIService;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    TextView textDetails;
    Button btnGetData,btnInsertData;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDetails=(TextView) findViewById(R.id.textDetails);
        btnGetData=(Button) findViewById(R.id.btnGetData);
        btnInsertData=(Button) findViewById(R.id.btnInsert);
        editName= (EditText) findViewById(R.id.editName);
        pDialog= new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnGetData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getPeopleDetails();
            }
        });
    }

    private void showpDialog(){
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    private void getPeopleDetails(){
        showpDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jace796.esy.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service=retrofit.create(APIService.class);
        Call<List<Student>> call=service.getPeopleDetails();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students= response.body();
                String details="";
                for(int i=0;i<students.size();i++){
                    String name= students.get(i).getName();
                    details +="\n\n name: "+name;
                }
                textDetails.setText(details);
                hidepDialog();
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                hidepDialog();
            }
        });




    }

}
