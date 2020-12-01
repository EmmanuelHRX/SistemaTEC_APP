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
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCoordinatorAnalysisAwaitingRequest extends Fragment implements View.OnClickListener {

    Button btnCAAR_Review, btnCAAR_uploadDictum, btnCAAR_dictumCapture, btnCAAR_ConCapture;
    TextView txtCAAR_StudentDate,txtCAAR_StudentName,txtCAAR_StudentID, txtCAAR_ReviewAnalysis;
    TextView txtCAAR_DictumCapture, txtCAAR_ConCapture;

    String encodedDictum;
    String encodedCon;

    String analysisURL;

    String analysisName;
    String dictumName;
    String conName;

    int docQueue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_analysis_awaiting_request, container, false);
        setViews(v);
        setData();
        setStudentData();
        getDocumentData();
        return v;
    }

    private void setViews(View v) {
        txtCAAR_StudentDate = v.findViewById(R.id.txtCAAR_StudentDate);
        txtCAAR_StudentName = v.findViewById(R.id.txtCAAR_StudentName);
        txtCAAR_StudentID = v.findViewById(R.id.txtCAAR_StudentID);
        txtCAAR_ReviewAnalysis = v.findViewById(R.id.txtCAAR_ReviewAnalysis);
        txtCAAR_DictumCapture = v.findViewById(R.id.txtCAAR_dictumCapture);
        txtCAAR_ConCapture = v.findViewById(R.id.txtCAAR_conCapture);

        btnCAAR_Review = v.findViewById(R.id.btnCAAR_ReviewAnalysis);
        btnCAAR_dictumCapture = v.findViewById(R.id.btnCAAR_dictumCapture);
        btnCAAR_uploadDictum = v.findViewById(R.id.btnCAAR_uploadDictum);
        btnCAAR_ConCapture = v.findViewById(R.id.btnCAAR_conCapture);

        btnCAAR_uploadDictum.setOnClickListener(this);
        btnCAAR_Review.setOnClickListener(this);
        btnCAAR_dictumCapture.setOnClickListener(this);
        btnCAAR_ConCapture.setOnClickListener(this);

    }

    private void setData() {
        analysisName = "-Análisis.pdf";
        dictumName = "Dictamen.pdf";
        conName = "Conformidad.pdf";
    }

    private void setStudentData() {
        txtCAAR_StudentDate.setText("Fecha: ---");
        txtCAAR_StudentName.setText("Nombre: " + Data.getStudentName());
        txtCAAR_StudentID.setText("Matricula: " + Data.getStudentId());
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCAAR_ReviewAnalysis:{
                if (analysisURL != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(analysisURL));
                    startActivity(browserIntent);
                }
                break;
            }

            case R.id.btnCAAR_dictumCapture:{
                docQueue = 1;
                selectDocument();
                break;
            }

            case R.id.btnCAAR_conCapture: {
                docQueue = 2;
                selectDocument();
                break;
            }

            case R.id.btnCAAR_uploadDictum:{
                if (encodedDictum.isEmpty() || encodedCon.isEmpty()) {
                    Toast.makeText(getActivity(), "Documentación incompleta.", Toast.LENGTH_LONG).show();
                    return;
                }
                uploadDocument();
                break;
            }
        }
    }

    private void getDocumentData() {

        Call<List<AnalysisDocumentList>> call = RetrofitClient.getInstance().getApi()
                .getAnalysisDocument(Data.getStudentId());
        call.enqueue(new Callback<List<AnalysisDocumentList>>() {
            @Override
            public void onResponse(Call<List<AnalysisDocumentList>> call, Response<List<AnalysisDocumentList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());

                    txtCAAR_ReviewAnalysis.setText(response.body().get(0).getAluMatricula() + analysisName);

                    analysisURL = response.body().get(0).getSolAnalisisDocUrl();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<AnalysisDocumentList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });

    }

    public void selectDocument() {
        UploadAndDownload.startPDFChooser(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UploadAndDownload.processPDFData(requestCode, resultCode, data, this.getActivity());

        switch (docQueue) {
            case 1: {
                encodedDictum = UploadAndDownload.getEncodedPDF();
                txtCAAR_DictumCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }

            case 2: {
                encodedCon = UploadAndDownload.getEncodedPDF();
                txtCAAR_ConCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }
        }


    }

    public void uploadDocument () {

        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().uploadDictum(Data.getStudentId(),
                        encodedDictum, dictumName);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
                return;
            }
        });

        Call<ResponsePOJO> call2 = RetrofitClient.getInstance()
                .getApi().uploadCon(Data.getStudentId(),
                        encodedCon, conName);
        call2.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call2, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                changeRequestStatus("5", "6");
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call2, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
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