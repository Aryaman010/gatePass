package com.aryaman.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;

public class aimlLanding extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button aimlSignout, newRequest,previousRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiml_landing);
        mAuth = FirebaseAuth.getInstance();
        aimlSignout = (Button) findViewById(R.id.aimlsignOutButton);
        newRequest = (Button) findViewById(R.id.newRequest);
        previousRequest = (Button) findViewById(R.id.previousRequest);
        aimlSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesMainActivity();
                toastMessage("Signing Out...");
                mAuth.signOut();
            }
        });
        newRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesAimlNewRequest();
            }
        });

        previousRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivitiesAimlPreviousRequest();
            }
        });

    }



    public void switchActivitiesMainActivity() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);

    }
    public void switchActivitiesAimlNewRequest() {
        Intent switchActivityIntent = new Intent(this, aimlNewRequests.class);
        startActivity(switchActivityIntent);

    }
    public void switchActivitiesAimlPreviousRequest() {
        Intent switchActivityIntent = new Intent(this, aimlPreviousActivity.class);
        startActivity(switchActivityIntent);

    }



    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}