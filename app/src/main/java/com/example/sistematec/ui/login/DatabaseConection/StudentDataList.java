package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class StudentDataList implements Serializable {

    private String aluMatricula;
    private String usuNombre;
    private String aluSemestre;
    private String aluCarId;
    private String carNombre;
    private String depId;
    private String depNombre;

    public StudentDataList(String aluMatricula, String usuNombre, String aluSemestre, String aluCarId, String carNombre, String depId, String depNombre) {
        this.aluMatricula = aluMatricula;
        this.usuNombre = usuNombre;
        this.aluSemestre = aluSemestre;
        this.aluCarId = aluCarId;
        this.carNombre = carNombre;
        this.depId = depId;
        this.depNombre = depNombre;
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

    public String getAluCarId() {
        return aluCarId;
    }

    public String getCarNombre() {
        return carNombre;
    }

    public String getDepId() {
        return depId;
    }

    public String getDepNombre() {
        return depNombre;
    }
}
