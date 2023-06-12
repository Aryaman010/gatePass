package com.aryaman.gatepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class departmentLogin extends AppCompatActivity {
    EditText departmentLoginUsername, departmentLoginPassword;
    Button departmentLogin;
    TextView result;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "departmentLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_login);
        departmentLoginUsername = (EditText) findViewById(R.id.departmentUsername);
        departmentLoginPassword = (EditText) findViewById(R.id.departmentPassword);
        departmentLogin = (Button) findViewById(R.id.departmentLoginButton);
        result = (TextView) findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthStateChange:signed_in"+user.getUid());
                    toastMessage("Successfully Singned in with:" + user.getEmail());
                    switchActivitiesDepartment_request_view();

                }
                else{
                    Log.d(TAG, "onAuthStateChange:signed_out");
                    toastMessage("Successfully signed out");
                }
            }
        };
        departmentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = departmentLoginUsername.getText().toString();
                String password = departmentLoginPassword.getText().toString();
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
    public void switchActivitiesDepartment_request_view() {
        Intent switchActivityIntent = new Intent(this, aimlLanding.class);
        startActivity(switchActivityIntent);
    }
}