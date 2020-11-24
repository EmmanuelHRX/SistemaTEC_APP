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

    @FormUrlEncoded
    @POST("DB_UploadRequestDocuments.php")
    Call<ResponsePOJO> uploadRequestDocuments(
            @Field("matricula") String matricula,
            @Field("departamento") String departamento,
            @Field("encodedSol") String encodedSol,
            @Field("encodedSolName") String encodedSolName,
            @Field("encodedKardex") String encodedKardex,
            @Field("encodedKardexName") String encodedKardexName);

    @FormUrlEncoded
    @POST("DB_UploadRequestDocuments2.php")
    Call<ResponsePOJO> uploadRequestDocuments2(
            @Field("matricula") String matricula,
            @Field("encodedLab") String encodedLab,
            @Field("encodedLabName") String encodedLabName,
            @Field("encodedLib") String encodedLib,
            @Field("encodedLibName") String encodedLibName);

    @GET("DB_GetStudentRequest.php")
    Call<List<StudentRequestList>> getStudentRequest(
            @Query("matricula") String matricula);

    @GET("DB_GetPersonalData.php")
    Call<List<PersonalDataList>> getPersonalData(
            @Query("matricula") String matricula);

    @GET("DB_GetPersonalRequests.php")
    Call<List<PersonalRequestsList>> getPersonalRequests(
            @Query("departamentoId") String departamentoId,
            @Query("etapaId") String etapaId);


}