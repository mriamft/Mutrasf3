package com.example.mutrasf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText phone, username, password;
    Button signup;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        phone = (EditText) findViewById(R.id.phone);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.btnsignup);
        DB = new DBHelper(signup.this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input values
                String phoneNumber = phone.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String name = username.getText().toString().trim();

                // Check if any of the fields are empty
                if (phoneNumber.equals("") || pass.equals("") || name.equals("")) {
                    Toast.makeText(signup.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Attempt to insert user data into the database
                    boolean success = DB.insertData(phoneNumber, pass, name);

                    if (success==true) {
                        // Redirect to dashboard activity
                        Toast.makeText(signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                        intent.putExtra( "phoneNumber",phoneNumber);
                        startActivity(intent);
//                        finish(); // Optional: Close the sign-up activity to prevent going back to it
                    } else {
                        Toast.makeText(signup.this, "Sign-up failed. Phone number already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
