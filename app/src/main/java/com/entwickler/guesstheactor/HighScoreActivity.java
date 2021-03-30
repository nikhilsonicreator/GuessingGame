package com.entwickler.guesstheactor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    private TextView high_score_logo_txt, high_score_cricketer_txt, high_score_actor_txt;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        toolbar=findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        setTitle("High Score");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        high_score_logo_txt = findViewById(R.id.high_score_logo_txt);
        high_score_cricketer_txt = findViewById(R.id.high_score_cricketer_txt);
        high_score_actor_txt = findViewById(R.id.high_score_actor_txt);

        SharedPreferences sharedPreferences = getSharedPreferences("High Scores",MODE_PRIVATE);

        String actor = sharedPreferences.getString("High Score Actors","0");
        String cricketer = sharedPreferences.getString("High Score Cricketers","0");
        String logo = sharedPreferences.getString("High Score Logos","0");

        high_score_logo_txt.setText(logo+" Points");
        high_score_cricketer_txt.setText(cricketer+" Points");
        high_score_actor_txt.setText(actor+" Points");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}