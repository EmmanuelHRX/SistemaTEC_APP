package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class PersonalDataList implements Serializable {

    private String perMatricula;
    private String usuNombre;
    private String perDepId;
    private String depNombre;
    private String usuFotoUrl;


    public PersonalDataList(String perMatricula, String usuNombre, String perDepId, String depNombre, String usuFotoUrl) {
        this.perMatricula = perMatricula;
        this.usuNombre = usuNombre;
        this.perDepId = perDepId;
        this.depNombre = depNombre;
        this.usuFotoUrl = usuFotoUrl;
    }

    public String getPerMatricula() {
        return perMatricula;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public String getPerDepId() {
        return perDepId;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public String getUsuFotoUrl() {
        return usuFotoUrl;
    }
}
