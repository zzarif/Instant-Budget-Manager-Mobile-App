package com.example.pfms.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pfms.ExpenseActivity;
import com.example.pfms.R;
import com.example.pfms.database.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    FloatingActionButton btnAddExpense;
    TextView tvBudget, tvTotalDebit, tvRRE, tvCRE, tvSavings, tvTopCat, tvRemarks, tvRemDays, tvSavingsTitle;
    ProgressBar pbProgressBar;
    ImageView ivRemarks;
    DataBaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvBudget = view.findViewById(R.id.tv_budget);
        tvTotalDebit = view.findViewById(R.id.tv_total_debit);
        tvRRE = view.findViewById(R.id.tv_rre);
        tvCRE = view.findViewById(R.id.tv_cre);
        tvSavings = view.findViewById(R.id.tv_savings);
        tvTopCat = view.findViewById(R.id.tv_top_cat);
        tvRemarks = view.findViewById(R.id.tv_remarks);
        tvRemDays = view.findViewById(R.id.tvRemDays);
        pbProgressBar = view.findViewById(R.id.pbProgressBar);
        btnAddExpense = view.findViewById(R.id.btn_add_expense);
        tvSavingsTitle = view.findViewById(R.id.tv_savings_title);
        ivRemarks = view.findViewById(R.id.iv_remarks);

        db = new DataBaseHelper(getContext());
        updateData();



        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ExpenseActivity.class));
                getActivity().overridePendingTransition(R.anim.bottom_up, R.anim.nothing);
            }
        });
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void updateData () {

        tvBudget.setText(String.format("%.2f", db.fetchBudget()));
        tvTotalDebit.setText(String.format("%.2f", db.fetchTotalDebit()));
        tvRRE.setText(String.format("%.2f", db.fetchRRE()));
        tvCRE.setText(String.format("%.2f", db.fetchCRE()));

        tvTopCat.setText((db.fetchTopCategories()!=null)? db.fetchTopCategories().get(0) :"None");
        tvRemarks.setText(db.fetchRemarks()?"Good":"Exceeding");
        tvRemDays.setText(Integer.toString(db.fetchRemainingDays()));

        if (db.fetchExpectedSavings() < 0) {
            tvSavings.setText(String.format("%.1f", -db.fetchExpectedSavings()));
            tvSavingsTitle.setText("Loss");
            tvSavingsTitle.setTextColor(Color.RED);
        }
        else {
            tvSavings.setText(String.format("%.1f", db.fetchExpectedSavings()));
            tvSavingsTitle.setText("Savings");
            tvSavingsTitle.setTextColor(Color.BLACK);
        }

        if (db.fetchRemarks()) {
            tvRemarks.setText("Good");
            ivRemarks.setImageResource(R.drawable.ic_baseline_check_24);
        }
        else {
            tvRemarks.setText("Exceeding");
            ivRemarks.setImageResource(R.drawable.ic_baseline_error_24);
        }


//        pbProgressBar.setProgress((int)((db.fetchRemainingDays()/30)*100));
    }


    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }
}
