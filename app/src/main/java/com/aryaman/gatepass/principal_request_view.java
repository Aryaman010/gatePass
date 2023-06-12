package com.aryaman.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;

public class principal_request_view extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button principalSignout,principalNewRequest,principalPreviousRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_request_view);
        mAuth = FirebaseAuth.getInstance();
        principalSignout = (Button) findViewById(R.id.principalsignOutButton);
        principalNewRequest=(Button) findViewById(R.id.principalnewRequest);
        principalPreviousRequest = (Button) findViewById(R.id.principalpreviousRequest);
        principalSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesMain();
                toastMessage("Signing Out...");
                mAuth.signOut();
            }
        });
        principalNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesPrincipalNewRequest();
            }
        });
        principalPreviousRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesPrincipalPreviousRequest();
            }
        });

    }
    public void switchActivitiesMain() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);

    }

    public void switchActivitiesPrincipalNewRequest() {
        Intent switchActivityIntent = new Intent(this, principalNewRequest.class);
        startActivity(switchActivityIntent);

    }

    public void switchActivitiesPrincipalPreviousRequest() {
        Intent switchActivityIntent = new Intent(this, principalPreviousRequest.class);
        startActivity(switchActivityIntent);

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}