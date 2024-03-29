package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class FragmentStudentRequestsCapture extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String ARG_ID = "id";

    private OnFragmentInteractionListener mListener;

    FragmentManager manager;


    TextView txt_studentReqCapture_name;

    Button btnStudentRequestsConfirm;
    Button btnStudentRequestsViewSolForm;
    Button btnStudentRequestsSolCapture;
    Button btnStudentRequestsKardexCapture;
    Button btnStudentRequestsNoDebtLabCapture;
    Button btnStudentRequestsNoDebtLibCapture;

    TextView txtStudentRequestsViewSolForm;
    TextView txtStudentRequestsSolCapture;
    TextView txtStudentRequestsKardexCapture;
    TextView txtStudentRequestsNoDebtLabCapture;
    TextView txtStudentRequestsNoDebtLibCapture;

    Spinner spnr_studentReqCapture_subjects;

    int docQueue;

    String downloadedUrl = "https://sistematecapp.000webhostapp.com/sistematec/util/SolicitudFormato.pdf";
    String encodedSol;
    String solName;
    String encodedKardex;
    String kardexName;
    String encodedLab;
    String labName;
    String encodedLib;
    String libName;

    public FragmentStudentRequestsCapture() {
        // Required empty public constructor
    }

    public static FragmentStudentRequestsCapture newInstance(String id) {
        FragmentStudentRequestsCapture fragment = new FragmentStudentRequestsCapture();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
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
        View view = inflater.inflate(R.layout.fragment_student_requests_capture, container, false);

        manager = getFragmentManager();

        txt_studentReqCapture_name = view.findViewById(R.id.txt_studentReqCapture_name);

        btnStudentRequestsConfirm = view.findViewById(R.id.btn_student_requests_confirm);
        btnStudentRequestsConfirm.setOnClickListener(this);
        btnStudentRequestsViewSolForm = view.findViewById(R.id.btn_studentReqCapture_viewSolForm);
        btnStudentRequestsViewSolForm.setOnClickListener(this);
        btnStudentRequestsSolCapture = view.findViewById(R.id.btn_studentReqCapture_solCapture);
        btnStudentRequestsSolCapture.setOnClickListener(this);
        btnStudentRequestsKardexCapture = view.findViewById(R.id.btn_studentReqCapture_kardexCapture);
        btnStudentRequestsKardexCapture.setOnClickListener(this);
        btnStudentRequestsNoDebtLabCapture = view.findViewById(R.id.btn_studentReqCapture_noDebtLabCapture);
        btnStudentRequestsNoDebtLabCapture.setOnClickListener(this);
        btnStudentRequestsNoDebtLibCapture = view.findViewById(R.id.btn_studentReqCapture_noDebtLibCapture);
        btnStudentRequestsNoDebtLibCapture.setOnClickListener(this);

        txtStudentRequestsViewSolForm = view.findViewById(R.id.txt_studentReqCapture_solForm);
        txtStudentRequestsSolCapture = view.findViewById(R.id.txt_studentReqCapture_solCapture);
        txtStudentRequestsKardexCapture = view.findViewById(R.id.txt_studentReqCapture_kardexCapture);
        txtStudentRequestsNoDebtLabCapture = view.findViewById(R.id.txt_studentReqCapture_noDebtLabCapture);
        txtStudentRequestsNoDebtLibCapture = view.findViewById(R.id.txt_studentReqCapture_noDebtLibCapture);

        //spnr_studentReqCapture_subjects = view.findViewById(R.id.spnr_studentReqCapture_subjects);
        //spnr_studentReqCapture_subjects.setOnItemSelectedListener(this);

        //setSpinnerData();
        initVar();
        setData();

        return view;
    }

    private void initVar() {
        solName = "Solicitud.pdf";
        kardexName = "Kardex.pdf";
        labName = "Laboratorio.pdf";
        libName = "Biblioteca.pdf";

        encodedSol = "";
        encodedKardex = "";
        encodedLab = "";
        encodedLib = "";

        docQueue = 0;
    }

    private void setSpinnerData() {
        //DB request

        int subjectCount = 5;

        String[] subjects = new String[subjectCount];
        subjects = new String[] {"Ing. en Sistemas Computacionales", "Ing. en TICS", "Ing. en Mecatrónica", "Ing. Industrial",
                                    "Ing. en Gestión Empresarial"};

        for (int i = 0; i < 0; i++) {
            //For para llenar el arreglo...
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, subjects);
        spnr_studentReqCapture_subjects.setAdapter(adapter);
    }

    private void setData() {
        //DB request

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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_student_requests_confirm) {

            confirmUpload();

        }

        if (view == btnStudentRequestsViewSolForm) {
            //Toast.makeText(getActivity(), "ViewSol", Toast.LENGTH_SHORT).show();

            if (downloadedUrl != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadedUrl));
                startActivity(browserIntent);
            }
        }

        if (view == btnStudentRequestsSolCapture) {
            //Toast.makeText(getActivity(), "SolCap", Toast.LENGTH_SHORT).show();
            docQueue = 1;
            selectDocument();


        }

        if (view == btnStudentRequestsKardexCapture) {
            //Toast.makeText(getActivity(), "KarCap", Toast.LENGTH_SHORT).show();
            docQueue = 2;
            selectDocument();

        }

        if (view == btnStudentRequestsNoDebtLabCapture) {
            //Toast.makeText(getActivity(), "LabCap", Toast.LENGTH_SHORT).show();
            docQueue = 3;
            selectDocument();

        }

        if (view == btnStudentRequestsNoDebtLibCapture) {
            //Toast.makeText(getActivity(), "LibCap", Toast.LENGTH_SHORT).show();
            docQueue = 4;
            selectDocument();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("Toasting " + spnr_studentReqCapture_subjects.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void selectDocument() {
        PDFMethods.startPDFChooser(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PDFMethods.processPDFData(requestCode, resultCode, data, this.getActivity());

        switch (docQueue) {
            case 0: {
                break;
            }
            case 1: {
                encodedSol = PDFMethods.getEncodedPDF();
                txtStudentRequestsSolCapture.setText(PDFMethods.getPdfRealName());
                break;
            }
            case 2: {
                encodedKardex = PDFMethods.getEncodedPDF();
                txtStudentRequestsKardexCapture.setText(PDFMethods.getPdfRealName());
                break;
            }
            case 3: {
                encodedLab = PDFMethods.getEncodedPDF();
                txtStudentRequestsNoDebtLabCapture.setText(PDFMethods.getPdfRealName());
                break;
            }
            case 4: {
                encodedLib = PDFMethods.getEncodedPDF();
                //System.out.println("-----------------------------------------------------------------------------");
                //System.out.println("-----------------------------------------------------ENCODED " + String.valueOf(encodedLib == null));
                txtStudentRequestsNoDebtLibCapture.setText(PDFMethods.getPdfRealName());
                break;
            }
        }

    }

    public void confirmUpload() {
        Dialog.showConfirmDialog(getActivity(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                uploadDocuments();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    public void uploadDocuments () {

        if (encodedSol.isEmpty() || encodedKardex.isEmpty() || encodedLab.isEmpty() || encodedLib.isEmpty()) {
            Toast.makeText(getActivity(), "Documentación incompleta.", Toast.LENGTH_LONG).show();
            return;
        }

        Dialog.showLoadingDialog(getActivity());

        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().uploadRequestDocuments(Data.getStudentId(), Data.getStudentDepId(),
                        encodedSol, solName, encodedKardex, kardexName);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión: FSRC:1", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
                return;
            }
        });

        Call<ResponsePOJO> call2 = RetrofitClient.getInstance()
                .getApi().uploadRequestDocuments2(Data.getStudentId(), encodedLab,
                        labName, encodedLib, libName);
        call2.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call2, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                insertInitialNotification();

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call2, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión: FSRC:2", Toast.LENGTH_SHORT).show();
                Dialog.hideDialog();
                t.printStackTrace();
            }
        });

    }

    private void insertInitialNotification() {
        //Getting student request id first
        Call<List<StudentRequestDocumentsList>> call = RetrofitClient.getInstance().getApi()
                .getStudentRequestDocuments(Data.getStudentId());
        call.enqueue(new Callback<List<StudentRequestDocumentsList>>() {
            @Override
            public void onResponse(Call<List<StudentRequestDocumentsList>> call, Response<List<StudentRequestDocumentsList>> response) {
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());
                    insertNotification();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<StudentRequestDocumentsList>> call, Throwable t) {
                System.out.println("Error de conexión FSRC:3" );
                Dialog.hideDialog();
            }
        });

    }

    private void insertNotification() {
        Call<ResponsePOJO> call3 = RetrofitClient.getInstance()
                .getApi().insertNotification(Data.getStudentSolId(), "0");
        call3.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call3, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                //System.out.println(response.body().getRemarks());
                Dialog.hideDialog();
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call3, Throwable t) {
                //Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                Dialog.hideDialog();
            }
        });
        getFragmentManager().popBackStack();
    }

}
