package com.demo.touchstone.grammarapicheck;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

/**
 * Created by touchstone on 4/14/2017.
 */

public class MyAlertDialog {

    public static void showAlertDialog(final Context activity, String msg) {
        AlertDialog dialog;
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, R.style.AppTheme_AppBarOverlay);
        } else {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setMessage(msg)
                .setPositiveButton("FINE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        //activity.finish();
                    }
                });
        // Create the MyAlertDialog object and return it
        dialog = builder.create();
        dialog.show();
    }
}
