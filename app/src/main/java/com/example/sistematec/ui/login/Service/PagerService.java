package com.example.sistematec.ui.login.Service;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerService extends FragmentPagerAdapter {

    private int tabNumber;

    public PagerService(FragmentManager fm, int tabNumber) {
        super(fm);
        this.tabNumber = tabNumber;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0: {
                return new FragmentServiceRequestsHistoryValidate();

            }
            case 1: {
                return new FragmentServiceRequestsHistoryDeny();
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return tabNumber;
    }
}
