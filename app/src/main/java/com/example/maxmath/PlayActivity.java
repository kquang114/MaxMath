package com.example.maxmath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class PlayActivity extends Activity implements View.OnClickListener,Runnable {

    public static final String FONT_PATH = "font/VNWaltDisney.ttf";
    public static final int TIMER = 3;
    public static final String SCORE = "score";

    Handler handler;

    private SharedPreferences sharedPreferences;

    private int bestscore;
    private int mscore;
    private int timer;
    private TextView tvScore;
    private TextView tvBest;
    private TextView tvTimer;
    private TextView tvQuestions;
    private Button[] answer;
    Random random;
    int num0,num1,ans,right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        random = new Random();
        mscore = 0;
        handler = new Handler();
        findViewById();
        setTypeFace();
        gernerateQues();
        setGameStatus();

    }

    private void setGameStatus() {
        @SuppressLint("StringFormatMatches") String score = getString(R.string.score,mscore);
        tvScore.setText(score);
        sharedPreferences = getSharedPreferences(SCORE, Context.MODE_PRIVATE);
        bestscore = sharedPreferences.getInt(SCORE,0);
        @SuppressLint("StringFormatMatches") String best = getString(R.string.best,bestscore);
        tvBest.setText(best);
    }

    private void gernerateQues() {
        handler.removeCallbacksAndMessages(null);
        timer = TIMER;
        num0 = random.nextInt(10);
        num1 = random.nextInt(10);
        ans = num0 + num1;
        right = random.nextInt(3);
        int[] options = new int[3];

        if(right !=  0){
                if(random.nextBoolean()){
                    options[0] = ans + (1 + random.nextInt(3));
                }
                else {
                    options[0] = ans - (1 + random.nextInt(3));
                }
        }
        else {
            options[right] = ans;
        }

        if(right != 1 ){
            do {
                if(random.nextBoolean()){
                    options[1] = ans + (1 + random.nextInt(3));
                }
                else {
                    options[1] = ans - (1 + random.nextInt(3));
                }
            }
            while ( options[1] == options[0]);
        }
        else{
            options[right] = ans;

        }

        if(right != 2 ){
            if(random.nextBoolean()){
                options[2] = ans + (1 + random.nextInt(3));
            }
            else {
                options[2] = ans - (1 + random.nextInt(3));
            }
        while ( options[2] == options[0] || options[2] == options[1] );
        }
        else{
            options[right] = ans;
        }


//        for(int i = 0 ; i < options.length;i++){
//            if(i == right){
//                options[i] = ans;
//            }
//            else {
//                int other = 1 + random.nextInt(9);
//                if(random.nextBoolean()){
//                    do {
//
//                    }
//                    while ()
//                }
//                else {
//                    if(i % 2 == 1){
//                        options[i] = ans - other;
//                    }
//                    else {
//                        options[i] = ans + other;
//                    }
//                }
//            }
//        }
        String question = num0 + " + " + num1 + " = " + "?";
        tvQuestions.setText(question);
        for(int i = 0 ; i < options.length;i++){
            answer[i].setText(options[i] + "");
        }
        int time = TIMER + 1;
        for(int i = 0 ; i < time ; i++){
            handler.postDelayed(this,i * 1000);
        }
    }

    private void setTypeFace() {

        Typeface typeface = Typeface.createFromAsset(getAssets(),FONT_PATH);
        tvScore.setTypeface(typeface);
        tvBest.setTypeface(typeface);
        tvTimer.setTypeface(typeface);
        tvQuestions.setTypeface(typeface);
        for(int i = 0 ; i < answer.length ; i++){
            answer[i].setTypeface(typeface);
        }

    }


    private void findViewById() {

        tvScore = findViewById(R.id.score);
        tvBest = findViewById(R.id.best);
        tvTimer = findViewById(R.id.timer);
        tvQuestions = findViewById(R.id.questions);

        answer = new Button[3];
        for(int i = 0 ; i < answer.length ; i++){
            answer[i] = findViewById(R.id.answer1 + i);
            answer[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        handler.removeCallbacksAndMessages(null);
        int index = v.getId() - R.id.answer1;
        if(index == right){
            mscore++;
            @SuppressLint("StringFormatMatches") String strScore = getString(R.string.score,mscore);
            tvScore.setText(strScore);
            gernerateQues();
        }
        else{
            goToGameOver();
            finish();
        }
    }

    @Override
    public void run() {
        tvTimer.setText(timer + "");
        timer--;
        if(timer == -1){
            goToGameOver();
        }
    }

    private void goToGameOver() {
        handler.removeCallbacksAndMessages(null);
        if(mscore > bestscore ){
            sharedPreferences.edit().putInt(SCORE,mscore).commit();
        }
        Intent intent = new Intent(this,OverActivity.class);
        intent.putExtra(SCORE,mscore);
        startActivity(intent);
    }
}
