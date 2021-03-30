package com.entwickler.guesstheactor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner menu_spinner_category,menu_spinner_time;
    private Button menu_start_btn;
    private TextView menu_check_score_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar=findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Guessing Game");

        final String[] category = {"Actors","Cricketers","Logos"};
        final String[] time = {"30 Second","60 Second","90 Second"};

        menu_check_score_txt = findViewById(R.id.menu_check_score_txt);
        menu_spinner_time = findViewById(R.id.menu_spinner_time);
        menu_spinner_category = findViewById(R.id.menu_spinner_category);
        menu_start_btn = findViewById(R.id.menu_start_btn);


        ArrayAdapter ad_category = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        ad_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu_spinner_category.setAdapter(ad_category);

        ArrayAdapter ad_time = new ArrayAdapter(this, android.R.layout.simple_spinner_item, time);
        ad_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menu_spinner_time.setAdapter(ad_time);

        menu_check_score_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,HighScoreActivity.class);
                startActivity(intent);
            }
        });

        menu_start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
                intent.putExtra("category",menu_spinner_category.getSelectedItem().toString());
                intent.putExtra("time",menu_spinner_time.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }
}