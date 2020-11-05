package com.example.sistematec.ui.login.academy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sistematec.R;
import com.example.sistematec.ui.login.Service.FragmentServiceAwaitingRequests;

public class FragmentAcademyRequests extends Fragment implements View.OnClickListener {


    //creacion de array de botones
    Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_requests, container, false);
        setData(v);
        return v;
    }

    //método en ciclo para la inicializacion y asignación de listener de los botones
    private void setData(View v){
        btn = v.findViewById(R.id.btnRequestAcademy);
        btn.setText("17171372 Román Alejandro Gaspar Atondo");
        btn.setOnClickListener(this);
    }

    public void onClick (View v){
        Toast.makeText(getContext(),"se presionó el botón",Toast.LENGTH_LONG).show();
        switch (v.getId()){
            case R.id.btnRequestAcademy:{
                // código que abra el fragmento awaiting request
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentServiceAwaitingRequests()).commit();
                break;
            }

        }
    }
}
