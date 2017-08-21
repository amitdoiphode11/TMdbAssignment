package com.eaglesofttech.tmdbassignment.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ADMIN1 on 23-Dec-16.
 */
public class CustomDialog {
    Activity activity;
    ProgressDialog progressDialog;

    /**
     * @param activity
     */
    public CustomDialog(Activity activity) {
        this.activity = activity;
    }


    /**
     * @param message
     */
    public void showProgressDialog(String message) {
        hideProgressDialog();
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(message);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
            progressDialog = null;
        }
    }

    /**
     * @param view
     * @param message
     * @param length  LENGTH_SHORT = -1
     *                LENGTH_INDEFINITE = -2
     *                LENGTH_LONG = 0
     */
    public void mSnackBar(View view, String message, int length) {
        Snackbar.make(view, message, length).show();
    }

    /**
     * @param message
     */
    public void mToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

}
