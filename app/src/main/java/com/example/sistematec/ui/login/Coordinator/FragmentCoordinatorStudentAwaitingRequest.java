package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;

public class FragmentCoordinatorStudentAwaitingRequest extends Fragment implements View.OnClickListener{

    TextView txtCSAR_RequestDate, txtCSAR_StudentName, txtCSAR_StudentID;
    Button btnCSAR_Review, btnCSAR_Review2, btnCSAR_Review3, btnCSAR_Review4, btnCSAR_Confirm, btnCSAR_Deny;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_student_awaiting_request, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        //lo que sea que muestre awaiting requests
        txtCSAR_RequestDate = v.findViewById(R.id.txtCSAR_RequestDate);
        txtCSAR_StudentName = v.findViewById(R.id.txtCSAR_StudentName);
        txtCSAR_StudentID = v.findViewById(R.id.txtCSAR_StudentID);

        //obtención de la información del estudiante mediante la BD
        txtCSAR_RequestDate.setText("Fecha: ---------");
        txtCSAR_StudentID.setText("Matrícula: " + Data.getStudentId());
        txtCSAR_StudentName.setText("Nombre: " + Data.getStudentName());

        btnCSAR_Review = v.findViewById(R.id.btnCSAR_Review);
        btnCSAR_Review2 = v.findViewById(R.id.btnCSAR_Review2);
        btnCSAR_Review3 = v.findViewById(R.id.btnCSAR_Review3);
        btnCSAR_Review4 = v.findViewById(R.id.btnCSAR_Review4);
        btnCSAR_Confirm = v.findViewById(R.id.btnCSAR_Confirm);
        btnCSAR_Deny = v.findViewById(R.id.btnCSAR_Deny);

        btnCSAR_Review.setOnClickListener(this);
        btnCSAR_Review2.setOnClickListener(this);
        btnCSAR_Review3.setOnClickListener(this);
        btnCSAR_Review4.setOnClickListener(this);
        btnCSAR_Confirm.setOnClickListener(this);
        btnCSAR_Deny.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCSAR_Review:{
                Toast.makeText(getContext(),"abriendo solicitud", Toast.LENGTH_SHORT).show();
                //logica para abrir la solicitud
                break;
            }
            case R.id.btnCSAR_Review2:{
                Toast.makeText(getContext(),"abriendo comprobante de no adeudo de libros", Toast.LENGTH_SHORT).show();
                //logica para abrir la comprobante
                break;
            }
            case R.id.btnCSAR_Review3:{
                Toast.makeText(getContext(),"abriendo comprobante de no adeudo de laboratorio", Toast.LENGTH_SHORT).show();
                //logica para abrir el comprobante
                break;
            }
            case R.id.btnCSAR_Review4:{
                Toast.makeText(getContext(),"abriendo kardex", Toast.LENGTH_SHORT).show();
                //logica para abrir el kardex
                break;
            }
            case R.id.btnCSAR_Confirm:{
                Toast.makeText(getContext(),"enviando a análisis", Toast.LENGTH_SHORT).show();
                //lógica cambiar el estado del proceso mediante la BD
                break;
            }
            case R.id.btnCSAR_Deny:{
                Toast.makeText(getContext(),"enviando resultado", Toast.LENGTH_SHORT).show();
                //lógica para rechazar
                break;
            }
        }
    }

}
