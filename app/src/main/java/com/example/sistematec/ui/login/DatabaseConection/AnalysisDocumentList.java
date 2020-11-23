package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class AnalysisDocumentList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String carNombre;
    private String solId;
    private String solAnalisisDocUrl;

    public AnalysisDocumentList(String aluMatricula, String usuNombre, String aluSemestre, String carNombre, String solId, String solAnalisisDocUrl) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.carNombre = carNombre;
        this.solId = solId;
        this.solAnalisisDocUrl = solAnalisisDocUrl;
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

    public String getSolAnalisisDocUrl() {
        return solAnalisisDocUrl;
    }
}
