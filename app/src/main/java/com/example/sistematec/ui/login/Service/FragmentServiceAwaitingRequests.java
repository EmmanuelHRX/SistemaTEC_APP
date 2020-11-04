package com.example.sistematec.ui.login.Service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentServiceAwaitingRequests extends Fragment {

    TextView txtStudentDateAR, txtStudentNameAR, txtStudentIDAR, txtStudentOldSyllabusAR, txtStudentNewSyllabusAR;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_awaiting_requests, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtStudentDateAR = v.findViewById(R.id.txtStudentDateAR);
        txtStudentNameAR = v.findViewById(R.id.txtStudentNameAR);
        txtStudentIDAR = v.findViewById(R.id.txtStudentIDAR);
        txtStudentOldSyllabusAR = v.findViewById(R.id.txtStudentOldSyllabusAR);
        txtStudentNewSyllabusAR = v.findViewById(R.id.txtStudentNewSyllabusAR);
        //procedimiento de llenado de información
        txtStudentDateAR.setText("Fecha: 25 de octubre de 2019");
        txtStudentNameAR.setText("Nombre: Juan Emmanuel López Laguna");
        txtStudentIDAR.setText("Matrícula: 17171403");
        txtStudentOldSyllabusAR.setText("Plan de Estudios Origen: ISIC-2010-224");
        txtStudentNewSyllabusAR.setText("Plan de Estudios Destino: IIND-2010-227");

    }

}
