package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentCoordinatorProfile extends Fragment {

    ImageView img;
    TextView txtCoordinatorProfileName, txtCoordinatorProfileEnrollment,txtCoordinatorProfileJob;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_coordinator_profile, container, false);
        setData(v);
        return v;
    }
    private void setData(View v) {
        img = v.findViewById(R.id.imgCoordinatorProfile);
        txtCoordinatorProfileName = v.findViewById(R.id.txtCoordinatorProfileName);
        txtCoordinatorProfileEnrollment = v.findViewById(R.id.txtCoordinatorProfileID);
        txtCoordinatorProfileJob = v.findViewById(R.id.txtCoordinatorProfileJob);
        //procedimiento de llenado de la información incluyendo la imagen

        txtCoordinatorProfileName.setText("Nombre: Marco Arturo Uribe López");
        txtCoordinatorProfileEnrollment.setText("Matricula: 17171503");
        txtCoordinatorProfileJob.setText("Puesto: Coordinador.");
    }
}