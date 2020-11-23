package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class StudentRequestDocumentsList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String carNombre;
    private String solId;
    private String solDocUrl;
    private String solKardexUrl;
    private String solLabDocUrl;
    private String solLibDocUrl;

    public StudentRequestDocumentsList(String aluMatricula, String usuNombre, String aluSemestre, String carNombre, String solId, String solDocUrl, String solKardexUrl, String solLabDocUrl, String solLibDocUrl) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.carNombre = carNombre;
        this.solId = solId;
        this.solDocUrl = solDocUrl;
        this.solKardexUrl = solKardexUrl;
        this.solLabDocUrl = solLabDocUrl;
        this.solLibDocUrl = solLibDocUrl;
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

    public String getSolLabDocUrl() {
        return solLabDocUrl;
    }

    public String getSolLibDocUrl() {
        return solLibDocUrl;
    }
}
