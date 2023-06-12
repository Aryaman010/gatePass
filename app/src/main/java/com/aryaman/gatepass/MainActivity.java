package com.aryaman.gatepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gatepass.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button principalLogin_button,departmentLogin_button,securityLogin_button,Visitor_entry;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        principalLogin_button = findViewById(R.id.principalLogin);
        departmentLogin_button = findViewById(R.id.departmentLogin);
        securityLogin_button = findViewById(R.id.securityLogin);


        principalLogin_button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){

                        switchActivitiesPrincipal();
            }

        });
        departmentLogin_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivitiesDepartment();
            }

        });
        securityLogin_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchActivitiesSecurity();
            }

        });

    }
    public void switchActivitiesPrincipal(){
        Intent switchActivityIntent = new Intent(this, principalLogin.class);
        startActivity(switchActivityIntent);
    }
    public void switchActivitiesDepartment(){
        Intent switchActivityIntent = new Intent(this, departmentLogin.class);
        startActivity(switchActivityIntent);
    }
    public void switchActivitiesSecurity(){
        Intent switchActivityIntent = new Intent(this, securitylogin.class);
        startActivity(switchActivityIntent);
    }

}