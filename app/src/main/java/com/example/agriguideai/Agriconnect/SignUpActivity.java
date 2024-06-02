package com.example.agriguideai.Agriconnect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agriguideai.R;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    EditText signupName, signupUsername, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupName = findViewById(R.id.signup_name);
//        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);
        // get reference to the string array that we just created
        String[] languages = getResources().getStringArray(R.array.programming_languages);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        // get reference to the autocomplete text view
        AutoCompleteTextView autocompleteTV = findViewById(R.id.signup_user);
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String user = autocompleteTV.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                if(name.length()==0 || user.length()==0 || username.length()==0 || password.length()==0){
                    Toast.makeText(SignUpActivity.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        HelperClass helperClass = new HelperClass(name, user, username, password);
                        reference.child(username).setValue(helperClass);
                    }
                    catch (Exception  e){
                        //Toast.makeText(SignUpActivity.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(SignUpActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
