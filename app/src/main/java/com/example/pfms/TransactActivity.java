package com.example.pfms;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfms.database.DataBaseHelper;

/**
 * Activity that provides money transaction utilities
 * @author zarif
 */
public class TransactActivity extends AppCompatActivity {

    EditText etNewCapital, etNewBudget;
    Button btnSave, btnCancel;
//    DataBaseHelper db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transact);

        etNewCapital = findViewById(R.id.et_set_init_capital);
        etNewBudget = findViewById(R.id.et_new_budget);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

//        db = new DataBaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(TransactActivity.this, "Feature Not Available Yet!", Toast.LENGTH_SHORT).show();

//                boolean isAllFilledUp = true;
//
//                String initCapital = etNewCapital.getText().toString();
//                String budget = etNewBudget.getText().toString();
//
//                if (TextUtils.isEmpty(initCapital)) {
//                    etNewCapital.setError("Enter Initial Capital");
//                    isAllFilledUp = false;
//                }
//                if (TextUtils.isEmpty(budget)) {
//                    etNewBudget.setError("Enter Budget");
//                    isAllFilledUp = false;
//                }
//
//
//                if (isAllFilledUp) {
//
//                    if (Float.parseFloat(budget)>Float.parseFloat(initCapital)) {
//                        etNewBudget.setError("Budget Larger than Initial Capital");
//                    } else {
//                        db.setUserNewCapital(Float.parseFloat(initCapital));
//                        db.setUserNewBudget(Float.parseFloat(budget));
//                        Toast.makeText(TransactActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                        onBackPressed();
//                    }
//
//
//                }



            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
