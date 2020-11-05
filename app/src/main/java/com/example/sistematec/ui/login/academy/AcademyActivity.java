package com.example.sistematec.ui.login.academy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.FragmentAllSettings;
import com.example.sistematec.ui.login.LoginActivity;
import com.example.sistematec.ui.login.Service.FragmentProfileService;
import com.example.sistematec.ui.login.Service.FragmentServiceNotification;
import com.example.sistematec.ui.login.Service.FragmentServiceRequests;
import com.example.sistematec.ui.login.Service.FragmentServiceRequestsHistory;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AcademyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_academy);
        NavigationView navigationView = findViewById(R.id.nav_view_academy);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentProfileService()).commit();
            navigationView.setCheckedItem(R.id.nav_My_Profile);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_academy);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch(item.getItemId()){
            case R.id.nav_My_Profile :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentProfileService()).commit();
                break;
            }
            case R.id.nav_Requests :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentServiceRequests()).commit();
                break;
            }
            case R.id.nav_Requests_History :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentServiceRequestsHistory()).commit();
                break;
            }
            case R.id.nav_Notifications :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentServiceNotification()).commit();
                break;
            }
            case R.id.nav_Configuration :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAllSettings()).commit();
                break;
            }
            case R.id.nav_Close_Session :{
                Intent actLogin = new Intent(this, LoginActivity.class);
                startActivity(actLogin);
                finish();
                break;
            }
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout_academy);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
