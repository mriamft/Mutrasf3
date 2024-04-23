package com.example.mutrasf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login1 extends AppCompatActivity {
    EditText username, password;
    Button register;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.confirmtimebtn);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(login1.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean check = DB.checkPassword(user, pass);
                    if (check==true){
                        Toast.makeText(login1.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                        intent.putExtra( "phoneNumber",user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login1.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}