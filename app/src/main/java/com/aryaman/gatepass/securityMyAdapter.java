package com.aryaman.gatepass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.R;

import java.util.List;

public class securityMyAdapter extends RecyclerView.Adapter<securityAcceptViewHolder >{

    Context context;
    List<dataHolder> items;

    public securityMyAdapter(Context context, List<dataHolder> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public securityAcceptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new securityAcceptViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull securityAcceptViewHolder holder, int position) {
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
