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

public class FragmentServiceRequestsHistoryDeny extends Fragment implements View.OnClickListener {

    Button btnSRHD;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_service_requests_history_deny, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        //lógica para hacerlo dinámico y con la BD
        btnSRHD = v.findViewById(R.id.btnSRHD1);
        btnSRHD.setText("17171403 López Laguna Juan Emmanuel");
        btnSRHD.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSRHD1:{
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container_service,new FragmentServiceRequestsHistoryExtend(),"FragmentServiceRequestsHistoryExtend");
                transaction.addToBackStack("addFragmentServiceRequestsHistoryExtend");
                transaction.commit();
                break;
            }
        }
    }
}
