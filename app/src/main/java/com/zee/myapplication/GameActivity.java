package com.zee.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView txtViewQuestion;
    TextView txtViewResult;
    TextView txtTime;
    TextView txtScore;
    ImageButton btnCorrect;
    ImageButton btnIncorrect;
    TextView btnQuestion;
    boolean isResultCorrect;
    int counter = 1;
    int seconds = 5;
    private int score = 0;
    private boolean stopTimer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        txtViewQuestion = findViewById(R.id.txtViewQuestions);
        txtViewResult = findViewById(R.id.txtViewResult);
        txtTime = findViewById(R.id.txtTime);
        txtScore = findViewById(R.id.txtScore);
        btnCorrect = findViewById(R.id.btnCorrect);
        btnIncorrect = findViewById(R.id.btnIncorrect);

        btnQuestion = findViewById(R.id.btnQuestion);
        timer();
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });
        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });
        generateQuestion();
    }

    private void generateQuestion() {
        isResultCorrect = true;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int result = a + b;
        float f = random.nextFloat();
        int temp = random.nextInt(100);
        if (f > 0.5f && temp!=result) {
            result = temp;
            isResultCorrect = false;
        }

        txtViewQuestion.setText(a + "+" + b);
        txtViewResult.setText("=" + result);
        btnQuestion.setText("Question "+counter);

    }

    public void verifyAnswer(boolean answer) {
        if (answer == isResultCorrect) {
            score += 5;
            seconds+=2;
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
            score -= 5;
            seconds-=2;
        }
        txtScore.setText("SCORE: " + score);
        counter++;
        generateQuestion();
    }

    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                txtTime.setText("TIME :" + seconds);
                seconds--;
                if (seconds < 0) {
                    Intent i = new Intent(GameActivity.this, ScoreActivity.class);
                    i.putExtra("score", score);
                    i.putExtra("counter",counter);
                    startActivity(i);
                    stopTimer = true;
                }
                if (stopTimer == false) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer = false;
        finish();
    }

}