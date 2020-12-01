package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentDataList;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStudentRequests extends Fragment implements View.OnClickListener {

    private static final String ARG_ID = "id";

    FragmentManager manager;
    LinearLayout lay_studentRequests_lay1;
    FloatingActionButton floatbtnAddRequest;
    TextView txt_studentRequests_type;
    TextView txt_studentRequests_folio;
    TextView txt_studentRequests_noReqWarn;
    Button btn_studentRequests_check;
    ImageView img_studentRequests_noReq;

    private String id;

    String requestType;
    String requestFolio;

    private OnFragmentInteractionListener mListener;

    public FragmentStudentRequests() {
        // Required empty public constructor
    }


    public static FragmentStudentRequests newInstance() {
        FragmentStudentRequests fragment = new FragmentStudentRequests();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_student_requests, container, false);

        manager = getFragmentManager();

        lay_studentRequests_lay1 = view.findViewById(R.id.lay_studentRequests_lay1);
        txt_studentRequests_type = view.findViewById(R.id.txt_studentRequests_type);
        txt_studentRequests_folio = view.findViewById(R.id.txt_studentRequests_folio);
        txt_studentRequests_noReqWarn = view.findViewById(R.id.txt_studentRequests_noReqWarn);
        btn_studentRequests_check = view.findViewById(R.id.btn_studentRequests_check);
        btn_studentRequests_check.setOnClickListener(this);
        img_studentRequests_noReq = view.findViewById(R.id.img_studentRequest_noReqs);

        floatbtnAddRequest = view.findViewById(R.id.floatbtn_student_requests_add);
        floatbtnAddRequest.setOnClickListener(this);

        lay_studentRequests_lay1.setVisibility(View.INVISIBLE);
        txt_studentRequests_type.setVisibility(View.INVISIBLE);
        txt_studentRequests_folio.setVisibility(View.INVISIBLE);
        btn_studentRequests_check.setVisibility(View.INVISIBLE);
        txt_studentRequests_noReqWarn.setVisibility(View.INVISIBLE);
        img_studentRequests_noReq.setVisibility(View.INVISIBLE);
        floatbtnAddRequest.hide();

        checkForRequest();

        setRequestData();

        return view;
    }

    private void setRequestData() {
        //BD request
        requestType = "Convalidación de estudios";
        requestFolio = "123";

        txt_studentRequests_type.setText(requestType);
        txt_studentRequests_folio.setText(requestFolio);

    }

    private void showRequestExistence(boolean isThereNayRequest) {
        //BD data request
        //If there's a request, shows the request and hide the noReq message and image
        boolean testBool = true;
        if (isThereNayRequest) {

            lay_studentRequests_lay1.setVisibility(View.VISIBLE);
            txt_studentRequests_type.setVisibility(View.VISIBLE);
            txt_studentRequests_folio.setVisibility(View.VISIBLE);
            btn_studentRequests_check.setVisibility(View.VISIBLE);

            if (testBool) {
                txt_studentRequests_noReqWarn.setVisibility(View.INVISIBLE);
                img_studentRequests_noReq.setVisibility(View.INVISIBLE);
                floatbtnAddRequest.hide();
            }

            if (testBool)
                return;
        }

        img_studentRequests_noReq.setVisibility(View.VISIBLE);
        txt_studentRequests_noReqWarn.setVisibility(View.VISIBLE);
        floatbtnAddRequest.show();

        if (testBool) {
            lay_studentRequests_lay1.setVisibility(View.INVISIBLE);
            txt_studentRequests_type.setVisibility(View.INVISIBLE);
            txt_studentRequests_folio.setVisibility(View.INVISIBLE);
            btn_studentRequests_check.setVisibility(View.INVISIBLE);
        }

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

        if (id == R.id.floatbtn_student_requests_add) {
            FragmentStudentRequestsCapture frgStudentRC = FragmentStudentRequestsCapture.newInstance(this.id);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container_student, frgStudentRC, "StudentRC");
            transaction.addToBackStack("addStudentRC");
            transaction.commit();
            return;
        }
        if (id == R.id.btn_studentRequests_check) {
            if (Integer.parseInt(Data.getStudentSolPhaseId()) == 5) {
                FragmentStudentRequestsCon frgStudentRC = FragmentStudentRequestsCon.newInstance();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container_student, frgStudentRC, "StudentRC");
                transaction.addToBackStack("addStudentRC");
                transaction.commit();
                return;
            }
            FragmentStudentRequestsStatus frgStudentRS = FragmentStudentRequestsStatus.newInstance();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container_student, frgStudentRS, "StudentRS");
            transaction.addToBackStack("addStudentRS");
            transaction.commit();
            return;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void checkForRequest() {

        Call<List<StudentRequestList>> call = RetrofitClient.getInstance().getApi().getStudentRequest(Data.getStudentId());
        call.enqueue(new Callback<List<StudentRequestList>>() {
            @Override
            public void onResponse(Call<List<StudentRequestList>> call, Response<List<StudentRequestList>> response) {
                System.out.println(response.body());
                if (response.body() != null) {
                    //System.out.println("MODIFICANDO: " + response.body().get(0).getSolId());
                    txt_studentRequests_folio.setText("Folio: " + response.body().get(0).getSolId());
                    Data.setStudentSolId(response.body().get(0).getSolId());
                    Data.setStudentSolPhaseId(response.body().get(0).getSolEtapaId());
                    Data.setStudentSolPhaseDescription(response.body().get(0).getEtapaDescripcion());
                    showRequestExistence(true);

                } else {
                    Toast.makeText(getActivity(), "No hay solicitudes.", Toast.LENGTH_SHORT).show();
                    showRequestExistence(false);
                }
            }

            @Override
            public void onFailure(Call<List<StudentRequestList>> call, Throwable t) {
                System.out.println("FALLA - " );
            }
        });
    }

}
