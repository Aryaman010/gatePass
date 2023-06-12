package com.aryaman.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;

public class securityGateAcceptance extends AppCompatActivity {
    Button Visitor_entry,PreviousRequest,AcceptedRequest;
    private FirebaseAuth mAuth;
    Button securitySign_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_gate_acceptance);
        mAuth = FirebaseAuth.getInstance();
        Visitor_entry = findViewById(R.id.requestNewVisitor);
        PreviousRequest = findViewById(R.id.viewPreviousRequets);
        AcceptedRequest = findViewById(R.id.checkApprovedRequest);
        securitySign_out = (Button) findViewById(R.id.securitySignoutButton);
        securitySign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesMain();
                toastMessage("Signing Out...");
                mAuth.signOut();
            }
        });
        Visitor_entry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivitiesVisitor();
            }

        });
        PreviousRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesPreviousRequests();
            }
        });
        AcceptedRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesAcceptedRequests();

            }
        });
    }
    public void switchActivitiesMain() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);

    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void switchActivitiesVisitor() {
        Intent switchActivityIntent = new Intent(this, VisitorEntry.class);
        startActivity(switchActivityIntent);
    }

    public void switchActivitiesPreviousRequests() {
        Intent switchActivityIntent = new Intent(this, securityPreviousRequests.class);
        startActivity(switchActivityIntent);
    }
    public void switchActivitiesAcceptedRequests() {
        Intent switchActivityIntent = new Intent(this, securitycheckApprovedRequests.class);
        startActivity(switchActivityIntent);
    }
}