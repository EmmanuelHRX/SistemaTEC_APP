package com.example.sistematec.ui.login.BackgroundTask;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class BackgroundTaks extends IntentService {

    public BackgroundTaks() {

        super("Notify");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int contador = 0;
        while(true){
            try {
                Thread.sleep(1000);
                contador++;
                //cambiar if por lógica de base de datos y elimar la variable contador de esta clase y de stundent
                //si no se mandará ningun parametro de contador.
                if(contador == 1){
                    Intent broadcast = new Intent("broadcast");
                    broadcast.putExtra("trys",contador);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(broadcast);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
