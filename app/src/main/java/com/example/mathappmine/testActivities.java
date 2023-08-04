package com.example.mathappmine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class testActivities extends AppCompatActivity {
private TextView score,qNumber,question,t_timer;
private EditText wAnswer;
private Button tSubmit;
private String levelNumber;
private RelativeLayout rlayout;
// questions for level 0;
 String[] qLevelZero={"2+3","1+2","3*1","10-1","6/6","5*3","4-2","4/2","8*4","7-4"};
//qanswers for level0
 String[] aLevelZero={"5","3","3","9","1","15","2","2","32","3"};
 // questions for level 1;
 String[] qLevel1={"3+4", "9-4", "3*4", "8-4", "12/6", "11-4", "7+2", "4*2", "11+1", "8/2"};
 //anw for level1
 String[] aLevel1={"7", "5", "12", "4", "2", "7", "9", "8", "12", "4"};
 //qstns fr level2
String[] qLevel2={"10+11","10+20","15-13","24/2","20*0","17-5","10+12","13*3","20-19","12*3"};
//anw for level2
String[] aLevel2={"21","30","2","12","0","12","22","39","1","36"};
private boolean isAnswered=false;

 int currentquestion=0;
 int current_score=0;
 private CountDownTimer count_timer;
 private MediaPlayer playApplause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activities);
        score=findViewById(R.id.tscore);
        qNumber=findViewById(R.id.questionNumber);
        question=findViewById(R.id.question);
        wAnswer=findViewById(R.id.answer);
        t_timer=findViewById(R.id.timer);
        tSubmit=findViewById(R.id.tsButton);
        rlayout=findViewById(R.id.Rlayout);
        playApplause=MediaPlayer.create(this,R.raw.applause);
        Bundle bundle=getIntent().getExtras();
        levelNumber=bundle.getString("level");
        if (levelNumber.equals("0")){       //line 46 to 55 do 1st after that if question displayed
            question.setText(qLevelZero[currentquestion]);
            rlayout.setBackgroundResource(R.drawable.tests);

        } else if (levelNumber.equals("1")) {
            question.setText(qLevel1[currentquestion]);
            rlayout.setBackgroundResource(R.drawable.login);
            time();
        }else if(levelNumber.equals("2")){
           question.setText (qLevel2[currentquestion]);
           rlayout.setBackgroundResource(R.drawable.levels);
           time();
        }

      tSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(isAnswered){   //doupt
                  next_question();
              }else{
              settSubmit();
              }


          }
      });


    }

private void settSubmit(){
        String answer=wAnswer.getText().toString();
        if(answer.isEmpty()) {
            Toast.makeText(this, "please type answer", Toast.LENGTH_SHORT).show();
        }else
        {
            isAnswered=true;
            tSubmit.setText("NEXT");
            if(levelNumber.equals("0")){
                if(answer.equals(aLevelZero[currentquestion])){
                    Toast.makeText(this, "correct answer", Toast.LENGTH_SHORT).show();
                    current_score=current_score+1;
                    score.setText("Score " + current_score+ "/10");
                    playApplause.start();
                }else {
                    Toast.makeText(this, "incorrect answer", Toast.LENGTH_SHORT).show();
                }
            }
            else if(levelNumber.equals("1")){
                if(answer.equals(aLevel1[currentquestion])){
                    Toast.makeText(this, "correct answer", Toast.LENGTH_SHORT).show();
                    current_score=current_score+1;
                    score.setText("Score "+current_score+"/10");
                    count_timer.cancel();
                }else {
                    Toast.makeText(this, "incorrect answer", Toast.LENGTH_SHORT).show();
                }
            }
            else if(levelNumber.equals("2")){
                if(answer.equals(aLevel2[currentquestion])){
                    Toast.makeText(this, "correct answer", Toast.LENGTH_SHORT).show();
                    current_score=current_score+1;
                    score.setText("Score "+current_score+"/10");
                    count_timer.cancel();
                }else {
                    Toast.makeText(this, "incorrect answer", Toast.LENGTH_SHORT).show();
                }
            }
        }

}
public void next_question(){

        if(currentquestion<9){
            tSubmit.setText("SUBMIT");
            currentquestion=currentquestion+1;
            qNumber.setText("Question No:" +(currentquestion+1));
            if(levelNumber.equals("0")){
                question.setText(qLevelZero[currentquestion]);
            }else if(levelNumber.equals("1")){
                question.setText(qLevel1[currentquestion]);
                time();
            } else if (levelNumber.equals("2")) {
                question.setText(qLevel2[currentquestion]);
                time();


            }
            wAnswer.setText("");
            isAnswered=false;
        }else{
            SharedPreferences preference_score=getSharedPreferences("scores",MODE_PRIVATE);
            SharedPreferences.Editor Sh_score=preference_score.edit();
            if(levelNumber.equals("0")){
                Sh_score.putString("score_zero","" + current_score);

            }else if(levelNumber.equals("1")){
                Sh_score.putString("score_one","" + current_score);
            }else if(levelNumber.equals("2")){
                Sh_score.putString("score_two","" + current_score);
            }
            Sh_score.apply();
            onBackPressed();
        }
}
public void time(){
        t_timer.setVisibility(View.VISIBLE);
        int interval=0;
        if(levelNumber.equals("1")){
            interval=20000;
        } else if(levelNumber.equals("2")){
        interval=10000;
    }
        count_timer=new CountDownTimer(interval+1000,1000) {
            @Override
            public void onTick(long l) {
                t_timer.setText("" + l/1000);
            }

            @Override
            public void onFinish() {
                t_timer.setText("time's up");
                if(levelNumber.equals("1")){
                Toast.makeText(testActivities.this, "correct answer is" +aLevel1[currentquestion], Toast.LENGTH_SHORT).show();

            } else if (levelNumber.equals("2")) {
                    Toast.makeText(testActivities.this, "correct answer is" +aLevel2[currentquestion], Toast.LENGTH_SHORT).show();
                }
                isAnswered=true;
                tSubmit.setText("NEXT");
                }

            }
                .start();
}
}
