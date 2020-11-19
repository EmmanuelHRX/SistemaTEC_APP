package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sistematec.R;

public class FragmentCoordinatorRequest extends Fragment {

    LinearLayout linearLayout;
    FragmentManager manager;
    Button[] buttonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_request, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        manager = getFragmentManager();
        linearLayout = v.findViewById(R.id.linearLayoutCR);
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
                    FragmentCoordinatorAwaitingRequest FCAR = new FragmentCoordinatorAwaitingRequest();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container_coordinator,FCAR,"FragmentCoordinatorAwaitingRequest");
                    transaction.addToBackStack("addFragmentCoordinatorAwaitingRequest");
                    transaction.commit();
                }
            });
            //llenado de botones al view
            linearLayout.addView(buttonArray[i]);

        }
    }
}
