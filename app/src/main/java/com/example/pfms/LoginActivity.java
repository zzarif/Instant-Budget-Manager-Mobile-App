package com.example.pfms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfms.database.DataBaseHelper;
import com.example.pfms.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Activity that presents a login prompt to the user
 * @author zarif
 */
public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnSignIn;
    TextView tvRegister;

    User user;

    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        tvRegister = findViewById(R.id.tv_register);

        db = new DataBaseHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User();
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());

                if (db.verifyUser(user)) {

                    if (db.hasUserStarted()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        showStartDialog();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    /**
     * Function that shows a dialog asking user to start the scheme
     *
     */
    private void showStartDialog () {
        AlertDialog dialog;

        dialog = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Do you want to start now?")
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        user.setStartDate(new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date()));

                        Log.d("Date Check", user.getStartDate());

                        db.setUserStartDate(user.getStartDate());

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                })
                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.setUserLoggedOut();
                        finishAffinity();
                    }
                })
                .create();
        dialog.show();
    }
}