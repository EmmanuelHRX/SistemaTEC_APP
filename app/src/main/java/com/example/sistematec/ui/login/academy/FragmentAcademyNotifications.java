package com.example.sistematec.ui.login.academy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentAcademyNotifications extends Fragment {

    TextView txtAcademyNotificationDescription, txtAcademyDictumDescription;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_academy_notifications, container, false);
    }
    private void setData(View v) {
        txtAcademyNotificationDescription = v.findViewById(R.id.txtAcademyNotificationDescription);
        int valueN = 1;
        txtAcademyNotificationDescription.setText("Tienes "+valueN+" solicitudes de Revalidación de Materias pendientes");

        txtAcademyDictumDescription = v.findViewById(R.id.txtAcademyNotificationDescription);
        int valueD = 3;
        txtAcademyDictumDescription.setText("Tienes "+valueD+" solicitudes de dictamenes tecnicos por resolver");
    }

}
