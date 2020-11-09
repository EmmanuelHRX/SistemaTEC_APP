package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.R;

public class FragmentStudentRequestsCapture extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String ARG_ID = "id";

    private OnFragmentInteractionListener mListener;

    FragmentManager manager;
    private Button btnStudentRequestsConfirm;

    private String id;

    TextView txt_studentReqCapture_name;
    TextView txt_studentReqCapture_id;
    TextView txt_studentReqCapture_career;
    TextView txt_studentReqCapture_semester;

    Spinner spnr_studentReqCapture_subjects;

    public FragmentStudentRequestsCapture() {
        // Required empty public constructor
    }

    public static FragmentStudentRequestsCapture newInstance(String id) {
        FragmentStudentRequestsCapture fragment = new FragmentStudentRequestsCapture();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_requests_capture, container, false);

        manager = getFragmentManager();

        txt_studentReqCapture_name = view.findViewById(R.id.txt_studentReqCapture_name);
        txt_studentReqCapture_id = view.findViewById(R.id.txt_studentReqCapture_id);;
        txt_studentReqCapture_career = view.findViewById(R.id.txt_studentReqCapture_career);;
        txt_studentReqCapture_semester = view.findViewById(R.id.txt_studentReqCapture_semester);;

        btnStudentRequestsConfirm = view.findViewById(R.id.btn_student_requests_confirm);
        btnStudentRequestsConfirm.setOnClickListener(this);

        spnr_studentReqCapture_subjects = view.findViewById(R.id.spnr_studentReqCapture_subjects);
        spnr_studentReqCapture_subjects.setOnItemSelectedListener(this);

        setSpinnerData();
        setData();

        return view;
    }

    private void setSpinnerData() {
        //DB request

        int subjectCount = 5;

        String[] subjects = new String[subjectCount];
        subjects = new String[] {"Ing. en Sistemas Computacionales", "Ing. en TICS", "Ing. en Mecatrónica", "Ing. Industrial",
                                    "Ing. en Gestión Empresarial"};

        for (int i = 0; i < 0; i++) {
            //For para llenar el arreglo...
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, subjects);
        spnr_studentReqCapture_subjects.setAdapter(adapter);
    }

    private void setData() {
        //DB request

        this.txt_studentReqCapture_name.setText("Nombre: " + getActivity().getIntent().getStringExtra("name"));
        this.txt_studentReqCapture_id.setText("Matrícula: " + getActivity().getIntent().getStringExtra("id"));
        this.txt_studentReqCapture_career.setText("Carrera: " + getActivity().getIntent().getStringExtra("career"));
        this.txt_studentReqCapture_semester.setText("Semestre: " + getActivity().getIntent().getStringExtra("semester"));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_student_requests_confirm) {
            FragmentStudentRequestsConfirmation frgStudentRCon = FragmentStudentRequestsConfirmation
                    .newInstance(spnr_studentReqCapture_subjects.getSelectedItem().toString());
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container_student, frgStudentRCon, "StudentRCon");
            transaction.addToBackStack("addStudentRCon");
            transaction.commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Toasting " + spnr_studentReqCapture_subjects.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
