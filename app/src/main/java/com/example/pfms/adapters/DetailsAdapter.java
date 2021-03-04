package com.example.pfms.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.*;

import com.example.pfms.R;
import com.example.pfms.models.Pair;
import com.example.pfms.models.SingleDetail;

import java.util.ArrayList;

public class DetailsAdapter extends Adapter<DetailsAdapter.MyViewHolder> {

        ArrayList<Pair> details;

        public static class MyViewHolder extends ViewHolder {

            public TextView tvDate;
            public TextView tvTotalDebit;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvDate = itemView.findViewById(R.id.tv_date);
                tvTotalDebit = itemView.findViewById(R.id.tv_total_debit);
            }
        }
        public DetailsAdapter(ArrayList<Pair> details) {
            this.details = details;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
            return new MyViewHolder(view);
        }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pair currentItem = details.get(position);

        holder.tvDate.setText(currentItem.getKey());
        holder.tvTotalDebit.setText(Float.toString(currentItem.getValue())+" BDT");
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
}

