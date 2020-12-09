package com.example.sistematec.ui.login.academy;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.PDFMethods;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestDocumentsList;
import com.example.sistematec.ui.login.Dialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAcademyAwaitingRequests extends Fragment implements View.OnClickListener {

    TextView txtAAR_StudentDate, txtAAR_StudentName, txtAAR_StudentID, txtAAR_Request, txtAAR_Kardex, txtAAR_AnalysisCapture;
    Button btnAAR_AnalysisCapture, btnAAR_Review, btnAAR_Review2, btnAARUploadAn;

    String requestUrl;
    String kardexUrl;

    String requestName;
    String kardexName;
    String analysisName;

    String encodedAn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academy_awaiting_requests, container, false);
        setViews(v);
        setData();
        getDocumentData();
        setStudentData();
        return v;
    }

    private void setData() {
        requestName = "-Solicitud.pdf";
        kardexName = "-Kardex.pdf";
        analysisName = "Analisis.pdf";
    }

    private void setStudentData() {
        //procedimiento de llenado de información con la BD
        //txtAAR_StudentDate.setText("Fecha: 25 de octubre de 2019");
        txtAAR_StudentName.setText("Nombre: " + Data.getStudentName());
        txtAAR_StudentID.setText("Matrícula: " + Data.getStudentId());
    }

    private void setViews(View v) {
        txtAAR_StudentDate = v.findViewById(R.id.txtAAR_StudentDate);
        txtAAR_StudentName = v.findViewById(R.id.txtAAR_StudentName);
        txtAAR_StudentID = v.findViewById(R.id.txtAAR_StudentID);
        txtAAR_Request = v.findViewById(R.id.txtAAR_Request);
        txtAAR_Kardex = v.findViewById(R.id.txtAAR_Kardex);
        txtAAR_AnalysisCapture = v.findViewById(R.id.txtAAR_AnalysisCapture);

        btnAAR_AnalysisCapture = v.findViewById(R.id.btnAAR_AnalysisCaptrue);
        btnAAR_Review = v.findViewById(R.id.btnAAR_Review);
        btnAAR_Review2 = v.findViewById(R.id.btnAAR_Review2);
        btnAARUploadAn = v.findViewById(R.id.btnAAR_UploadAn);

        btnAAR_AnalysisCapture.setOnClickListener(this);
        btnAAR_Review.setOnClickListener(this);
        btnAAR_Review2.setOnClickListener(this);
        btnAARUploadAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //logica de aceptar o rechazar solicitud

        switch (view.getId()){
            case R.id.btnAAR_Review:{
                if (requestUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnAAR_Review2:{
                if (requestUrl != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(kardexUrl));
                    startActivity(browserIntent);
                }
                break;
            }
            case R.id.btnAAR_AnalysisCaptrue:{
                selectDocument();

                break;
            }
            case R.id.btnAAR_UploadAn: {
                if (encodedAn.isEmpty()) {
                    Toast.makeText(getActivity(), "Documentación incompleta.", Toast.LENGTH_LONG).show();
                    return;
                }
                confirmUpload();
                break;
            }
        }
    }

    public void selectDocument() {
        PDFMethods.startPDFChooser(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PDFMethods.processPDFData(requestCode, resultCode, data, this.getActivity());

        encodedAn = PDFMethods.getEncodedPDF();
        System.out.println(PDFMethods.getEncodedPDF() == null);
        txtAAR_AnalysisCapture.setText(PDFMethods.getPdfRealName());
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

                    txtAAR_Request.setText(response.body().get(0).getAluMatricula() + requestName);
                    txtAAR_Kardex.setText(response.body().get(0).getAluMatricula() + kardexName);

                    requestUrl = response.body().get(0).getSolDocUrl();
                    kardexUrl = response.body().get(0).getSolKardexUrl();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<StudentRequestDocumentsList>> call, Throwable t) {
                System.out.println("Error de conexión FAAR:1" );
            }
        });

    }

    public void confirmUpload() {

        Dialog.showConfirmDialog(getActivity(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadDocument();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    public void uploadDocument () {

        Dialog.showLoadingDialog(getActivity());

        System.out.println(Data.getStudentDepId());
        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().uploadAnalysis(Data.getStudentId(),
                        encodedAn, analysisName);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                changeRequestStatus("3", "2");
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión FAAR:4", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
            }
        });

    }

    private void changeRequestStatus(String newPhase, String notifMessage) {


        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().setRequestPhase(Data.getStudentId(), newPhase);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                System.out.println(response.body().getRemarks());
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión FAAR:3", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Error de conexión FAAR:4", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
            }
        });
        getFragmentManager().popBackStack();
    }



}
