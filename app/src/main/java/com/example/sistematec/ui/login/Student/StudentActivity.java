package com.example.sistematec.ui.login.Student;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.BackgroundTask.BackgroundTaks;
import com.example.sistematec.ui.login.FragmentAllSettings;
import com.example.sistematec.ui.login.Login.LoginActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener,
        FragmentStudentProfile.OnFragmentInteractionListener,
        FragmentStudentRequests.OnFragmentInteractionListener, FragmentStudentRequestsCapture.OnFragmentInteractionListener,
        FragmentStudentRequestsConfirmation.OnFragmentInteractionListener,
        FragmentStudentRequestsNotifications.OnFragmentInteractionListener,
        FragmentStudentRequestsStatus.OnFragmentInteractionListener, FragmentStudentRequestsCon.OnFragmentInteractionListener {

    FragmentManager manager;
    Intent intentService;



    boolean checkOnBackstackchanges;
    private TextView txt_navHeaderStudent_name;
    private TextView txt_navHeaderStudent_id;


    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_service);
        navigationView = findViewById(R.id.nav_view_student);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //Components Init///////////////////////////////////////////////////////////////////////////
        manager = getSupportFragmentManager();
        manager.addOnBackStackChangedListener(this);

        txt_navHeaderStudent_name = navigationView.getHeaderView(0).findViewById(R.id.txt_navHeaderStudent_name);
        txt_navHeaderStudent_id = navigationView.getHeaderView(0).findViewById(R.id.txt_navHeaderStudent_id);

        ////////////////////////////////////////////////////////////////////////////////////////////
        checkOnBackstackchanges = true;
        ////////////////////////////////////////////////////////////////////////////////////////////

        setNavHeaderText();


        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_student, FragmentStudentProfile.newInstance())
                    .commit();

            navigationView.setCheckedItem(R.id.nav_student_profile);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (manager.getBackStackEntryCount() == 1) {
                navigationView.setCheckedItem(R.id.nav_student_profile);
            }
            super.onBackPressed();
        }
    }

    public void setNavHeaderText() {
        //Recolección de datos de BD

        txt_navHeaderStudent_name.setText("Instituto Tecnológico de Culiacán");
        txt_navHeaderStudent_id.setText("Menú");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_student_profile) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_profile) {

                manager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_student, FragmentStudentProfile.newInstance())
                        .commit();
            }

        } else if (id == R.id.nav_student_requests) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_requests) {
                checkOnBackstackchanges = false;
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                checkOnBackstackchanges = true;


                FragmentStudentRequests frgStudentR = FragmentStudentRequests.newInstance();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container_student, frgStudentR, "StudentR");
                transaction.addToBackStack("addStudentR");
                transaction.commit();

            }

        } else if (id == R.id.nav_student_notifications) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_notifications) {
                checkOnBackstackchanges = false;
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                checkOnBackstackchanges = true;


                FragmentStudentRequestsNotifications frgStudentN = FragmentStudentRequestsNotifications.newInstance();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container_student, frgStudentN, "StudentN");
                transaction.addToBackStack("addStudentN");
                transaction.commit();

            }

        } else if (id == R.id.nav_student_settings) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_settings) {
                checkOnBackstackchanges = false;
                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                checkOnBackstackchanges = true;


                FragmentAllSettings frgStudentSettings = new FragmentAllSettings();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container_student, frgStudentSettings, "StudentSettings");
                transaction.addToBackStack("addStudentSettings");
                transaction.commit();

            }

        } else if (id == R.id.nav_student_logout) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_logout) {
                Data.resetLogindata();
                manager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Intent actLogin = new Intent(this, LoginActivity.class);
                startActivity(actLogin);
                finish();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackStackChanged() {
        if (manager.getBackStackEntryCount() == 0 && checkOnBackstackchanges) {
            navigationView.setCheckedItem(R.id.nav_student_profile);
        }
    }



}
