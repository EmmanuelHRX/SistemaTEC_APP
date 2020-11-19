package com.example.sistematec.ui.login.Coordinator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sistematec.R;

public class FragmentCoordinatorAwaitingRequest extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coordinator_awaiting_request, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        //lo que sea que muestre awaiting requests
    }

}
