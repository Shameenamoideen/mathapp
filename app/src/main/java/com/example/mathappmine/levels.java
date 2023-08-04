package com.example.mathappmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class levels extends AppCompatActivity {
    private Button level0,level1,level2;
    private TextView score_level0,score_level1,score_level2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        level0 = findViewById(R.id.level0button);
        level1 = findViewById(R.id.level1button);
        level2 = findViewById(R.id.level2button);
        score_level0=findViewById(R.id.Score0);
        score_level1=findViewById(R.id.Score1);
        score_level2=findViewById(R.id.Score2);
        level0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, testActivities.class);
                intent.putExtra("level", "0");
                startActivity(intent);
            }
        });
        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, testActivities.class);
                intent.putExtra("level", "1");
                startActivity(intent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(levels.this, testActivities.class);
                intent.putExtra("level", "2");
                startActivity(intent);
            }
        });
    }

//to get score from different levels use onResume()


    @Override
    protected void onResume() {
        super.onResume();


        SharedPreferences preferences = getSharedPreferences("scores", MODE_PRIVATE);
        String score0 = preferences.getString("score_zero", "");

        String score1 = preferences.getString("score_one", "");

        String score2 = preferences.getString("score_two", "");

        score_level0.setText("Score" + score0);
        score_level1.setText("Score" + score1);
        score_level2.setText("Score" + score2);

    }

}