package com.aryaman.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gatepass.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class principalNewRequest extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("vp2ftztFCMTmCIrrTGZhWb0j27v1");
    List<dataHolder> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_new_request);

        RecyclerView recyclerView = findViewById(R.id.recyclerPrincipalNewEntries);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                items.clear();

                for(DataSnapshot visitors : snapshot.child("visitors").getChildren()){
                    String getfirstName =visitors.getKey();
                    String getPhone = visitors.child("phone").getValue(String.class);
                    String getlastName = "";
                    String getEmail = "";
                    String getDepartment = "";
                    int getStatus =  visitors.child("status").getValue(int.class);
                    String getReason = visitors.child("reason").getValue(String.class);
                    String getDate = visitors.child("requestDate").getValue(String.class);
                    String getTime = visitors.child("requestTime").getValue(String.class);
                    dataHolder obj = new dataHolder(getfirstName,getlastName,getPhone,getEmail,getReason,getDepartment,getDate,getTime,getStatus);
                    if(getStatus == 3){

                        items.add(obj);
                    }

                }
                recyclerView.setAdapter(new principalrecyclerMyAdapter(getApplicationContext(),items));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}