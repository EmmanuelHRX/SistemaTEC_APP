package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
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
import com.example.sistematec.ui.login.DatabaseConection.PersonalRequestsList;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentDataList;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCoordinatorStudentRequest extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout;
    FragmentManager manager;
    Button[] buttonArray;
    int[] idArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_student_request, container, false);

        setViews(v);
        getStudentRequests();

        return v;
    }

    private void setViews(View v) {
        manager = getFragmentManager();
        linearLayout = v.findViewById(R.id.linearLayoutCR);
    }

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

                    getStudentData(Data.getStudentId());


                }
            });
            //llenado de botones al view
            linearLayout.addView(buttonArray[i]);

        }
    }

    @Override
    public void onClick(View v) {

    }

    private void getStudentRequests() {
        Call<List<PersonalRequestsList>> call = RetrofitClient.getInstance().getApi()
            .getPersonalRequests(Data.getCoordAcDepId(), "0");
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
                System.out.println("FALLA - " );
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
        FragmentCoordinatorStudentAwaitingRequest FCAR = new FragmentCoordinatorStudentAwaitingRequest();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container_coordinator,FCAR,"FragmentCoordinatorStudentAwaitingRequest");
        transaction.addToBackStack("addFragmentCoordinatorAwaitingRequest");
        transaction.commit();
    }



}
