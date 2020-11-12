package com.example.sistematec.ui.login.Service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentServiceProfile extends Fragment {

    ImageView img;
    TextView txtServiceProfileName, txtServiceProfileEnrollment,txtServiceProfileJob;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_service_profile, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        img = v.findViewById(R.id.imgServiceProfile);
        txtServiceProfileName = v.findViewById(R.id.txtServiceProfileName);
        txtServiceProfileEnrollment = v.findViewById(R.id.txtServiceProfileID);
        txtServiceProfileJob = v.findViewById(R.id.txtServiceProfileJob);
        //procedimiento de llenado de la información

        txtServiceProfileName.setText("Nombre: Román Alejandro Gaspar Atondo");
        txtServiceProfileEnrollment.setText("Matricula: 17171372");
        txtServiceProfileJob.setText("Puesto: Jefe de Depto. de Servicios Escolares.");
    }
}
