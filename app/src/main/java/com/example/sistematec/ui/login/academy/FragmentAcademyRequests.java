package com.example.sistematec.ui.login.academy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.Coordinator.FragmentCoordinatorStudentAwaitingRequest;
import com.example.sistematec.ui.login.DatabaseConection.PersonalRequestsList;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentDataList;
import com.example.sistematec.ui.login.Service.FragmentServiceAwaitingRequests;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAcademyRequests extends Fragment {

    LinearLayout linearLayout;
    FragmentManager manager;
    Button [] buttonArray;
    int[] idArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_requests, container, false);
        setViews(v);
        getStudentRequests();
        return v;
    }

    private void setViews(View v) {
        manager = getFragmentManager();
        linearLayout = v.findViewById(R.id.linearLayoutAR);
    }

    //método en ciclo para la inicializacion y asignación de listener de los botones


    private void setData(final List<PersonalRequestsList> requests) {

        //obtener la cantidad de solicitudes de la base de datos

        buttonArray = new Button[requests.size()];
        idArray = new int[requests.size()];
        for (int i = 0 ; i < requests.size(); i++){
            buttonArray[i] = new Button(getActivity().getApplicationContext());
            buttonArray[i].setId(Integer.parseInt(requests.get(i).getAluMatricula()));
            idArray[i] = Integer.parseInt(requests.get(i).getAluMatricula());
            //obtención de datos de la base de datos
            buttonArray[i].setText(requests.get(i).getAluMatricula() + " - " + requests.get(i).getUsuNombre());
            buttonArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Data.setStudentId(String.valueOf(view.getId()));
                    System.out.println("Click on ID: " + Data.getStudentId());
                    getStudentData(Data.getStudentId());


                }
            });
            //llenado de botones al view
            linearLayout.addView(buttonArray[i]);

        }
    }

    private void getStudentRequests() {
        Call<List<PersonalRequestsList>> call = RetrofitClient.getInstance().getApi()
                .getPersonalRequests(Data.getCoordAcDepId(), "2");
        call.enqueue(new Callback<List<PersonalRequestsList>>() {
            @Override
            public void onResponse(Call<List<PersonalRequestsList>> call, Response<List<PersonalRequestsList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {
                    setData(response.body());

                } else {
                    Toast.makeText(getActivity(), "No hay solicitudes.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PersonalRequestsList>> call, Throwable t) {
                System.out.println("-FALLA SOL - ANÁLISIS- " );
            }
        });
    }

    private void getStudentData(String studentId) {
        Call<List<StudentDataList>> call = RetrofitClient.getInstance().getApi().getStudentData(studentId);
        call.enqueue(new Callback<List<StudentDataList>>() {
            @Override
            public void onResponse(Call<List<StudentDataList>> call, Response<List<StudentDataList>> response) {
                if (response.body() != null) {
                    System.out.println("MODIFICANDO: " + response.body().get(0).getAluMatricula());
                    Data.setStudentId(response.body().get(0).getAluMatricula());
                    Data.setStudentName(response.body().get(0).getUsuNombre());
                    Data.setStudentCareer(response.body().get(0).getCarNombre());
                    Data.setStudentSemester(response.body().get(0).getAluSemestre());
                    Data.setStudentDepId(response.body().get(0).getDepId());

                    openRequestFragment();

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

    private void openRequestFragment() {
        FragmentAcademyAwaitingRequests FAAR = new FragmentAcademyAwaitingRequests();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container_academy, FAAR,"FragmentAcademyAwaitingRequests");
        transaction.addToBackStack("addFragmentAcademyAwaitingRequests");
        transaction.commit();
    }


}
