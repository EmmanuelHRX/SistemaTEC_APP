package com.example.sistematec.ui.login.Service;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sistematec.R;

public class FragmentServiceRequestsHistory extends Fragment {

    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    TabItem tabItemSRH_validate, tabItemSHR_deny;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_requests_history, container, false);
        setData(v);
        return v;
    }

    private void setData(View v) {
        viewPager = v.findViewById(R.id.viewPagerSRH);
        tabLayout = v.findViewById(R.id.tablayoutSRH);
        tabItemSRH_validate = v.findViewById(R.id.tabItemSRH_validate);
        tabItemSHR_deny = v.findViewById(R.id.tabItemSHR_deny);

            pagerAdapter = new PagerService(getFragmentManager(),tabLayout.getTabCount());
            viewPager.setAdapter(pagerAdapter);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }


            });
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }

}
