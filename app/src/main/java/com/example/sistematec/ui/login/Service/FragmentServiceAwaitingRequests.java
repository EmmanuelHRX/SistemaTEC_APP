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

    TextView txtServiceAwaitingRequestsStudentDateAR, txtServiceAwaitingRequestsStudentNameAR, txtServiceAwaitingRequestsStudentIDAR;
    Button btnSAR_Review, btnSAR_Confirm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_awaiting_requests, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtServiceAwaitingRequestsStudentDateAR = v.findViewById(R.id.txtServiceAwaitingRequestsStudentDateAR);
        txtServiceAwaitingRequestsStudentNameAR = v.findViewById(R.id.txtServiceAwaitingRequestsStudentNameAR);
        txtServiceAwaitingRequestsStudentIDAR = v.findViewById(R.id.txtServiceAwaitingRequestsStudentIDAR);

        btnSAR_Review = v.findViewById(R.id.btnSAR_Review);
        btnSAR_Confirm = v.findViewById(R.id.btnSAR_Confirm);

        btnSAR_Confirm.setOnClickListener(this);
        btnSAR_Review.setOnClickListener(this);
        //procedimiento de llenado de información
        txtServiceAwaitingRequestsStudentDateAR.setText("Fecha: 25 de octubre de 2019");
        txtServiceAwaitingRequestsStudentNameAR.setText("Nombre: Juan Emmanuel López Laguna");
        txtServiceAwaitingRequestsStudentIDAR.setText("Matrícula: 17171403");

    }

    @Override
    public void onClick(View view) {
        //logica de aceptar o rechazar solicitud
        switch (view.getId()){
            case R.id.btnSAR_Review:{
                Toast.makeText(getContext(),"Solicitud Aceptada",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para borrar la solicitud pendiente y actualizar el historial de solicitudes atendidas
                break;
            }
            case R.id.btnSAR_Confirm:{
                Toast.makeText(getContext(),"Solicitud Rechazada",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //lógica para borrar la solicitud pendiente y actualizar el historial de solicitudes atendidas
                break;
            }
        }
    }
}
