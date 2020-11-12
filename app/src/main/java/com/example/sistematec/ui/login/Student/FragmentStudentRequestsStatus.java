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

import com.example.sistematec.R;

public class FragmentStudentRequestsStatus extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    ImageView img_studentReqStatus_info1;
    ImageView img_studentReqStatus_info2;
    ImageView img_studentReqStatus_info3;
    ImageView img_studentReqStatus_info4;
    ImageView img_studentReqStatus_info5;
    ImageView img_studentReqStatus_info6;
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
        img_studentReqStatus_info2 = view.findViewById(R.id.img_studentReqStatus_info2);
        img_studentReqStatus_info3 = view.findViewById(R.id.img_studentReqStatus_info3);
        img_studentReqStatus_info4 = view.findViewById(R.id.img_studentReqStatus_info4);
        img_studentReqStatus_info5 = view.findViewById(R.id.img_studentReqStatus_info5);
        img_studentReqStatus_info6 = view.findViewById(R.id.img_studentReqStatus_info6);
        btn_studentReqStatus_back = view.findViewById(R.id.btn_studentReqStatus_back);
        btn_studentReqStatus_back.setOnClickListener(this);

        setInfoStatus();

        return view;
    }

    private void setInfoStatus() {
        //DB request
        int[] infoStatus = new int[] {1, 1, 1, 0, 0, 0};

        for (int i = 0; i < infoStatus.length; i++) {
            if (infoStatus[i] == 0) {
                continue;
            }
            setInfoStatus(i);
        }

    }
    //TODO: Cambiar íconos ic_menu_send por palomitas
    private void setInfoStatus(int infoNumber) {
        switch (infoNumber) {

            case 0: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info1.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info1.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }
                break;
            }

            case 1: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info2.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info2.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }

                break;
            }

            case 2: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info3.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info3.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }

                break;
            }

            case 3: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info4.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info4.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }

                break;
            }

            case 4: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info5.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info5.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }

                break;
            }

            case 5: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    img_studentReqStatus_info6.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send,
                            getActivity().getApplicationContext().getTheme()));
                } else {
                    img_studentReqStatus_info6.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
                }

                break;
            }

            default: {

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
}
