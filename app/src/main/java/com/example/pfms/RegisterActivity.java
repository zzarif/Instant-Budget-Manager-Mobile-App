package com.example.pfms;

import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfms.database.DataBaseHelper;
import com.example.pfms.models.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Activity that prompts the user to register
 * @author zarif
 */
public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etEmail,etInitCapital,etBudget,etPassword,etConfirmPassword;

    String username, email, initCapital, budget, password, confirmPassword;
    TextView btnSave, btnCancel;
    DataBaseHelper db;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etUsername = findViewById(R.id.et_set_username);
        etEmail = findViewById(R.id.et_set_email);
        etInitCapital = findViewById(R.id.et_init_capital);
        etBudget = findViewById(R.id.et_budget);
        etPassword = findViewById(R.id.et_set_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);



        db = new DataBaseHelper(this);
        user = new User();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isAllFilledUp = true;

                username = etUsername.getText().toString();
                email = etEmail.getText().toString().trim();
                initCapital = etInitCapital.getText().toString();
                budget = etBudget.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    etUsername.setError("Enter Username");
                    isAllFilledUp = false;
                }
                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Enter Email");
                    isAllFilledUp = false;
                }
                if (TextUtils.isEmpty(initCapital)) {
                    etInitCapital.setError("Enter Initial Capital");
                    isAllFilledUp = false;
                }
                if (TextUtils.isEmpty(budget)) {
                    etBudget.setError("Enter Budget");
                    isAllFilledUp = false;
                }
                if (TextUtils.isEmpty(password)) {
                    etPassword.setError("Enter Password");
                    isAllFilledUp = false;
                }

                if (password.equals(confirmPassword)) {

                    if (isAllFilledUp) {

                        if (Float.parseFloat(budget)>Float.parseFloat(initCapital)) {
                            etBudget.setError("Budget Larger than Initial Capital");
                        }
                        else {
                            showConfirmRegisterDialog();
                        }
                    }

                }
                else {
                    Toast.makeText(RegisterActivity.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * Function that asks user to confirm registration
     */
    private void showConfirmRegisterDialog() {
        AlertDialog dialog;

        dialog = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Registering will clear existing users on this device. Continue?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.setId(1);
                        user.setUsername(username);
                        user.setEmail(email);
                        user.setInitCapital(Float.parseFloat(initCapital));
                        user.setBudget(Float.parseFloat(budget));
                        user.setPassword(password);
                        user.setStartDate("XXX");
                        user.setLoggedIn(1);

                        db.dropUser();
                        db.createUser(user);

                        showStartDialog();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    /**
     * Function that shows a dialog asking user to start the scheme
     *
     */
    private void showStartDialog () {
        AlertDialog dialog;

        dialog = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Do you want to start now?")
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        user.setStartDate(new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date()));

                        Log.d("Date Check", user.getStartDate());

                        db.setUserStartDate(user.getStartDate());

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
