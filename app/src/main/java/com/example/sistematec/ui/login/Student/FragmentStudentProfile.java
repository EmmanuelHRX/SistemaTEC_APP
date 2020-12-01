package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.ImageGetter;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentDataList;
import com.example.sistematec.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStudentProfile extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView txtAlumnoNombre, txtAlumnoMatricula, txtAlumnoCarrera, txtAlumnoSemestre;
    private ImageView img_studentProfile_photo;


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
        if (getArguments() != null) {
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
        img_studentProfile_photo = view.findViewById(R.id.img_studentProfile_photo);

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

        System.out.println("LLAMANDO");

        getStudentData();


    }


    private void getStudentData() {


        Call<List<StudentDataList>> call = RetrofitClient.getInstance().getApi().getStudentData(Data.getStudentId());
        System.out.println("PASANDO");
        call.enqueue(new Callback<List<StudentDataList>>() {
            @Override
            public void onResponse(Call<List<StudentDataList>> call, Response<List<StudentDataList>> response) {
                System.out.println("COMPROBANDO");
                if (response.body() != null) {
                    System.out.println("MODIFICANDO: " + response.body().get(0).getAluMatricula());
                    Data.setStudentId(response.body().get(0).getAluMatricula());
                    Data.setStudentName(response.body().get(0).getUsuNombre());
                    Data.setStudentCareer(response.body().get(0).getCarNombre());
                    Data.setStudentSemester(response.body().get(0).getAluSemestre());
                    Data.setStudentDepId(response.body().get(0).getDepId());
                    Data.setStudentPhotoURL(response.body().get(0).getUsuFotoUrl());

                    txtAlumnoNombre.setText("Nombre: " + Data.getStudentName());
                    txtAlumnoMatricula.setText("Matrícula: " + Data.getStudentId());
                    txtAlumnoCarrera.setText("Carrera: " + Data.getStudentCareer());
                    txtAlumnoSemestre.setText("Semestre: " + Data.getStudentSemester());
                    ImageGetter.getInstance().getAndSetImageFromUrl(img_studentProfile_photo, Data.getStudentPhotoURL());

                } else {
                    Toast.makeText(getActivity(), "Matricula no válida, error.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<StudentDataList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });
    }


}
