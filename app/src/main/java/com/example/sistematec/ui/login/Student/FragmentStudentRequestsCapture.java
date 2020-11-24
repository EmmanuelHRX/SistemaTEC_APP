package com.example.sistematec.ui.login.Student;

import android.content.Context;
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
import com.example.sistematec.UploadAndDownload;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class FragmentStudentRequestsCapture extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String ARG_ID = "id";

    private OnFragmentInteractionListener mListener;

    FragmentManager manager;


    private String id;

    TextView txt_studentReqCapture_name;
    TextView txt_studentReqCapture_id;
    TextView txt_studentReqCapture_career;
    TextView txt_studentReqCapture_semester;

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

    String downloadedUrl = "http://192.168.56.1/sistematec/uploads/Formato-Solicitud.pdf";
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
            id = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_requests_capture, container, false);

        manager = getFragmentManager();

        txt_studentReqCapture_name = view.findViewById(R.id.txt_studentReqCapture_name);
        txt_studentReqCapture_id = view.findViewById(R.id.txt_studentReqCapture_id);;
        txt_studentReqCapture_career = view.findViewById(R.id.txt_studentReqCapture_career);;
        txt_studentReqCapture_semester = view.findViewById(R.id.txt_studentReqCapture_semester);;

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

        this.txt_studentReqCapture_name.setText("Nombre: " + getActivity().getIntent().getStringExtra("name"));
        this.txt_studentReqCapture_id.setText("Matrícula: " + getActivity().getIntent().getStringExtra("id"));
        this.txt_studentReqCapture_career.setText("Carrera: " + getActivity().getIntent().getStringExtra("career"));
        this.txt_studentReqCapture_semester.setText("Semestre: " + getActivity().getIntent().getStringExtra("semester"));
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

            uploadDocuments();

            boolean a = true;
            if (a)
                return;
            FragmentStudentRequestsConfirmation frgStudentRCon = FragmentStudentRequestsConfirmation
                    .newInstance();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container_student, frgStudentRCon, "StudentRCon");
            transaction.addToBackStack("addStudentRCon");
            transaction.commit();
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
        UploadAndDownload.startPDFChooser(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UploadAndDownload.processPDFData(requestCode, resultCode, data, this.getActivity());
        switch (docQueue) {
            case 0: {
                break;
            }
            case 1: {
                encodedSol = UploadAndDownload.getEncodedPDF();
                System.out.println(UploadAndDownload.getEncodedPDF() == null);
                txtStudentRequestsSolCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }
            case 2: {
                encodedKardex = UploadAndDownload.getEncodedPDF();
                System.out.println(UploadAndDownload.getEncodedPDF() == null);
                txtStudentRequestsKardexCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }
            case 3: {
                encodedLab = UploadAndDownload.getEncodedPDF();
                System.out.println(UploadAndDownload.getEncodedPDF() == null);
                txtStudentRequestsNoDebtLabCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }
            case 4: {
                encodedLib = UploadAndDownload.getEncodedPDF();
                System.out.println(UploadAndDownload.getEncodedPDF() == null);
                txtStudentRequestsNoDebtLibCapture.setText(UploadAndDownload.getPdfRealName());
                break;
            }
        }

    }

    public void uploadDocuments () {

        if (encodedSol.isEmpty() || encodedKardex.isEmpty() || encodedLab.isEmpty() || encodedLib.isEmpty()) {
            //Toast.makeText(getActivity(), "Documentación incompleta.", Toast.LENGTH_LONG).show();
            //return;
        }
        System.out.println(Data.getStudentDepId());
        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().uploadRequestDocuments(Data.getStudentId(), Data.getStudentDepId(),
                        encodedSol, solName, encodedKardex, kardexName);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 1", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

        Call<ResponsePOJO> call2 = RetrofitClient.getInstance()
                .getApi().uploadRequestDocuments2(Data.getStudentId(), encodedLab,
                        labName, encodedLib, libName);
        call2.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call2, Response<ResponsePOJO> response) {
                Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call2, Throwable t) {
                Toast.makeText(getActivity(), "Ha fallado el método 2", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });

    }

}
