package com.example.sistematec.ui.login.academy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sistematec.R;

public class FragmentAcademyRequests extends Fragment implements View.OnClickListener {
    Button btn;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_requests, container, false);
        setData(v);
        return v;
    }

    //método en ciclo para la inicializacion y asignación de listener de los botones
    private void setData(View v){
        manager = getFragmentManager();
        btn = v.findViewById(R.id.btnRequestAcademy);
        btn.setText("17171372 Juan Juancho Perez Pereira");
        btn.setOnClickListener(this);
    }

    public void onClick (View v){

        switch (v.getId()){
            case R.id.btnRequestAcademy:{
                // código que abra el fragmento awaiting request
                FragmentAcademyAwaitingRequests FAAR = new FragmentAcademyAwaitingRequests();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentAcademyProfile,FAAR,"FragmentAcademyAwaitingRequests");
                transaction.addToBackStack("addFragmentAcademyAwaitingRequests");
                transaction.commit();
                break;
            }

        }
    }
}
