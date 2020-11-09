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

import com.example.sistematec.R;

public class FragmentStudentRequests extends Fragment implements View.OnClickListener {

    FragmentManager manager;
    FloatingActionButton floatbtnAddRequest;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_requests, container, false);

        manager = getFragmentManager();

        floatbtnAddRequest = view.findViewById(R.id.floatbtn_student_requests_add);
        floatbtnAddRequest.setOnClickListener(this);

        return view;
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

        if(id == R.id.floatbtn_student_requests_add) {
            FragmentStudentRequestsCapture frgStudentRC = FragmentStudentRequestsCapture.newInstance("Nothing", "Nothing");
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container_student, frgStudentRC, "StudentRC");
            transaction.addToBackStack("addStudentRC");
            transaction.commit();

        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
