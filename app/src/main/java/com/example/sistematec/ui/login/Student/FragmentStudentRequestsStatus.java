package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.DatabaseConection.RetrofitClient;
import com.example.sistematec.ui.login.DatabaseConection.StudentRequestList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStudentRequestsStatus extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    ImageView img_studentReqStatus_info1;
    TextView txt_studentReqStatus_info;
    TextView txt_studentReqStatus_desc;
    Button btn_studentReqStatus_back;


    public FragmentStudentRequestsStatus() {
        // Required empty public constructor
    }

    public static FragmentStudentRequestsStatus newInstance() {
        FragmentStudentRequestsStatus fragment = new FragmentStudentRequestsStatus();
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
        View view = inflater.inflate(R.layout.fragment_student_requests_status, container, false);

        img_studentReqStatus_info1 = view.findViewById(R.id.img_studentReqStatus_info1);
        txt_studentReqStatus_info = view.findViewById(R.id.txt_studentReqStatus_info);
        txt_studentReqStatus_desc =  view.findViewById(R.id.txt_studentReqStatus_desc);
        btn_studentReqStatus_back = view.findViewById(R.id.btn_studentReqStatus_back);
        btn_studentReqStatus_back.setOnClickListener(this);

        setInfoStatus();

        return view;
    }


    //TODO: Cambiar íconos ic_menu_send por palomitas
    private void setInfoStatus(int infoNumber) {

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

        if (id == R.id.btn_studentReqStatus_back) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setInfoStatus() {
        //DB request or not

        txt_studentReqStatus_desc.setText(Data.getStudentSolPhaseDescription());


    }
}
