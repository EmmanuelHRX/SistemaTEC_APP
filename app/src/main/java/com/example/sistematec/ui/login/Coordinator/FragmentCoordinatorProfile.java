package com.example.sistematec.ui.login.Coordinator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistematec.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCoordinatorProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCoordinatorProfile extends Fragment {



    ImageView img;
    TextView txtCoordinatorProfileName, txtCoordinatorProfileEnrollment,txtCoordinatorProfileJob;
    // TODO: Rename and change types of parameters


    public FragmentCoordinatorProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCoordinatorProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCoordinatorProfile newInstance(String param1, String param2) {
        FragmentCoordinatorProfile fragment = new FragmentCoordinatorProfile();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_profile_coordinator, container, false);
        setData(v);
        return v;
    }
    private void setData(View v) {
        img = v.findViewById(R.id.imgCoordinatorProfile);
        txtCoordinatorProfileName = v.findViewById(R.id.txtCoordinatorProfileName);
        txtCoordinatorProfileEnrollment = v.findViewById(R.id.txtCoordinatorProfileID);
        txtCoordinatorProfileJob = v.findViewById(R.id.txtCoordinatorProfileJob);
        //procedimiento de llenado de la información

        txtCoordinatorProfileName.setText("Nombre: Marco Arturo Uribe López");
        txtCoordinatorProfileEnrollment.setText("Matricula: 17171503");
        txtCoordinatorProfileJob.setText("Puesto: Coordinador.");
    }
}