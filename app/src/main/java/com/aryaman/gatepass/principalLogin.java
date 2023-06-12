package com.aryaman.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class principalLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button SubmitButton;
    EditText principalUsername, principalPassword;
    TextView result;
    private static final String TAG = "principalLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        principalUsername = (EditText) findViewById(R.id.principalUsername);
        principalPassword = (EditText) findViewById(R.id.principalPassword);
        result = (TextView) findViewById(R.id.textView2);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthStateChange:signed_in"+user.getUid());
                    toastMessage("Successfully Singned in with:" + user.getEmail());
                    switchActivitiesPrincipal_request_view();

                }
                else{
                    Log.d(TAG, "onAuthStateChange:signed_out");
                    toastMessage("Successfully signed out");
                }
            }
        };

        SubmitButton = findViewById(R.id.principalLoginButton);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = principalUsername.getText().toString();
                String password = principalPassword.getText().toString();
                if (!email.equals("") && !password.equals("")){
                    mAuth.signInWithEmailAndPassword(email,password);
                }
                else {
                    toastMessage("You did not fill all the fields");
                }
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    public void switchActivitiesPrincipal_request_view() {
        Intent switchActivityIntent = new Intent(this, principal_request_view.class);
        startActivity(switchActivityIntent);
    }
}