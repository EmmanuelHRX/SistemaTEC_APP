package com.example.sistematec.ui.login.academy;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import com.example.sistematec.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentAcademyProfile extends Fragment {

    ImageView img;
    TextView txtServiceProfileName, txtServiceProfileEnrollment,txtServiceProfileJob;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_academy_profile, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        img = v.findViewById(R.id.imgAcademyProfile);
        txtServiceProfileName = v.findViewById(R.id.txtAcademyProfileName);
        txtServiceProfileEnrollment = v.findViewById(R.id.txtAcademyProfileID);
        txtServiceProfileJob = v.findViewById(R.id.txtAcademyProfileJob);
        //procedimiento de llenado de la información

        txtServiceProfileName.setText("Nombre: Perez Rodriguez Juan");
        txtServiceProfileEnrollment.setText("Matricula: AE131372");
        txtServiceProfileJob.setText("Departamento: Sistemas Computacionales .");
    }
}
