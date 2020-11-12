package com.example.sistematec.ui.login.Service;

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

public class FragmentServiceRequests extends Fragment implements View.OnClickListener {

    //creacion de array de botones
    Button btn;
    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_requests, container, false);
        setData(v);
        return v;
    }

    //método en ciclo para la inicializacion y asignación de listener de los botones
    private void setData(View v){
        manager = getFragmentManager();
        btn = v.findViewById(R.id.btnRequestService);
        btn.setText("17171372 Román Alejandro Gaspar Atondo");
        btn.setOnClickListener(this);
    }

    public void onClick (View v){

        switch (v.getId()){
            case R.id.btnRequestService:{
                // código que abra el fragmento awaiting request
                FragmentServiceAwaitingRequests FSAR = new FragmentServiceAwaitingRequests();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,FSAR,"FragmentServiceAwaitingRequests");
                transaction.addToBackStack("addFragmentServiceAwaitingRequests");
                transaction.commit();
                break;
            }

        }
    }

}
