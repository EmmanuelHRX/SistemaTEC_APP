package com.example.sistematec;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class UploadAndDownload {

    private static String pdfRealName;
    private static String pdfUploadName;
    private static String encodedPDF;

    public static String getPdfRealName() {
        return pdfRealName;
    }

    public static String getPdfUploadName() {
        return pdfUploadName;
    }

    public static String getEncodedPDF() {
        return encodedPDF;
    }

    public static String getFileName(Uri uri, FragmentActivity activity) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static void startPDFChooser(Fragment fragment) {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("application/pdf");
        chooseFile = Intent.createChooser(chooseFile, "Selecciona PDF");
        fragment.startActivityForResult(chooseFile, Data.getReqPdf());
    }

    public static void processPDFData(int requestCode, int resultCode, @Nullable Intent data,
                                      FragmentActivity activity) {
        if (requestCode == Data.getReqPdf() && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            pdfRealName = getFileName(path, activity);



            try {
                InputStream inputS = activity.getContentResolver().openInputStream(path);
                byte[] PDFInBytes = new byte[inputS.available()];
                inputS.read(PDFInBytes);
                encodedPDF = Base64.encodeToString(PDFInBytes, Base64.DEFAULT);
                Toast.makeText(activity, "PDF tomado", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Error en InputStream", Toast.LENGTH_SHORT).show();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Error en InputStream", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
