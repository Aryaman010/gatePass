package com.aryaman.gatepass;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gatepass.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class securityAcceptViewHolder extends RecyclerView.ViewHolder {
    TextView Name,Phone,Reason,Date,Time;
    Button AcceptButton,RejectButton;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("vp2ftztFCMTmCIrrTGZhWb0j27v1");
    public securityAcceptViewHolder(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.Name);
        Phone = itemView.findViewById(R.id.Phone);
        Reason = itemView.findViewById(R.id.Reason);
        Date = itemView.findViewById(R.id.Date);
        Time = itemView.findViewById(R.id.Time);
        AcceptButton = itemView.findViewById(R.id.Accept);
        RejectButton = itemView.findViewById(R.id.Reject);

        AcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("Request Accepted for " + Name.getText());
                myRef.child("visitors").child((String) Name.getText()).child("status").setValue(4);

            };




        });
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage("Request Rejected");
                myRef.child("visitors").child((String) Name.getText()).child("status").setValue(2);
            }
        });

    }
    private void toastMessage(String message) {
        Toast.makeText(itemView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
