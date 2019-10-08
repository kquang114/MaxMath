package com.example.maxmath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OverActivity extends Activity implements View.OnClickListener{

    public static final String FONT_PATH = "font/VNWaltDisney.ttf";

    private TextView tvGameOver;
    private TextView tvYourScore;
    private Button btnPlayAgain;
    private Button btnMore;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        findViewById();
        setTypeFace();
        int score = getIntent().getExtras().getInt(PlayActivity.SCORE);
        @SuppressLint({"StringFormatInvalid", "LocalSuppress", "StringFormatMatches"}) String strScore = getString(R.string.your_score,score);

        tvYourScore.setText(strScore);
    }

    private void setTypeFace() {

        Typeface typeface = Typeface.createFromAsset(getAssets(),FONT_PATH);
        tvGameOver.setTypeface(typeface);
        tvYourScore.setTypeface(typeface);
        btnPlayAgain.setTypeface(typeface);
        btnMore.setTypeface(typeface);
    }

    private void findViewById() {

        tvGameOver = findViewById(R.id.gameover);
        tvYourScore = findViewById(R.id.yourscore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnMore = findViewById(R.id.more);

        btnPlayAgain.setOnClickListener(this);
        btnMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnPlayAgain:
                clickPlayAgain();
                break;
            case R.id.more:
                clickHome();
                break;
            default:
                break;
        }
    }

    private void clickPlayAgain() {
        Intent intent = new Intent(this,PlayActivity.class);
        startActivity(intent);
        finish();
    }

    private void clickHome() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
