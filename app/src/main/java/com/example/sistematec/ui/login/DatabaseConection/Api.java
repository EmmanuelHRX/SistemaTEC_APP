package com.example.sistematec.ui.login.DatabaseConection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @FormUrlEncoded
    @POST("uploadindividualpdf.php")
    Call<ResponsePOJO> uploadPDF(
            @Field("id") String id,
            @Field("name") String name,
            @Field("PDF") String encodedPDF);


    @GET("DB_GetStudentData.php")
    Call<List<StudentDataList>> getStudentData(
            @Query("matricula") String matricula);


}