package com.example.sistematec.ui.login.Coordinator;

import android.content.DialogInterface;
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
import com.example.sistematec.ui.login.DatabaseConection.PersonalRequestsList;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestDocumentsList;
import com.example.sistematec.ui.login.Dialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCoordinatorStudentAwaitingRequest extends Fragment implements View.OnClickListener{

    TextView txtCSAR_RequestDate, txtCSAR_StudentName, txtCSAR_StudentID;
    TextView txtCSAR_Request, txtCSAR_Kardex, txtCSAR_Lab, txtCSAR_Lib;
    Button btnCSAR_Review, btnCSAR_Review2, btnCSAR_Review3, btnCSAR_Review4, btnCSAR_Confirm, btnCSAR_Deny;
    String requestUrl;
    String kardexUrl;
    String labUrl;
    String libUrl;

    String requestName;
    String kardexName;
    String labName;
    String libName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_student_awaiting_request, container, false);

        setData();
        setViews(v);
        setStudentData();
        getDocumentData();

        return v;
    }

    private void setData() {
        requestName = "-Solicitud.pdf";
        kardexName = "-Kardex.pdf";
        labName = "-laboratorio.pdf";
        libName = "-biblioteca.pdf";
    }

    private void setStudentData() {
        //obtención de la información del estudiante mediante la BD
        //txtCSAR_RequestDate.setText("Fecha: ---------");
        txtCSAR_StudentID.setText("Matrícula: " + Data.getStudentId());
        txtCSAR_StudentName.setText("Nombre: " + Data.getStudentName());
    }

    private void setViews(View v) {
        //lo que sea que muestre awaiting requests
        txtCSAR_RequestDate = v.findViewById(R.id.txtCSAR_RequestDate);
        txtCSAR_StudentName = v.findViewById(R.id.txtCSAR_StudentName);
        txtCSAR_StudentID = v.findViewById(R.id.txtCSAR_StudentID);

        txtCSAR_Request = v.findViewById(R.id.txtCSAR_ReviewRequest);
        txtCSAR_Kardex = v.findViewById(R.id.txtCSAR_Kardex);
        txtCSAR_Lab = v.findViewById(R.id.txtCSAR_ReviewLab);
        txtCSAR_Lib = v.findViewById(R.id.txtCSAR_ReviewLib);

        btnCSAR_Review = v.findViewById(R.id.btnCSAR_Review);
        btnCSAR_Review2 = v.findViewById(R.id.btnCSAR_Review4);
        btnCSAR_Review3 = v.findViewById(R.id.btnCSAR_Review3);
        btnCSAR_Review4 = v.findViewById(R.id.btnCSAR_Review2);
        btnCSAR_Confirm = v.findViewById(R.id.btnCSAR_Confirm);
        btnCSAR_Deny = v.findViewById(R.id.btnCSAR_Deny);

        btnCSAR_Review.setOnClickListener(this);
        btnCSAR_Review2.setOnClickListener(this);
        btnCSAR_Review3.setOnClickListener(this);
        btnCSAR_Review4.setOnClickListener(this);
        btnCSAR_Confirm.setOnClickListener(this);
        btnCSAR_Deny.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCSAR_Review:{
                //logica para abrir la solicitud
                if (requestUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnCSAR_Review2:{
                //logica para abrir el comprobante
                if (libUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(kardexUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnCSAR_Review3:{
                //logica para abrir el comprobante
                if (labUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(labUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnCSAR_Review4:{
                //logica para abrir la kardex
                if (kardexUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(libUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnCSAR_Confirm:{
                //lógica cambiar el estado del proceso mediante la BD
                confirmRequest();
                break;
            }
            case R.id.btnCSAR_Deny:{
                //lógica para rechazar
                notConfirmRequest();
                break;
            }
        }
    }

    private void getDocumentData() {

        Call<List<StudentRequestDocumentsList>> call = RetrofitClient.getInstance().getApi()
                .getStudentRequestDocuments(Data.getStudentId());
        call.enqueue(new Callback<List<StudentRequestDocumentsList>>() {
            @Override
            public void onResponse(Call<List<StudentRequestDocumentsList>> call, Response<List<StudentRequestDocumentsList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());

                    txtCSAR_Request.setText(response.body().get(0).getAluMatricula() + requestName);
                    txtCSAR_Kardex.setText(response.body().get(0).getAluMatricula() + kardexName);
                    txtCSAR_Lab.setText(response.body().get(0).getAluMatricula() + labName);
                    txtCSAR_Lib.setText(response.body().get(0).getAluMatricula() + libName);

                    requestUrl = response.body().get(0).getSolDocUrl();
                    kardexUrl = response.body().get(0).getSolKardexUrl();
                    labUrl = response.body().get(0).getSolLabDocUrl();
                    libUrl = response.body().get(0).getSolLibDocUrl();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<StudentRequestDocumentsList>> call, Throwable t) {
                System.out.println("Error de conexión FCSAR:1" );
            }
        });

    }

    public void confirmRequest() {

        Dialog.showConfirmDialog(getActivity(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                acceptRequest();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    public void notConfirmRequest() {

        Dialog.showConfirmDialog(getActivity(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                rejectRequest();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    private void acceptRequest() {
        changeRequestStatus("2", "2");
        changeRequestStatus("2", "3");
    }

    private void rejectRequest() {
        changeRequestStatus("1", "1");

    }

    private void changeRequestStatus(String newPhase, String notifMessage) {

        if (!Dialog.loadingDialogIsShowing()) {
            Dialog.showLoadingDialog(getActivity());
        }

        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setRequestPhase(Data.getStudentId(), newPhase);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                System.out.println(response.body().getRemarks());
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión FCSAR:2", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
            }
        });

        //Insert new notification

        Call<ResponsePOJO> call2 = RetrofitClient.getInstance()
                .getApi().insertNotification(Data.getStudentSolId(), notifMessage);
        call2.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call2, Response<ResponsePOJO> response) {
                System.out.println(response.body().getRemarks());
                Dialog.hideDialog();
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call2, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión FCSAR:3", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
            }
        });
        getFragmentManager().popBackStack();
    }



}
