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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sistematec.R;
import com.example.sistematec.ui.login.Service.FragmentServiceAwaitingRequests;

public class FragmentAcademyRequests extends Fragment {

    LinearLayout linearLayout;
    FragmentManager manager;
    Button [] buttonArray;

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
        linearLayout = v.findViewById(R.id.linearLayoutAR);
        //obtener la cantidad de solicitudes de la base de datos
        int num = 15;
        buttonArray = new Button[num];
        for (int i = 0 ; i < num; i++){
            buttonArray[i] = new Button(getActivity().getApplicationContext());
            buttonArray[i].setId(i);
            //obtención de datos de la base de datos
            buttonArray[i].setText("datos de usuario");
            buttonArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentAcademyAwaitingRequests FSAR = new FragmentAcademyAwaitingRequests();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container_academy,FSAR,"FragmentAcademyAwaitingRequests");
                    transaction.addToBackStack("addFragmentAcademyAwaitingRequests");
                    transaction.commit();
                }
            });
            //llenado de botones al view
            linearLayout.addView(buttonArray[i]);
        }

    }
}
