package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class JobPost extends AppCompatActivity {


    EditText signupName, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        String[] languages = getResources().getStringArray(R.array.job_type);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        AutoCompleteTextView signupUsername = findViewById(R.id.job_type);
        signupUsername.setAdapter(arrayAdapter);

        signupName = findViewById(R.id.plant_name);
        signupPassword = findViewById(R.id.job_location);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
             // reference = database.getReference("jobs/"+LoginActivity.activeUserName);
                reference= database.getReference("jobs");
                String plant_name = signupName.getText().toString();
                String name = LoginActivity.activeUser;
                String phoneno = LoginActivity.activeUserName;
                String job_type = signupUsername.getText().toString();
                String location = signupPassword.getText().toString();

                try {
                    HelperClass helperClass = new HelperClass(name, phoneno, plant_name, job_type, location);
                    reference.child(phoneno).setValue(helperClass);
                }
                catch (Exception  e){
                    Toast.makeText(JobPost.this, "You have posted a job successfully!", Toast.LENGTH_SHORT).show();
                }
                if(plant_name.length()==0 || job_type.length()==0 || location.length()==0){
                    Toast.makeText(JobPost.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(JobPost.this, "You have posted a job successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JobPost.this, agriconnectact.class);
                    startActivity(intent);
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobPost.this, agriconnectact.class);
                startActivity(intent);
            }
        });
    }
}
