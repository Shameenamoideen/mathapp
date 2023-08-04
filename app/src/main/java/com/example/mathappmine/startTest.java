package com.example.mathappmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startTest extends AppCompatActivity {
private Button starttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        starttest=findViewById(R.id.starTestButton);
        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(startTest.this, levels.class);
                startActivity(intent);
            }
        });
    }
}