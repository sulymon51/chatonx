package com.chatonx.chat.helpers.progress;

import android.content.Context;


/**
 * Created by Jacuzzy on 15/03/2016.
 */
public class ProgressDialog {
    static android.app.ProgressDialog pDialog;
    public static void showDialog(Context mContext) {
        pDialog = new android.app.ProgressDialog(mContext);
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }


}
