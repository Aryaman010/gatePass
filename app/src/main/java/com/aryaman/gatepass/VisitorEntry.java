package com.aryaman.gatepass;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gatepass.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Map;

public class VisitorEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "AddToDatabase";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    Button submit_visitor_details;
    EditText fName, LName,phoneNumber,eMail,reasonForEntry;
    TextView result;
    int status;

    String[] departments = {"Artificial Intelligence and Machine Learning","Architecture","Busisness School","Civil","Chemistry" ,"Computer Science","Computerscience and Design","Electrical and Electronics","Electrical and Communication","Information Science","Mathematics","MCA","Mechanical","Physics"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_entry);
        Spinner spin = (Spinner) findViewById(R.id.departmentOptions);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,departments);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        fName = (EditText) findViewById(R.id.visitor_first_name);
        LName = (EditText) findViewById(R.id.visitor_last_name);
        phoneNumber = (EditText) findViewById(R.id.visitor_phonenumber);
        eMail = (EditText) findViewById(R.id.visitor_email);
        reasonForEntry = (EditText) findViewById(R.id.visitor_reason);
        result = (TextView) findViewById(R.id.textView2);
        submit_visitor_details = findViewById(R.id.VisitorSubmit);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("vp2ftztFCMTmCIrrTGZhWb0j27v1");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    Log.d(TAG,"onAuthStateChange:signed_in"+user.getUid());
                    toastMessage("Successfully Signed in with:" + user.getEmail());

                }
                else{
                    Log.d(TAG, "onAuthStateChange:signed_out");
                    toastMessage("Successfully signed out");
                }
            }
        };
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        submit_visitor_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department = (String) spin.getSelectedItem().toString();
                String firstName = fName.getText().toString();
                String lastName = LName.getText().toString().toString();
                String phone = phoneNumber.getText().toString().toString();
                String email = eMail.getText().toString().toString();
                String reason = reasonForEntry.getText().toString();

                Date date = new Date();
                DateFormat setDate  = new SimpleDateFormat("dd/MM/yyyy");
                String requestDate = setDate.format(date);

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
                String requestTime = mdformat.format(calendar.getTime());
                String regex = "\\d{10}";
                status = 0;
                dataHolder obj = new dataHolder(firstName,lastName,phone,email,reason,department,requestDate, requestTime, status);
                Log.d(TAG,"onClick: Attempting to add to database");
                if(firstName.matches("^[a-zA-Z]*") && phone.matches(regex) && !reason.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    myRef.child("visitors").child(firstName).setValue(obj);
                    toastMessage("Request Sent Successfully");

                    finish();

                }
                else{
                    toastMessage("Enter valid details in all the fields");
                }


            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}