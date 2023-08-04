package com.example.mathappmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText uname,password,confirmpasswrd;
private Button register,aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmpasswrd=findViewById(R.id.cofirmpassword);
        register=findViewById(R.id.register);
        aboutus=findViewById(R.id.aboutus);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkDetails();
            }
        });

    }
    public void checkDetails(){
        String username=uname.getText().toString();
        String pword=password.getText().toString();
        String cpassword=confirmpasswrd.getText().toString();
        if(username.length()==0){
            Toast.makeText(this, "please enter user name", Toast.LENGTH_SHORT).show();
        } else if (pword.length()==0) {
            Toast.makeText(this, "please type password", Toast.LENGTH_SHORT).show();
        } else if (cpassword.length()==0) {
            Toast.makeText(this, "please confirm password", Toast.LENGTH_SHORT).show();
        }else if (!pword.equals(cpassword)){
            Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences preferences=getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("username",username);
            editor.putString("password",pword);
            editor.apply();
            Toast.makeText(this, "registered successfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }

    }
}