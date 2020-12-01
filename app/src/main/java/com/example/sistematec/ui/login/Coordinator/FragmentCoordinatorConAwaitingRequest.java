package com.example.sistematec.ui.login.Coordinator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.UploadAndDownload;
import com.example.sistematec.ui.login.DatabaseConection.AnalysisDocumentList;
import com.example.sistematec.ui.login.DatabaseConection.ConDocumentList;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCoordinatorConAwaitingRequest extends Fragment implements View.OnClickListener {

    Button btnCCAR_Review, btnCCAR_confirm;
    TextView txtCCAR_StudentDate, txtCCAR_StudentName, txtCCAR_StudentID, txtCCAR_ReviewCon;

    String conURL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_con_awaiting_request, container, false);
        setViews(v);
        setData();
        setStudentData();
        getDocumentData();
        return v;
    }

    private void setViews(View v) {
        txtCCAR_StudentDate = v.findViewById(R.id.txtCCAR_StudentDate);
        txtCCAR_StudentName = v.findViewById(R.id.txtCCAR_StudentName);
        txtCCAR_StudentID = v.findViewById(R.id.txtCCAR_StudentID);
        txtCCAR_ReviewCon = v.findViewById(R.id.txtCCAR_ReviewCon);

        btnCCAR_Review = v.findViewById(R.id.btnCCAR_ReviewCon);
        btnCCAR_confirm = v.findViewById(R.id.btnCCAR_confirm);

        btnCCAR_confirm.setOnClickListener(this);
        btnCCAR_Review.setOnClickListener(this);

    }

    private void setData() {
    }

    private void setStudentData() {
        txtCCAR_StudentDate.setText("Fecha: ---");
        txtCCAR_StudentName.setText("Nombre: " + Data.getStudentName());
        txtCCAR_StudentID.setText("Matricula: " + Data.getStudentId());
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCCAR_ReviewCon:{
                if (conURL != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(conURL));
                    startActivity(browserIntent);
                }
                break;
            }

            case R.id.btnCCAR_confirm:{
                changeRequestStatus("7", "8");
                break;
            }
        }
    }

    private void getDocumentData() {

        Call<List<ConDocumentList>> call = RetrofitClient.getInstance().getApi()
                .getConDocument(Data.getStudentId());
        call.enqueue(new Callback<List<ConDocumentList>>() {
            @Override
            public void onResponse(Call<List<ConDocumentList>> call, Response<List<ConDocumentList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());

                    txtCCAR_ReviewCon.setText(UploadAndDownload.getFileName(response.body().get(0).getSolConformidadDocUrl()));

                    conURL = response.body().get(0).getSolConformidadDocUrl();
                    System.out.println("CONURL: " + conURL);

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<ConDocumentList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });

    }

    private void changeRequestStatus(String newPhase, String notifMessage) {


        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setRequestPhase(Data.getStudentId(), newPhase);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getRemarks());
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

        //Insert new notification

        Call<ResponsePOJO> call2 = RetrofitClient.getInstance()
                .getApi().insertNotification(Data.getStudentSolId(), notifMessage);
        call2.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call2, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println(response.body().getRemarks());
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call2, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });
        getFragmentManager().popBackStack();
    }


}