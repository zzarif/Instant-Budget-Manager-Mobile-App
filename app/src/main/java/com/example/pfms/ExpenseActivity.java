package com.example.pfms;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfms.database.DataBaseHelper;
import com.example.pfms.models.Expense;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExpenseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView tvCurrDate, tvCurrTime;
    EditText etAmount;
    Spinner spCategories;
    Button btnAdd, btnCancel;
    String strCategory;

    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCurrDate =findViewById(R.id.tv_curr_date);
        tvCurrTime = findViewById(R.id.tv_curr_time);
        etAmount = findViewById(R.id.et_amount);
        spCategories = findViewById(R.id.sp_categories);
        btnAdd = findViewById(R.id.btn_save_expense);
        btnCancel = findViewById(R.id.btn_cancel_expense);

        db = new DataBaseHelper(this);

        tvCurrDate.setText(new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date()));
        tvCurrTime.setText(DateFormat.format("hh:mm aaa", Calendar.getInstance().getTime()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategories.setAdapter(adapter);
        spCategories.setOnItemSelectedListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isAllFilledUp = true;

                String amount = etAmount.getText().toString().trim();

                if (TextUtils.isEmpty(amount)) {
                    etAmount.setError("Empty Amount");
                    isAllFilledUp = false;
                }

                if (isAllFilledUp) {

                    etAmount.setText("");

                    Expense expense = new Expense();

                    expense.setAmount(Float.parseFloat(amount));
                    expense.setCategory(strCategory);
                    expense.setDate(tvCurrDate.getText().toString());

                    db.addExpense(expense);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        strCategory = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.bottom_down);
    }
}
