package com.example.sistematec.ui.login.DatabaseConection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://sistematecapp.000webhostapp.com/sistematec/";
    private static RetrofitClient myClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (myClient == null) {
            myClient = new RetrofitClient();
        }

        return myClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

}
