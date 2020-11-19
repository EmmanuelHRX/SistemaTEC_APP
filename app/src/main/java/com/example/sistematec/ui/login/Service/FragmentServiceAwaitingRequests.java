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
import android.widget.Toast;

import com.example.sistematec.R;

public class FragmentServiceAwaitingRequests extends Fragment implements View.OnClickListener{

    TextView txtSAR_StudentDate, txtSAR_StudentName, txtSAR_StudentID;
    Button btnSAR_Review, btnSAR_Confirm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_awaiting_requests, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtSAR_StudentDate = v.findViewById(R.id.txtSAR_StudentDate);
        txtSAR_StudentName = v.findViewById(R.id.txtSAR_StudentName);
        txtSAR_StudentID = v.findViewById(R.id.txtSAR_StudentID);

        btnSAR_Review = v.findViewById(R.id.btnSAR_Review);
        btnSAR_Confirm = v.findViewById(R.id.btnSAR_Confirm);

        btnSAR_Confirm.setOnClickListener(this);
        btnSAR_Review.setOnClickListener(this);
        //procedimiento de llenado de información
        txtSAR_StudentDate.setText("Fecha: 25 de octubre de 2019");
        txtSAR_StudentName.setText("Nombre: Juan Emmanuel López Laguna");
        txtSAR_StudentID.setText("Matrícula: 17171403");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSAR_Review:{
                Toast.makeText(getContext(),"cargando pdf para revision",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para abrir el pdf
                break;
            }
            case R.id.btnSAR_Confirm:{
                Toast.makeText(getContext(),"enviando solicitud",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para enviar el dictamen a service
                break;
            }
        }
    }
}
