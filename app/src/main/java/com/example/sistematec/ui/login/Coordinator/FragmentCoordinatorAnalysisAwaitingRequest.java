package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.R;


public class FragmentCoordinatorAnalysisAwaitingRequest extends Fragment implements View.OnClickListener {

    Button btnCAAR_Review, btnCAAR_Send, btnCAAR_Upload;
    TextView txtCAAR_StudentDate,txtCAAR_StudentName,txtCAAR_StudentID;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_analysis_awaiting_request, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        txtCAAR_StudentDate = v.findViewById(R.id.txtCAAR_StudentDate);
        txtCAAR_StudentName = v.findViewById(R.id.txtCAAR_StudentName);
        txtCAAR_StudentID = v.findViewById(R.id.txtCAAR_StudentID);

        //llenado de texto con la bose de dutus
        txtCAAR_StudentDate.setText("");
        txtCAAR_StudentName.setText("");
        txtCAAR_StudentID.setText("");

        btnCAAR_Review = v.findViewById(R.id.btnCAAR_ReviewAnalysis);
        btnCAAR_Upload = v.findViewById(R.id.btnCAAR_Upload);
        btnCAAR_Send = v.findViewById(R.id.btnCAAR_Send);

        btnCAAR_Send.setOnClickListener(this);
        btnCAAR_Review.setOnClickListener(this);
        btnCAAR_Upload.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCAAR_ReviewAnalysis:{

                break;
            }
            case R.id.btnCAAR_Send:{

                break;
            }
            case R.id.btnCAAR_Upload:{

                break;
            }
        }
    }
}