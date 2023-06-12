package com.aryaman.gatepass;

import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.R;

import java.util.List;

public class previousEntriesMyAdapter extends RecyclerView.Adapter<previousEntriesviewHolder> {

    Context context;
    List<dataHolder> items;

    public previousEntriesMyAdapter(Context context, List<dataHolder> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public previousEntriesviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new previousEntriesviewHolder(LayoutInflater.from(context).inflate(R.layout.previousentriesrecycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull previousEntriesviewHolder holder, int position) {

        holder.Name.setText(items.get(position).getFirstName());
        holder.Phone.setText(items.get(position).getPhone());
        holder.Reason.setText(items.get(position).getReason());
        holder.Date.setText(items.get(position).getRequestDate());
        holder.Time.setText(items.get(position).getRequestTime());

        if (items.get(position).getStatus() ==1) {
            holder.Status.setTextColor(Color.YELLOW);
            holder.Status.setText("Approved- Pending Security ");

        } else if (items.get(position).getStatus() ==2) {
            holder.Status.setTextColor(Color.RED);
            holder.Status.setText("Rejected ");

        }
        else if (items.get(position).getStatus() ==0) {
            holder.Status.setTextColor(Color.YELLOW);
            holder.Status.setText("Pending - Department");
        }
        else if (items.get(position).getStatus() ==3) {
            holder.Status.setTextColor(Color.YELLOW);
            holder.Status.setText("Pending - Principal");
        }
        else if (items.get(position).getStatus() == 4){
            holder.Status.setTextColor(Color.GREEN);
            holder.Status.setText("Approved");

        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
