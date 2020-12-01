package com.example.sistematec.ui.login.academy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.example.sistematec.Data;
import com.example.sistematec.ImageGetter;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.DatabaseConection.PersonalDataList;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAcademyProfile extends Fragment {

    ImageView img;
    TextView txtAcademyName, txtAcademyId, txtAcademyDepName;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_academy_profile, container, false);
        setViews(v);
        getAcademyData();
        return v;
    }

    private void setViews(View v) {
        img = v.findViewById(R.id.img_academyProfile_photo);
        txtAcademyName = v.findViewById(R.id.txtAcademyProfileName);
        txtAcademyId = v.findViewById(R.id.txtAcademyProfileID);
        txtAcademyDepName = v.findViewById(R.id.txtAcademyProfileJob);
        //procedimiento de llenado de la información incluyendo la imagen

    }

    private void setData() {
        txtAcademyName.setText("Nombre: " + Data.getCoordAcName());
        txtAcademyId.setText("Matricula: " + Data.getCoordAcId());
        txtAcademyDepName.setText("Departamento: " + Data.getCoordAcDepName());
        ImageGetter.getInstance().getAndSetImageFromUrl(img, Data.getCoorAcPhotoURL());
    }

    private void getAcademyData() {
        Call<List<PersonalDataList>> call = RetrofitClient.getInstance().getApi().getPersonalData(Data.getCoordAcId());
        System.out.println("PASANDO");
        call.enqueue(new Callback<List<PersonalDataList>>() {
            @Override
            public void onResponse(Call<List<PersonalDataList>> call, Response<List<PersonalDataList>> response) {
                System.out.println("COMPROBANDO");
                if (response.body() != null) {
                    System.out.println("MODIFICANDO: " + response.body().get(0).getPerMatricula());
                    Data.setCoordAcId(response.body().get(0).getPerMatricula());
                    Data.setCoordAcName(response.body().get(0).getUsuNombre());
                    Data.setCoordAcDepId(response.body().get(0).getPerDepId());
                    Data.setCoordAcDepName(response.body().get(0).getDepNombre());
                    Data.setCoorAcPhotoURL(response.body().get(0).getUsuFotoUrl());

                    setData();

                } else {
                    Toast.makeText(getActivity(), "Matricula no válida, error.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<PersonalDataList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });
    }
}
