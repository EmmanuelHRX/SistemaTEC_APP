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

    TextView txtAcademyAwaitingRequestsStudentDateAR, txtAcademyAwaitingRequestsStudentNameAR, txtAcademyAwaitingRequestsStudentIDAR, txtAcademyAwaitingRequestsStudentOldSyllabusAR
            , txtAcademyAwaitingRequestsStudentNewSyllabusAR;
    Button btnAcademyAwaitingRequestsValidateAR, btnAcademyAwaitingRequestsDenyAR;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_awaiting_requests, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtAcademyAwaitingRequestsStudentDateAR = v.findViewById(R.id.txtAcademyAwaitingRequestsStudentDateAR);
        txtAcademyAwaitingRequestsStudentNameAR = v.findViewById(R.id.txtAcademyAwaitingRequestsStudentNameAR);
        txtAcademyAwaitingRequestsStudentIDAR = v.findViewById(R.id.txtAcademyAwaitingRequestsStudentIDAR);
        txtAcademyAwaitingRequestsStudentOldSyllabusAR = v.findViewById(R.id.txtAcademyAwaitingRequestsStudentOldSyllabusAR);
        txtAcademyAwaitingRequestsStudentNewSyllabusAR = v.findViewById(R.id.txtAcademyAwaitingRequestsStudentNewSyllabusAR);

        btnAcademyAwaitingRequestsValidateAR = v.findViewById(R.id.btnAAR_Upload);
        btnAcademyAwaitingRequestsDenyAR = v.findViewById(R.id.btnAAR_Upload);

        btnAcademyAwaitingRequestsValidateAR.setOnClickListener(this);
        btnAcademyAwaitingRequestsDenyAR.setOnClickListener(this);
        //procedimiento de llenado de información
        txtAcademyAwaitingRequestsStudentDateAR.setText("Fecha: 25 de octubre de 2019");
        txtAcademyAwaitingRequestsStudentNameAR.setText("Nombre: Juan Emmanuel López Laguna");
        txtAcademyAwaitingRequestsStudentIDAR.setText("Matrícula: 17171403");
        txtAcademyAwaitingRequestsStudentOldSyllabusAR.setText("Plan de Estudios Origen: ISIC-2010-224");
        txtAcademyAwaitingRequestsStudentNewSyllabusAR.setText("Plan de Estudios Destino: IIND-2010-227");

    }

    @Override
    public void onClick(View view) {
        //logica de aceptar o rechazar solicitud
        final int a = R.id.btnAAR_Upload;
        final int b = R.id.btnAAR_Upload;
        switch (view.getId()){
            case 1:{
                Toast.makeText(getContext(),"Solicitud Aceptada",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para borrar la solicitud pendiente y actualizar el historial de solicitudes atendidas
                break;
            }
            case b:{
                Toast.makeText(getContext(),"Solicitud Rechazada",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para borrar la solicitud pendiente y actualizar el historial de solicitudes atendidas
                break;
            }
        }
    }

}
