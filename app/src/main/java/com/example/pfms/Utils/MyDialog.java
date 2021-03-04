package com.example.pfms.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.pfms.R;
import com.example.pfms.database.DataBaseHelper;

public class MyDialog extends Dialog implements View.OnClickListener {

    TextView tvDismiss;
    Activity activity;
    boolean onSuccess;
    DataBaseHelper db;

    public MyDialog(Activity activity, boolean onSuccess) {
        super(activity);
        this.activity = activity;
        this.onSuccess = onSuccess;
        db = new DataBaseHelper(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (onSuccess) {
            setContentView(R.layout.on_success_dialog);
        } else {
            setContentView(R.layout.on_failure_dialog);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvDismiss = findViewById(R.id.tvDismiss);
        tvDismiss.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        db.dropUser();
        activity.finishAffinity();
        dismiss();
    }
}
