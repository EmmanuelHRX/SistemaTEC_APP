package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class CheckedCredentialsList implements Serializable {

    private String cargo;

    public CheckedCredentialsList(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
