package com.zee.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    ImageButton btnhome;
    ImageButton btnreplay;
    TextView txtLeaderBoard;
    int lastScore;
    int best1, best2, best3;
    private SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        
        btnhome = findViewById(R.id.btnhome);
        btnreplay = findViewById(R.id.btnreplay);
        
        TextView txtScore = findViewById(R.id.score);
        TextView txtQuestion = findViewById(R.id.question);
        final int score = getIntent().getIntExtra("score", 0);
        int counter = getIntent().getIntExtra("counter", 0);

        txtScore.setText("Current Score   " + score);
        txtQuestion.setText("Question Attempted  " + counter);

       txtLeaderBoard = findViewById(R.id.txtLeadearBoard);


        sh = SharedPreferences.getInstance(getApplicationContext(), "GAMESCORES");
        getSH();
        setScore(score);
        txtLeaderBoard.setText(
                "BEST1: " + best1 + "\n" +
                        "BEST2: " + best2 + "\n" +
                        "BEST3: " + best3);
        
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        
        btnreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setScore(int score){
        if (score > best1) {
            best3 = best2;
            best2 = best1;
            best1 = score;
            sh.saveInteger("best1", best1);
        } else if (score > best2) {
            best3 = best2;
            best2 = score;
            sh.saveInteger("best2", best2);
        } else if (score > best3) {
            best3 = score;
            sh.saveInteger("best3", best3);
        }
    }
    private void getSH() {

        best1 = sh.getInteger("best1", 0);
        best2 = sh.getInteger("best2", 0);
        best3 = sh.getInteger("best3", 0);

    }

}