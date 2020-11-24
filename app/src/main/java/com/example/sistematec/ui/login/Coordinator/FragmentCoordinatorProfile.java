package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.DatabaseConection.PersonalDataList;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentDataList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCoordinatorProfile extends Fragment {

    ImageView img;
    TextView txtCoordinatorProfileName, txtCoordinatorProfileEnrollment,txtCoordinatorProfileJob;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_coordinator_profile, container, false);
        img = v.findViewById(R.id.imgCoordinatorProfile);
        txtCoordinatorProfileName = v.findViewById(R.id.txtCoordinatorProfileName);
        txtCoordinatorProfileEnrollment = v.findViewById(R.id.txtCoordinatorProfileID);
        txtCoordinatorProfileJob = v.findViewById(R.id.txtCoordinatorProfileJob);

        getCoordinatorData();

        return v;
    }
    private void setData() {

        //procedimiento de llenado de la información incluyendo la imagen

        txtCoordinatorProfileName.setText("Nombre: " + Data.getCoordAcName());
        txtCoordinatorProfileEnrollment.setText("Matricula: " + Data.getCoordAcId());
        txtCoordinatorProfileJob.setText("Departamento: " + Data.getCoordAcDepName());
    }

    private void getCoordinatorData() {
        Call<List<PersonalDataList>> call = RetrofitClient.getInstance().getApi().getPersonalData("21354612");
        System.out.println("PASANDO");
        call.enqueue(new Callback<List<PersonalDataList>>() {
            @Override
            public void onResponse(Call<List<PersonalDataList>> call, Response<List<PersonalDataList>> response) {
                System.out.println("COMPROBANDO");
                if (response.body() != null) {
                    System.out.println("MODIFICANDO: " + response.body().get(0).getPerMatricula());
                    Data.setCoordAcId(response.body().get(0).getPerMatricula());
                    Data.setCoordAcName(response.body().get(0).getUsuNombre());
                    Data.setCoordAcDepId(response.body().get(0).getPerDepId());
                    Data.setCoordAcDepName(response.body().get(0).getDepNombre());

                    setData();

                } else {
                    Toast.makeText(getActivity(), "Matricula no válida, error.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<PersonalDataList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });
    }

}