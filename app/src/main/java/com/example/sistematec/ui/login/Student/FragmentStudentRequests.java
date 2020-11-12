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

import com.example.sistematec.R;

public class FragmentStudentRequests extends Fragment implements View.OnClickListener {

    private static final String ARG_ID = "id";

    FragmentManager manager;
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


    public static FragmentStudentRequests newInstance(String id) {
        FragmentStudentRequests fragment = new FragmentStudentRequests();
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
        View view = inflater.inflate(R.layout.fragment_student_requests, container, false);

        manager = getFragmentManager();

        txt_studentRequests_type = view.findViewById(R.id.txt_studentRequests_type);
        txt_studentRequests_folio = view.findViewById(R.id.txt_studentRequests_folio);
        txt_studentRequests_noReqWarn = view.findViewById(R.id.txt_studentRequests_noReqWarn);
        btn_studentRequests_check = view.findViewById(R.id.btn_studentRequests_check);
        btn_studentRequests_check.setOnClickListener(this);
        img_studentRequests_noReq = view.findViewById(R.id.img_studentRequest_noReqs);

        floatbtnAddRequest = view.findViewById(R.id.floatbtn_student_requests_add);
        floatbtnAddRequest.setOnClickListener(this);

        showRequestExistence();
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

    private void showRequestExistence() {
        //BD data request
        //If there's a request, shows the request and hide the noReq message and image
        boolean testBool = false;
        boolean theresARequest = true;
        if (theresARequest) {

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
}
