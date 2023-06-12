package com.aryaman.gatepass;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<viewHolderClass > {

    Context context;
    List<dataHolder> items;

    public MyAdapter(Context context, List<dataHolder> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public viewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderClass(LayoutInflater.from(context).inflate(R.layout.recycleruse,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderClass holder, int position) {

        holder.Name.setText(items.get(position).getFirstName());
        holder.Phone.setText(items.get(position).getPhone());
        holder.Reason.setText(items.get(position).getReason());
        holder.Date.setText(items.get(position).getRequestDate());
        holder.Time.setText(items.get(position).getRequestTime());

    }

    @Override
    public int getItemCount() {

        return this.items.size();
    }
}
