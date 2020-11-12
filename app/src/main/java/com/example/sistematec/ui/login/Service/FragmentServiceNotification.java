package com.example.sistematec.ui.login.Service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentServiceNotification extends Fragment {

    TextView txtServiceNotificationDescription;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_notification, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtServiceNotificationDescription = v.findViewById(R.id.txtServiceNotificationDescription);
        int value = 1;
        txtServiceNotificationDescription.setText("Tienes "+value+" solicitudes de Revalidación de Materias pendientes");
    }
}
