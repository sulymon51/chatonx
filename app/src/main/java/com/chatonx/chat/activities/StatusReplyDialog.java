package com.chatonx.chat.activities;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chatonx.chat.apps.R;

/**
 * Created by SMC - Cuuzy on 6/13/2017.
 */

class StatusReplyDialog extends Dialog {
    ImageView emoji;

    StatusReplyDialog(final Context context) {
        super(context, R.style.custom_dialog_theme);
        this.setContentView(R.layout.status_reply_item);
        getWindow().setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
    }

}
