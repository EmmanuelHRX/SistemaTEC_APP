package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class ConDocumentList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String carNombre;
    private String solId;
    private String solConformidadDocUrl;

    public ConDocumentList(String aluMatricula, String usuNombre, String aluSemestre, String carNombre, String solId, String solConformidadDocUrl) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.carNombre = carNombre;
        this.solId = solId;
        this.solConformidadDocUrl = solConformidadDocUrl;
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

    public String getSolConformidadDocUrl() {
        return solConformidadDocUrl;
    }
}
