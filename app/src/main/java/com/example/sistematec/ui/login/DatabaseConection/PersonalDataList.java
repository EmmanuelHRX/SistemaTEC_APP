package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class PersonalDataList implements Serializable {

    private String perMatricula;
    private String usuNombre;
    private String perDepId;
    private String depNombre;

    public PersonalDataList(String perMatricula, String usuNombre, String perDepId, String depNombre) {
        this.perMatricula = perMatricula;
        this.usuNombre = usuNombre;
        this.perDepId = perDepId;
        this.depNombre = depNombre;
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
}
