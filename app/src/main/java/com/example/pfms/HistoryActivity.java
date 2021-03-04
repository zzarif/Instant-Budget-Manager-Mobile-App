package com.example.pfms;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfms.adapters.DetailsAdapter;
import com.example.pfms.database.DataBaseHelper;
import com.example.pfms.models.Pair;
import com.example.pfms.models.SingleDetail;

import java.util.ArrayList;

/**
 * Activity that shows user history
 * @author zarif
 */
public class HistoryActivity extends AppCompatActivity {

    RecyclerView rvHistory;
    ArrayList<Pair> historyItems;
    LinearLayoutManager linearLayoutManager;
    DataBaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvHistory = findViewById(R.id.rv_history);
        db = new DataBaseHelper(this);

        historyItems = new ArrayList<>();

        for (Pair pair : db.fetchHistory()) {

            historyItems.add(new Pair(pair.getKey(), pair.getValue()));

        }

        DetailsAdapter detailsAdapter = new DetailsAdapter(historyItems);
        linearLayoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(linearLayoutManager);
        rvHistory.setAdapter(detailsAdapter);

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
