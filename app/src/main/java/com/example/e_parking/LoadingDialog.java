package com.example.e_parking;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import java.util.zip.Inflater;

public class LoadingDialog {

    Activity activity;
    AlertDialog dialog;

    LoadingDialog(Activity myActivity){
        activity = myActivity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    void dissmissDialog(){
        dialog.dismiss();
    }
}
