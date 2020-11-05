package com.example.sistematec.ui.login.academy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.Student.FragmentStudentProfile;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentAcademyProfile extends Fragment {

    private TextView txtAlumnoNombre, txtAlumnoMatricula, txtAlumnoCarrera, txtAlumnoSemestre;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentStudentProfile.OnFragmentInteractionListener mListener;

    public FragmentAcademyProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentStudentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentStudentProfile newInstance(String param1, String param2) {
        FragmentStudentProfile fragment = new FragmentStudentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

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
        if (context instanceof FragmentStudentProfile.OnFragmentInteractionListener) {
            mListener = (FragmentStudentProfile.OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void setData() {

        //TIPO_USUARIO MÉTODO DE LLENADO

        this.txtAlumnoNombre.setText("Nombre: Román Alejandro Gaspar Atondo");
        this.txtAlumnoMatricula.setText("Matrícula: 17171372");
        this.txtAlumnoCarrera.setText("Carrera: Ing. Sistemas Computacionales");
        this.txtAlumnoSemestre.setText("Semestre: 12vo :'c");
    }
}
