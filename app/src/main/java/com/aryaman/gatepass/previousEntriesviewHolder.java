package com.aryaman.gatepass;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.R;

public class previousEntriesviewHolder extends RecyclerView.ViewHolder {

    TextView Name,Phone,Reason,Date,Time,Status;

    public previousEntriesviewHolder(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.Name);
        Phone = itemView.findViewById(R.id.Phone);
        Reason = itemView.findViewById(R.id.Reason);
        Date = itemView.findViewById(R.id.Date);
        Time = itemView.findViewById(R.id.Time);
        Status = itemView.findViewById(R.id.Status);



    }
}
