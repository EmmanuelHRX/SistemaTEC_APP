package com.example.sistematec.ui.login.Student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sistematec.R;


public class FragmentStudentRequestsNotifications extends Fragment {

    private OnFragmentInteractionListener mListener;

    LinearLayout lay_studentReqNotif_notif1;
    LinearLayout lay_studentReqNotif_notif2;
    LinearLayout lay_studentReqNotif_notif3;
    LinearLayout lay_studentReqNotif_notif4;
    LinearLayout lay_studentReqNotif_notif5;
    LinearLayout lay_studentReqNotif_notif6;


    public FragmentStudentRequestsNotifications() {
        // Required empty public constructor
    }


    public static FragmentStudentRequestsNotifications newInstance() {
        FragmentStudentRequestsNotifications fragment = new FragmentStudentRequestsNotifications();
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
        View view = inflater.inflate(R.layout.fragment_student_requests_notifications, container, false);

        lay_studentReqNotif_notif1 = view.findViewById(R.id.lay_studentReqNotif_notif1);
        lay_studentReqNotif_notif1.setVisibility(View.INVISIBLE);
        lay_studentReqNotif_notif2 = view.findViewById(R.id.lay_studentReqNotif_notif2);
        lay_studentReqNotif_notif2.setVisibility(View.INVISIBLE);
        lay_studentReqNotif_notif3 = view.findViewById(R.id.lay_studentReqNotif_notif3);
        lay_studentReqNotif_notif3.setVisibility(View.INVISIBLE);
        lay_studentReqNotif_notif4 = view.findViewById(R.id.lay_studentReqNotif_notif4);
        lay_studentReqNotif_notif4.setVisibility(View.INVISIBLE);
        lay_studentReqNotif_notif5 = view.findViewById(R.id.lay_studentReqNotif_notif5);
        lay_studentReqNotif_notif5.setVisibility(View.INVISIBLE);
        lay_studentReqNotif_notif6 = view.findViewById(R.id.lay_studentReqNotif_notif6);
        lay_studentReqNotif_notif6.setVisibility(View.INVISIBLE);

        showNotifications();

        return view;
    }

    private void showNotifications() {
        //DB request

        int processProgress = 3;

        switch  (processProgress) {
            case 6: {
                lay_studentReqNotif_notif6.setVisibility(View.VISIBLE);
            }
            case 5: {
                lay_studentReqNotif_notif5.setVisibility(View.VISIBLE);
            }
            case 4: {
                lay_studentReqNotif_notif4.setVisibility(View.VISIBLE);
            }
            case 3: {
                lay_studentReqNotif_notif3.setVisibility(View.VISIBLE);
            }
            case 2: {
                lay_studentReqNotif_notif2.setVisibility(View.VISIBLE);
            }
            case 1: {
                lay_studentReqNotif_notif1.setVisibility(View.VISIBLE);
            }
            case 0: {

                break;
            }
            default : {

            }
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
