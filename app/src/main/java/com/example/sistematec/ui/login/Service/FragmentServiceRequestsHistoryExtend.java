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

public class FragmentServiceRequestsHistoryExtend extends Fragment {

    TextView txtSRHE_StudentDate, txtSRHE_StudentName, txtSRHE_StudentID, txtSRHE_StudentOldSyllabus
            , txtSRHE_StudentNewSyllabus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_requests_history_extend, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtSRHE_StudentDate = v.findViewById(R.id.txtSHRE_StudentDate);
        txtSRHE_StudentName = v.findViewById(R.id.txtSHRE_StudentName);
        txtSRHE_StudentID = v.findViewById(R.id.txtSHRE_StudentID);
        txtSRHE_StudentOldSyllabus = v.findViewById(R.id.txtSHRE_StudentOldSyllabus);
        txtSRHE_StudentNewSyllabus = v.findViewById(R.id.txtSHRE_StudentNewSyllabus);

        txtSRHE_StudentDate.setText("Fecha: 25 de octubre de 2019");
        txtSRHE_StudentName.setText("Nombre: Juan Emmanuel López Laguna");
        txtSRHE_StudentID.setText("Matrícula: 17171403");
        txtSRHE_StudentOldSyllabus.setText("Plan de Estudios Origen: ISIC-2010-224");
        txtSRHE_StudentNewSyllabus.setText("Plan de Estudios Destino: IIND-2010-227");

    }
}
