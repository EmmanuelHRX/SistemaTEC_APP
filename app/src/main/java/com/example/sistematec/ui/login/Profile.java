package com.example.sistematec.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import com.example.sistematec.R;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtAlumnoNombre, txtAlumnoMatricula, txtAlumnoCarrera, txtAlumnoSemestre;
    private String TIPO_USUARIO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        TIPO_USUARIO = getIntent().getStringExtra("TIPO_USUARIO");


        //TIPO USUARIO MÉTODO SE SELECCIÓN DE DRAWER

        DrawerLayout drawer;
        NavigationView navigationView;
        switch(TIPO_USUARIO){
            case "Al":{
                drawer = findViewById(R.id.drawer_layout_service);
                navigationView = findViewById(R.id.nav_view);
                break;

            }
            case "Se":{
                drawer = findViewById(R.id.drawer_layout_service);
                navigationView = findViewById(R.id.nav_view_service);
                break;
            }
            default:
                drawer = findViewById(R.id.drawer_layout_service);
                navigationView = findViewById(R.id.nav_view);
            break;
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        txtAlumnoNombre = findViewById(R.id.txtAlumnoNombre);
        txtAlumnoMatricula = findViewById(R.id.txtAlumnoMatricula);
        txtAlumnoCarrera = findViewById(R.id.txtAlumnoCarrera);
        txtAlumnoSemestre = findViewById(R.id.txtAlumnoSemestre);

        setData();

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (TIPO_USUARIO){
            case "Al":{
                if (id == R.id.nav_home) {

                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_tools) {
                    //declaración he inicio de una nueva actividad configuracion
                    Intent actSettings = new Intent(this, ServiceActivity.class);
                    startActivity(actSettings);
                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }
                break;
            }

            case "Se" :{
                if (id == R.id.nav_My_Profile) {
                    //ya se encuentra en mi perfil
                } else if (id == R.id.nav_Requests) {

                } else if (id == R.id.nav_Requests_History) {

                } else if (id == R.id.nav_Notifications) {

                } else if (id == R.id.nav_Configuration) {
                    //declaración he inicio de una nueva actividad configuracion
                    Intent actSettings = new Intent(this, ServiceActivity.class);
                    startActivity(actSettings);
                } else if (id == R.id.nav_Close_Session) {

                }
            }
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout_service);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setData() {

        //Lógica de extración de datos de la BD según el tipo de usuario

        //TIPO_USUARIO MÉTODO DE LLENADO


        this.txtAlumnoNombre.setText("Nombre: Román Alejandro Gaspar Atondo");
        this.txtAlumnoMatricula.setText("Matrícula: 17171372");
        this.txtAlumnoCarrera.setText("Carrera: Ing. Sistemas Computacionales");
        this.txtAlumnoSemestre.setText("Semestre: 12vo :'c");


    }



}
