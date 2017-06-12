package com.example.jose.retrofittutorial.service;

import com.example.jose.retrofittutorial.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jose on 06/06/2017.
 */

public interface APIService {
    @GET("getStudent.php")
    Call<List<Student>> getPeopleDetails();
}
