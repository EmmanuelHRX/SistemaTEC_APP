package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentStudentProfile extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView txtAlumnoNombre, txtAlumnoMatricula, txtAlumnoCarrera, txtAlumnoSemestre;

    public FragmentStudentProfile() {
        // Required empty public constructor
    }

    public static FragmentStudentProfile newInstance() {
        FragmentStudentProfile fragment = new FragmentStudentProfile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        txtAlumnoNombre = view.findViewById(R.id.txtAlumnoNombre);
        txtAlumnoMatricula = view.findViewById(R.id.txtAlumnoMatricula);
        txtAlumnoCarrera = view.findViewById(R.id.txtAlumnoCarrera);
        txtAlumnoSemestre = view.findViewById(R.id.txtAlumnoSemestre);

        setData();

        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setData() {

        //TIPO_USUARIO MÉTODO DE LLENADO

        this.txtAlumnoNombre.setText("Nombre: Juan Emmanuel López Laguna");
        this.txtAlumnoMatricula.setText("Matrícula: 17171372");
        this.txtAlumnoCarrera.setText("Carrera: Ing. Sistemas Computacionales");
        this.txtAlumnoSemestre.setText("Semestre: 12vo :'c");
    }



}
