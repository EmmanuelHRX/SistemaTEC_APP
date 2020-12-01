package com.example.sistematec.ui.login.Coordinator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.example.sistematec.Data;
import com.example.sistematec.R;
import com.example.sistematec.ui.login.Login.LoginActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class ActivityCoordinator extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.NavigationView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coordinator, new FragmentCoordinatorProfile()).commit();
            navigationView.setCheckedItem(R.id.nav_coordinator_profile);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){
            case R.id.nav_coordinator_profile: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coordinator, new FragmentCoordinatorProfile()).commit();
                break;
            }
            case R.id.nav_coordinator_request: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coordinator, new FragmentCoordinatorStudentRequest()).commit();
                break;
            }
            case R.id.nav_coordinator_analysis: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coordinator, new FragmentCoordinatorAnalysisRequest()).commit();
                break;
            }
            case R.id.nav_coordinator_conf: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coordinator, new FragmentCoordinatorConRequest()).commit();
                break;
            }
            case R.id.nav_coordinator_logout: {
                Data.resetLogindata();
                Intent actLogin = new Intent(this, LoginActivity.class);
                startActivity(actLogin);
                finish();
                break;
            }

        }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
