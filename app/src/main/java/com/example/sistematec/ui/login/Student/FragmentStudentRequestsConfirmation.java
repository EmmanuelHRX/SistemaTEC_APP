package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistematec.R;

public class FragmentStudentRequestsConfirmation extends Fragment implements View.OnClickListener {

    private static final String ARG_CAREER = "career";

    private OnFragmentInteractionListener mListener;

    private String career;

    ImageView img_studentReqConfirmation_confirmation;
    TextView txt_studentReqConfirmation_message;
    Button btn_studentReqConfirmation_back;

    public FragmentStudentRequestsConfirmation() {
        // Required empty public constructor
    }

    public static FragmentStudentRequestsConfirmation newInstance(String career) {
        FragmentStudentRequestsConfirmation fragment = new FragmentStudentRequestsConfirmation();
        Bundle args = new Bundle();
        args.putString(ARG_CAREER, career);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            career = getArguments().getString(ARG_CAREER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_requests_confirmation, container, false);

        img_studentReqConfirmation_confirmation = view.findViewById(R.id.img_studentReqConfirmation_confirmation);
        txt_studentReqConfirmation_message = view.findViewById(R.id.txt_studentReqConfirmation_message);
        btn_studentReqConfirmation_back = view.findViewById(R.id.btn_studentReqConfirmation_back);
        btn_studentReqConfirmation_back.setOnClickListener(this);

        setConfirmation();

        return view;
    }

    private void setConfirmation() {
        if (checkInfo()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                img_studentReqConfirmation_confirmation.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                        getActivity().getApplicationContext().getTheme()));
            } else {
                img_studentReqConfirmation_confirmation.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
            }
            txt_studentReqConfirmation_message.setText("Tu solicitud ha sido enviada correctamente");
            return;
        }
        txt_studentReqConfirmation_message.setText("No cumples con los requisitos para convalidación." +
                                                    "\nTu solicitud ha sido rechazada");
    }

    private boolean checkInfo() {
        //check student info

        return false;
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

        if (id == R.id.btn_studentReqConfirmation_back){
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
