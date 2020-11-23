package com.example.sistematec.ui.login.DatabaseConection;

import java.io.Serializable;

public class CoordNotificationsList implements Serializable {

    private String notId;
    private String menMensaje;

    public CoordNotificationsList(String notId, String menMensaje) {
        this.notId = notId;
        this.menMensaje = menMensaje;
    }

    public String getNotId() {
        return notId;
    }

    public String getMenMensaje() {
        return menMensaje;
    }
}
