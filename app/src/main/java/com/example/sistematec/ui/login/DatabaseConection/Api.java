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

    @GET("DB_GetStudentRequestDocuments.php")
    Call<List<StudentRequestDocumentsList>> getStudentRequestDocuments(
            @Query("matricula") String matricula);

    @FormUrlEncoded
    @POST("DB_SetRequestPhase.php")
    Call<ResponsePOJO> setRequestPhase(
            @Field("matricula") String matricula,
            @Field("etapaId") String etapaId);

    @FormUrlEncoded
    @POST("DB_InsertNotification.php")
    Call<ResponsePOJO> insertNotification(
            @Field("solId") String solId,
            @Field("menId") String menId);

    @FormUrlEncoded
    @POST("DB_UploadAnalysis.php")
    Call<ResponsePOJO> uploadAnalysis(
            @Field("matricula") String matricula,
            @Field("encodedAn") String encodedAn,
            @Field("encodedAnName") String encodedAnName);

    @GET("DB_GetAnalysisDocument.php")
    Call<List<AnalysisDocumentList>> getAnalysisDocument(
            @Query("matricula") String matricula);

    @FormUrlEncoded
    @POST("DB_UploadDictum.php")
    Call<ResponsePOJO> uploadDictum(
            @Field("matricula") String matricula,
            @Field("encodedDic") String encodedDic,
            @Field("encodedDicName") String encodedDicName);

    @FormUrlEncoded
    @POST("DB_UploadConf.php")
    Call<ResponsePOJO> uploadCon(
            @Field("matricula") String matricula,
            @Field("encodedCon") String encodedCon,
            @Field("encodedConName") String encodedConName);

    @GET("DB_GetDictumDocument.php")
    Call<List<DictumDocumentList>> getDicDocument(
            @Query("matricula") String matricula);

    @GET("DB_GetConfDocument.php")
    Call<List<ConDocumentList>> getConDocument(
            @Query("matricula") String matricula);

    @GET("DB_GetStudentNotifications.php")
    Call<List<StudentNotificationsList>> getStudentNotifications(
            @Query("matricula") String matricula);

    @GET("DB_GetCoordNotifications.php")
    Call<List<CoordNotificationsList>> getCoordNotifications(
            @Query("departamentoId") String departamentoId);

    @GET("DB_GetAcademyNotifications.php")
    Call<List<AcademyNotificationsList>> getAcademyNotifications(
            @Query("departamentoId") String departamentoId);

    @GET("DB_CheckStudentCredentials.php")
    Call<List<CheckedCredentialsList>> getCheckedCredentials(
            @Query("matricula") String matricula,
            @Query("contrasenia") String contrasenia);

    @GET("DB_CheckPersonalCredentials.php")
    Call<List<CheckedCredentialsList>> getPersonalCheckedCredentials(
            @Query("matricula") String matricula,
            @Query("contrasenia") String contrasenia);

    @FormUrlEncoded
    @POST("DB_SetCheckedStudentNotifications.php")
    Call<ResponsePOJO> setCheckedStudentNotifications(
            @Field("matricula") String matricula);

    @FormUrlEncoded
    @POST("DB_SetCheckedCoordNotifications.php")
    Call<ResponsePOJO> setCheckedCoordNotifications(
            @Field("departamentoId") String departamentoId);

    @FormUrlEncoded
    @POST("DB_SetCheckedAcademyNotifications.php")
    Call<ResponsePOJO> setCheckedAcademyNotifications(
            @Field("departamentoId") String departamentoId);

}