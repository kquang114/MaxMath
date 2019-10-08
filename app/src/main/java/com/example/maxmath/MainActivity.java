package com.example.maxmath;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Type;

public class MainActivity extends Activity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;
    public static final String FONT_PATH = "font/VNWaltDisney.ttf";

    private TextView tvGameName;
    private TextView tvBestScore;
    private Button btnPlay;
    private Button btnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        setTypeFace();

    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(PlayActivity.SCORE, Context.MODE_PRIVATE);

        @SuppressLint("StringFormatMatches") String best = getString(R.string.best_score,sharedPreferences.getInt(PlayActivity.SCORE,0));
        tvBestScore.setText(best);
    }

    private void setTypeFace() {

        Typeface typeface = Typeface.createFromAsset(getAssets(),FONT_PATH);
        tvBestScore.setTypeface(typeface);
        tvGameName.setTypeface(typeface);
        btnPlay.setTypeface(typeface);
        btnMore.setTypeface(typeface);
    }

    private void findViewById() {

        tvGameName = findViewById(R.id.gamename);
        tvBestScore = findViewById(R.id.bestscore);
        btnPlay = findViewById(R.id.btnPlay);
        btnMore = findViewById(R.id.btnMore);

        btnPlay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                clickPlay();
                break;
            case R.id.btnMore:
                clickMore();
                break;
            default:
                break;
        }
    }

    private void clickPlay() {
        Intent intent = new Intent(this,PlayActivity.class);
        startActivity(intent);
    }

    private void clickMore() {
        Intent intent = new Intent(this,MoreActivity.class);
        startActivity(intent);
    }
}
