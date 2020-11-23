package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class StudentRequestList implements Serializable {

    private String solId;
    private String aluMatricula;
    private String usuNombre;
    private String solDepId;
    private String solEtapaId;
    private String etapaDescripcion;

    public StudentRequestList(String solId, String aluMatricula, String usuNombre, String solDepId, String solEtapaId, String etapaDescripcion) {
        this.solId = solId;
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.solDepId = solDepId;
        this.solEtapaId = solEtapaId;
        this.etapaDescripcion = etapaDescripcion;
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

    public String getSolDepId() {
        return solDepId;
    }

    public String getSolEtapaId() {
        return solEtapaId;
    }

    public String getEtapaDescripcion() {
        return etapaDescripcion;
    }
}
