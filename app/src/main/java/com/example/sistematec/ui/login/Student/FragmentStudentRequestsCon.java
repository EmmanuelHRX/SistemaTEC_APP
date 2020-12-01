package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.UploadAndDownload;
import com.example.sistematec.ui.login.DatabaseConection.ConDocumentList;
import com.example.sistematec.ui.login.DatabaseConection.DictumDocumentList;
import com.example.sistematec.ui.login.DatabaseConection.ResponsePOJO;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestDocumentsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentStudentRequestsCon extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    FragmentManager manager;
    FragmentActivity activity;


    TextView txt_studentReqConCapture_name;

    Button btnStudentRequestsConConfirm;
    Button btnStudentRequestsConViewDicForm;
    Button btnStudentRequestsConViewConForm;
    Button btnStudentRequestsConConCapture;

    TextView txtStudentRequestsConViewDicForm;
    TextView txtStudentRequestsConViewConForm;
    TextView txtStudentRequestsConConCapture;

    String dicURL;
    String conURL;

    String encodedCon;
    String conName;


    public FragmentStudentRequestsCon() {
        // Required empty public constructor
    }

    public static FragmentStudentRequestsCon newInstance() {
        FragmentStudentRequestsCon fragment = new FragmentStudentRequestsCon();
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
        View v = inflater.inflate(R.layout.fragment_student_requests_con, container, false);

        setViews(v);
        setData();
        getDocumentData();

        return v;
    }

    private void setViews(View v) {
        manager = getFragmentManager();
        activity = getActivity();

        txt_studentReqConCapture_name = v.findViewById(R.id.txt_studentReqConCapture_name);

        btnStudentRequestsConConfirm = v.findViewById(R.id.btn_studentReqCon_confirm);
        btnStudentRequestsConConfirm.setOnClickListener(this);
        btnStudentRequestsConViewDicForm = v.findViewById(R.id.btn_studentReqConCapture_viewDicForm);
        btnStudentRequestsConViewDicForm.setOnClickListener(this);
        btnStudentRequestsConViewConForm = v.findViewById(R.id.btn_studentReqConCapture_viewConForm);
        btnStudentRequestsConViewConForm.setOnClickListener(this);
        btnStudentRequestsConConCapture = v.findViewById(R.id.btn_studentReqConCapture_conCapture);
        btnStudentRequestsConConCapture.setOnClickListener(this);

        txtStudentRequestsConViewDicForm = v.findViewById(R.id.txt_studentReqConCapture_dicForm);
        txtStudentRequestsConViewConForm = v.findViewById(R.id.txt_studentReqConCapture_conForm);
        txtStudentRequestsConConCapture = v.findViewById(R.id.txt_studentReqConCapture_conCapture);
    }

    private void setData() {
        conName = "Conformidad.pdf";
    }

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

        if (id == R.id.btn_studentReqCon_confirm) {
            if (encodedCon.isEmpty()) {
                Toast.makeText(getActivity(), "Documentación incompleta.", Toast.LENGTH_LONG).show();
                return;
            }
            uploadDocument();
        }

        if (view == btnStudentRequestsConViewDicForm) {
            //Toast.makeText(getActivity(), "ViewSol", Toast.LENGTH_SHORT).show();

            if (dicURL != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(dicURL));
                startActivity(browserIntent);
            }
        }

        if (view == btnStudentRequestsConViewConForm) {
            //Toast.makeText(getActivity(), "ViewSol", Toast.LENGTH_SHORT).show();

            if (conURL != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(conURL));
                startActivity(browserIntent);
            }
        }

        if (view == btnStudentRequestsConConCapture) {
            //Toast.makeText(getActivity(), "SolCap", Toast.LENGTH_SHORT).show();
            selectDocument();

        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getDocumentData() {

        Call<List<DictumDocumentList>> call2 = RetrofitClient.getInstance().getApi()
                .getDicDocument(Data.getStudentId());
        call2.enqueue(new Callback<List<DictumDocumentList>>() {
            @Override
            public void onResponse(Call<List<DictumDocumentList>> call2, Response<List<DictumDocumentList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());

                    txtStudentRequestsConViewDicForm.setText(UploadAndDownload.getFileName(response.body().get(0).getSolDictamenDocUrl()));

                    dicURL = response.body().get(0).getSolDictamenDocUrl();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<DictumDocumentList>> call2, Throwable t) {
                System.out.println("FALLA -  DICTAMEN" );
            }
        });

        Call<List<ConDocumentList>> call = RetrofitClient.getInstance().getApi()
                .getConDocument(Data.getStudentId());
        call.enqueue(new Callback<List<ConDocumentList>>() {
            @Override
            public void onResponse(Call<List<ConDocumentList>> call, Response<List<ConDocumentList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {

                    Data.setStudentSolId(response.body().get(0).getSolId());

                    txtStudentRequestsConViewConForm.setText(UploadAndDownload.getFileName(response.body().get(0).getSolConformidadDocUrl()));

                    conURL = response.body().get(0).getSolConformidadDocUrl();

                } else {
                    Toast.makeText(getActivity(), "No info.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<ConDocumentList>> call, Throwable t) {
                System.out.println("FALLA - CONFORMIDAD" );
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

        encodedCon = UploadAndDownload.getEncodedPDF();
        txtStudentRequestsConConCapture.setText(UploadAndDownload.getPdfRealName());

    }

    public void uploadDocument () {

        Call<ResponsePOJO> call = RetrofitClient.getInstance()
                .getApi().uploadCon(Data.getStudentId(),
                        encodedCon, conName);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                //Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
                System.out.println("SUBIDA EXITOSA");
                changeRequestStatus("6", "7");
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
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
                Toast.makeText(getActivity(), "Ha fallado el método Estado", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getActivity(), "Ha fallado el método Notif", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });
        getFragmentManager().popBackStack();
    }
}
