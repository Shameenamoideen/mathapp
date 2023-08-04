package com.example.mathappmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText lusername,lpassword;
    private Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lusername = findViewById(R.id.loginUsername);
        lpassword = findViewById(R.id.loginPassword);
        login = findViewById(R.id.LoginButton);
        register = findViewById(R.id.RegisterButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkdetails();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

            public void checkdetails() {
                String user = lusername.getText().toString();
                String pwrd = lpassword.getText().toString();
                SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                String savedName = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");
                if (savedName.equals(user) && savedPassword.equals(pwrd)) {
                    Toast.makeText(Login.this, "login successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Login.this,startTest.class);
                    startActivity(intent);
                } else if (user.length()==0) {
                    Toast.makeText(this, "please enter user name", Toast.LENGTH_SHORT).show();

                } else if (pwrd.length()==0) {
                    Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "incorrect user name and password", Toast.LENGTH_SHORT).show();
                }

            }

    }

