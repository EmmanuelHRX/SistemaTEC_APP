package com.example.sistematec.ui.login.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.example.sistematec.R;
import com.example.sistematec.ui.login.FragmentAllSettings;
import com.example.sistematec.ui.login.Login.LoginActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ActivityService extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        NavigationView navigationView = findViewById(R.id.nav_view_service);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container_service, new FragmentServiceProfile()).commit();
            navigationView.setCheckedItem(R.id.nav_My_Profile);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else{
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch(item.getItemId()){
            case R.id.nav_My_Profile :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_service, new FragmentServiceProfile()).commit();
                break;
            }
            case R.id.nav_Requests :{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_service, new FragmentServiceRequests()).commit();
                break;
            }
            case R.id.nav_Requests_History :{
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_service, new FragmentServiceRequestsHistory()).commit();
                break;
            }
            case R.id.nav_Notifications :{
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_service, new FragmentServiceNotification()).commit();
                break;
            }
            case R.id.nav_Configuration :{
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_service, new FragmentAllSettings()).commit();
                break;
            }
            case R.id.nav_Close_Session :{
                Intent actLogin = new Intent(this, LoginActivity.class);
                startActivity(actLogin);
                finish();
                break;
            }

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
