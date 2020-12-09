package com.example.sistematec.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.widget.Toast;


import dmax.dialog.SpotsDialog;

public class Dialog {

    public static AlertDialog dialog;
    public static AlertDialog confirmDialog;
    private static boolean dialogSelection;

    public static void showLoadingDialog(Activity activity) {
        String msg = "Cargando";
        showDialog(activity, msg);
    }

    public static void showDialog(Activity activity, String msg) {
        dialog = new SpotsDialog.Builder()
                .setContext(activity)
                .setMessage(msg)
                .setCancelable(false)
                .build();
        dialog.show();
    }
    public static boolean loadingDialogIsShowing() {
        if (dialog == null) {
            return false;
        }
        return dialog.isShowing();
    }

    public static void hideDialog() {
        if (dialog == null){
            return;
        }

        dialog.dismiss();
        dialog = null;
    }

    public static boolean showConfirmDialog(final Activity activity, DialogInterface.OnClickListener okListener,
                                            DialogInterface.OnClickListener noListener) {
        dialogSelection = false;
        confirmDialog = new AlertDialog
                .Builder(activity)
                .setTitle("¿Desea continuar?")
                .setPositiveButton("Sí", okListener)
                .setNegativeButton("No", noListener)
                .create();
        confirmDialog.show();

        return dialogSelection;

    }

    public static void hideOptionDialog() {
        if (confirmDialog == null) {
            return;
        }
    }

}
