package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class StudentRequest2FirstDocumentsList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String carNombre;
    private String solId;
    private String solDocUrl;
    private String solKardexUrl;

    public StudentRequest2FirstDocumentsList(String aluMatricula, String usuNombre, String aluSemestre, String carNombre, String solId, String solDocUrl, String solKardexUrl) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.carNombre = carNombre;
        this.solId = solId;
        this.solDocUrl = solDocUrl;
        this.solKardexUrl = solKardexUrl;
    }

    public String getAluMatricula() {
        return aluMatricula;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public String getAluSemestre() {
        return aluSemestre;
    }

    public String getCarNombre() {
        return carNombre;
    }

    public String getSolId() {
        return solId;
    }

    public String getSolDocUrl() {
        return solDocUrl;
    }

    public String getSolKardexUrl() {
        return solKardexUrl;
    }
}
