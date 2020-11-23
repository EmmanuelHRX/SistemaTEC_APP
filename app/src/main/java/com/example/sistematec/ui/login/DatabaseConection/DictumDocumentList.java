package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class DictumDocumentList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String carNombre;
    private String solId;
    private String solDictamenDocUrl;

    public DictumDocumentList(String aluMatricula, String usuNombre, String aluSemestre, String carNombre, String solId, String solDictamenDocUrl) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.carNombre = carNombre;
        this.solId = solId;
        this.solDictamenDocUrl = solDictamenDocUrl;
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

    public String getSolDictamenDocUrl() {
        return solDictamenDocUrl;
    }
}
