package com.example.agriguideai.Agriconnect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agriguideai.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editUsername, editPassword;
    AutoCompleteTextView editUser;
    Button saveButton;
    String nameUser, userUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameUser = LoginActivity.activeUser;
        userUser = LoginActivity.activeUserType;
        usernameUser = LoginActivity.activeUserName;
        passwordUser = LoginActivity.activeUserPhoneno;

        String[] languages = getResources().getStringArray(R.array.programming_languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, languages);
        editUser = findViewById(R.id.editUser);
        editUser.setAdapter(arrayAdapter);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);

        editUsername.setEnabled(false);

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isUserChanged() || isPasswordChanged() || isUsernameChanged()){
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUserChanged() {
        if (!userUser.equals(editUser.getText().toString())){
            reference.child(userUser).child("user").setValue(editUser.getText().toString());
            userUser = editUser.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isNameChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isUsernameChanged() {
        if (!usernameUser.equals(editUsername.getText().toString())){
            reference.child(usernameUser).child("username").setValue(editUsername.getText().toString());
            usernameUser = editUsername.getText().toString();
            return true;
        } else {
            return false;
        }
    }


    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){

        Intent intent = getIntent();

//         nameUser = intent.getStringExtra("name");
//         userUser = intent.getStringExtra("user");
//         usernameUser = intent.getStringExtra("username");
//         passwordUser = intent.getStringExtra("password");

        editName.setText(nameUser);
        editUser.setText(userUser, false);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }
}