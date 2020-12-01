package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class CheckedCredentialsList implements Serializable {

    private String ocupacion;

    public CheckedCredentialsList(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }
}
