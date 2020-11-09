package com.example.sistematec.ui.login.Student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.FragmentAllSettings;
import com.example.sistematec.ui.login.LoginActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentStudentProfile.OnFragmentInteractionListener,
        FragmentStudentRequests.OnFragmentInteractionListener, FragmentStudentRequestsCapture.OnFragmentInteractionListener,
        FragmentStudentRequestsConfirmation.OnFragmentInteractionListener,
        FragmentStudentRequestsNotifications.OnFragmentInteractionListener,
        FragmentStudentRequestsStatus.OnFragmentInteractionListener{

    // Necessary data
    private String userEmail;
    private String name;
    private String id;
    private String career;
    private String semester;

    //


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

        txt_navHeaderStudent_name = navigationView.getHeaderView(0).findViewById(R.id.txt_navHeaderStudent_name);
        txt_navHeaderStudent_id = navigationView.getHeaderView(0).findViewById(R.id.txt_navHeaderStudent_id);

        ////////////////////////////////////////////////////////////////////////////////////////////

        setNecessaryData();
        setNavHeaderText();


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_student, FragmentStudentProfile.newInstance(name, id, career, semester)).commit();
            navigationView.setCheckedItem(R.id.nav_student_profile);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setNecessaryData() {

        //BD data request
        userEmail = getIntent().getStringExtra("userEmail");
        name = "Juan Emmanuel López Laguna";
        id = "17171372";
        career = "Ing. Sistemas Computacionales";
        semester = "7vo :'c";

        getIntent().putExtra("name", name);
        getIntent().putExtra("id", id);
        getIntent().putExtra("career", career);
        getIntent().putExtra("semester", semester);
    }

    public void setNavHeaderText() {
        //Recolección de datos de BD

        txt_navHeaderStudent_name.setText(name);
        txt_navHeaderStudent_id.setText(userEmail);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_student_profile) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_profile) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_student, FragmentStudentProfile.newInstance(name, this.id, career, semester))
                        .commit();
            }

        } else if (id == R.id.nav_student_requests) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_requests) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_student, FragmentStudentRequests.newInstance(this.id))
                        .commit();
            }

        } else if (id == R.id.nav_student_notifications) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_notifications) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_student, FragmentStudentRequestsNotifications.newInstance())
                        .commit();
            }

        } else if (id == R.id.nav_student_settings) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_settings) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_student, new FragmentAllSettings()).commit();
            }

        } else if (id == R.id.nav_student_logout) {
            if (navigationView.getCheckedItem().getItemId() != R.id.nav_student_logout) {
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
}
