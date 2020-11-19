package com.example.sistematec.ui.login.academy;

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

public class FragmentAcademyAwaitingRequests extends Fragment implements View.OnClickListener {

    TextView txtAAR_StudentDate, txtAAR_StudentName, txtAAR_StudentID;
    Button btnAAR_Upload, btnAAR_Review, btnAAR_Review2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_awaiting_requests, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtAAR_StudentDate = v.findViewById(R.id.txtAAR_StudentDate);
        txtAAR_StudentName = v.findViewById(R.id.txtAAR_StudentName);
        txtAAR_StudentID = v.findViewById(R.id.txtAAR_StudentID);

        btnAAR_Upload = v.findViewById(R.id.btnAAR_Upload);
        btnAAR_Review = v.findViewById(R.id.btnAAR_Review);
        btnAAR_Review2 = v.findViewById(R.id.btnAAR_Review2);

        btnAAR_Upload.setOnClickListener(this);
        btnAAR_Review.setOnClickListener(this);
        btnAAR_Review2.setOnClickListener(this);

        //procedimiento de llenado de información con la BD
        txtAAR_StudentDate.setText("Fecha: 25 de octubre de 2019");
        txtAAR_StudentName.setText("Nombre: Juan Emmanuel López Laguna");
        txtAAR_StudentID.setText("Matrícula: 17171403");


    }

    @Override
    public void onClick(View view) {
        //logica de aceptar o rechazar solicitud

        switch (view.getId()){
            case R.id.btnAAR_Review:{
                Toast.makeText(getContext(),"cargando review 1",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //logica para abrir el pdf
                break;
            }
            case R.id.btnAAR_Review2:{
                Toast.makeText(getContext(),"cargando review 2",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para abrir el pdf
                break;
            }
            case R.id.btnAAR_Upload:{
                Toast.makeText(getContext(),"subiendolo ",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para hacer el envio
                break;
            }
        }
    }

}
