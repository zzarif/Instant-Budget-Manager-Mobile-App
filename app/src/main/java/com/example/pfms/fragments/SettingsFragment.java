package com.example.pfms.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pfms.EditActivity;
import com.example.pfms.ExpenseActivity;
import com.example.pfms.HistoryActivity;
import com.example.pfms.R;
import com.example.pfms.RegisterActivity;
import com.example.pfms.TransactActivity;
import com.example.pfms.database.DataBaseHelper;

public class SettingsFragment extends Fragment {

    LinearLayout llEdit,llHistory, llLogOut, llExport, llTransact;
    TextView tvName, tvMail;
    DataBaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llEdit = view.findViewById(R.id.ll_edit);
        llHistory = view.findViewById(R.id.ll_history);
        llLogOut = view.findViewById(R.id.ll_log_out);
        llExport = view.findViewById(R.id.ll_export);
        llTransact = view.findViewById(R.id.ll_money_transact);
        tvName = view.findViewById(R.id.tv_username);
        tvMail = view.findViewById(R.id.tv_email);

        db = new DataBaseHelper(getContext());

        tvName.setText(db.fetchUsername());
        tvMail.setText(db.fetchMail());

        llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });

        llExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Feature Not Available Yet!", Toast.LENGTH_SHORT).show();
            }
        });

        llTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TransactActivity.class));
            }
        });

        llLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmLogOutDialog();
            }
        });

        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
            }
        });
    }

    private void showConfirmLogOutDialog() {
        AlertDialog dialog;

        dialog = new AlertDialog.Builder(getContext())
                .setTitle("Are you sure you want to terminate session?")
                .setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.setUserLoggedOut();

                        getActivity().finishAffinity();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
