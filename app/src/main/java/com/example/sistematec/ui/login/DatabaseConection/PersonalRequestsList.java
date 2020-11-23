package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class PersonalRequestsList implements Serializable {

    private String solId;
    private String aluMatricula;
    private String usuNombre;

    public PersonalRequestsList(String solId, String aluMatricula, String usuNombre) {
        this.solId = solId;
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
    }

    public String getSolId() {
        return solId;
    }

    public String getAluMatricula() {
        return aluMatricula;
    }

    public String getUsuNombre() {
        return usuNombre;
    }
}
