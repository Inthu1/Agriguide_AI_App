package com.example.agriguideai.Agriconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    HelperClass helperClass;
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    static String activeUserName;
    static String activeUserPhoneno, activeUser, activeUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

                loginUsername = findViewById(R.id.login_username);
                loginPassword = findViewById(R.id.login_password);
                loginButton = findViewById(R.id.login_button);
                signupRedirectText = findViewById(R.id.signupRedirectText);

                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!validateUsername() | !validatePassword()) {
                            Toast.makeText(LoginActivity.this, "Wrong Username or Password!", Toast.LENGTH_SHORT).show();
                        } else {
                            checkUser();
                            activeUserName = loginUsername.getText().toString();
                            activeUserPhoneno = loginPassword.getText().toString();
                        }
                    }
                });

                signupRedirectText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                });

            }

            public Boolean validateUsername() {
                String val = loginUsername.getText().toString();
                if (val.isEmpty()) {
                    loginUsername.setError("Username cannot be empty");
                    return false;
                } else {
                    loginUsername.setError(null);
                    return true;
                }
            }

            public Boolean validatePassword(){
                String val = loginPassword.getText().toString();
                if (val.isEmpty()) {
                    loginPassword.setError("Password cannot be empty");
                    return false;
                } else {
                    loginPassword.setError(null);
                    return true;
                }
            }


            public void checkUser(){
                String userUsername = loginUsername.getText().toString().trim();
                String userPassword = loginPassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()){

                            loginUsername.setError(null);
                            String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(userPassword)) {
                                loginUsername.setError(null);

                                String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                                String emailFromDB = snapshot.child(userUsername).child("user").getValue(String.class);
                                String usernameFromDB = snapshot.child(userUsername).child("phoneno").getValue(String.class);

                                Intent intent;
                                if(Objects.equals(emailFromDB, "Farmer")){
                                    intent = new Intent(LoginActivity.this, agriconnectact.class);
                                }
                                else{
                                    intent = new Intent(LoginActivity.this, WorkerActivity.class);
                                }

                                activeUser=nameFromDB;
                                activeUserType=emailFromDB;
                                //Toast.makeText(LoginActivity.this, activeUser, Toast.LENGTH_SHORT).show();
                                intent.putExtra("name", nameFromDB);
                                intent.putExtra("user", emailFromDB);
                                intent.putExtra("phoneno", usernameFromDB);
                                intent.putExtra("password", passwordFromDB);

                                startActivity(intent);
                            } else {
                                loginPassword.setError("Invalid Credentials");
                                loginPassword.requestFocus();
                            }
                        } else {
                            loginUsername.setError("User does not exist");
                            loginUsername.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            protected void onResume() {
                super.onResume();
                // Clear EditText when activity is resumed
                loginUsername.setText("");
                loginPassword.setText("");
            }
        }