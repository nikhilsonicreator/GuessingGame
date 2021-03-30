package com.entwickler.guesstheactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
TextView time_txt, score_txt, ans_txt, final_result_txt;
Button button0,button1,button2,button3,playagain,play;

ArrayList<String> arrayList1= new ArrayList<String>();

LinearLayout linearLayout;

int score =0, total_score =0;
int x, locationofanswer, incorrect, game_time;
int d0=-1,d1=-1,d2=-1,d3=-1;
String time,category, high_score;
String[] quiz_images;
String[] quiz_names;
CountDownTimer2 count_dn;
boolean bb = false;


   public void play_again(){
       score =0;
       total_score =0;
       linearLayout.setVisibility(View.VISIBLE);
       time_txt.setText("30s");
       ans_txt.setText("");
       score_txt.setText("0/0");
       playagain.setVisibility(View.INVISIBLE);
       generate();

       bb=true;

       count_dn = new CountDownTimer2(game_time,1000) {
           @Override
           public void onTick(long millisUntilFinished) {
               time_txt.setText(((int)millisUntilFinished/1000)+"s");
           }

           @Override
           public void onFinish() {
               time_txt.setText("0s");
               ans_txt.setText("");
               linearLayout.setVisibility(View.INVISIBLE);
               playagain.setVisibility(View.VISIBLE);
               final_result_txt.setVisibility(View.VISIBLE);
               bb=false;

               int points = (score * 2) - (total_score - score);
               if (points<0){
                   points=0;
               }

               if (Integer.parseInt(high_score) < points) {

                   SharedPreferences sharedPreferences = getSharedPreferences("High Scores",MODE_PRIVATE);
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.putString("High Score "+category, points+"");
                   editor.apply();
                   high_score=points+"";
                   final_result_txt.setText("New High Score - "+points);
               }
               else {
                   final_result_txt.setText("Points Secured - " +points);
               }

               score_txt.setText(score +"/"+ total_score);

           }
       };

       count_dn.start();

   }

    public void option(View view){
        total_score++;
        if(view.getTag().toString().equals(Integer.toString(locationofanswer))){
            score++;
            ans_txt.setText("Correct!!!");
        }
        else
        {
            ans_txt.setText("Incorrect!!!");
        }
        score_txt.setText(score +"/"+ total_score);
        generate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.linearLayout);
        imageView=findViewById(R.id.imageView);
        time_txt =findViewById(R.id.textView);
        score_txt =findViewById(R.id.textView2);
        ans_txt =findViewById(R.id.textView3);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playagain=findViewById(R.id.play_again);
        final_result_txt = findViewById(R.id.final_result_txt);
        play=findViewById(R.id.play);

        Intent intent = getIntent();
        time = intent.getStringExtra("time");
        category = intent.getStringExtra("category");

        SharedPreferences sharedPreferences = getSharedPreferences("High Scores",MODE_PRIVATE);
        high_score = sharedPreferences.getString("High Score "+category,"0");
        switch (category){
            case "Actors":
                quiz_images = getResources().getStringArray(R.array.actors_images);
                quiz_names = getResources().getStringArray(R.array.actors_names);
                break;
            case "Cricketers":
                quiz_images = getResources().getStringArray(R.array.cricketer_images);
                quiz_names = getResources().getStringArray(R.array.cricketer_names);
                break;
            case "Logos":
                quiz_images = getResources().getStringArray(R.array.logo_images);
                quiz_names = getResources().getStringArray( R.array.logo_names);
                break;
        }

        switch (time){
            case "30 Second":
                game_time = 30000+1000;
                break;
            case "60 Second":
                game_time = 60000+1000;
                break;
            case "90 Second":
                game_time = 90000+1000;
                break;
        }

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_again();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                score_txt.setVisibility(View.VISIBLE);
                time_txt.setVisibility(View.VISIBLE);
                ans_txt.setVisibility(View.VISIBLE);
                play.setVisibility(View.INVISIBLE);
                play_again();
            }
        });

    }

   public void generate(){
        d0=-1;
        d1=-1;
        d2=-1;
        d3=-1;

        int length = quiz_names.length;
        Random rand =new Random();
        x =rand.nextInt(length);

        locationofanswer=rand.nextInt(4);
        for(int i=0;i<4;i++){
            if(i==locationofanswer){
                arrayList1.add(quiz_names[x]);
                String s = quiz_images[x].replace("R.drawable.", "");
                int res_id = getResources().getIdentifier(s , "drawable", getPackageName());
                imageView.setImageResource(res_id);
            }
            else
            {
                incorrect=rand.nextInt(length);
            if(i==0) {
                  while (incorrect == x) {
                  incorrect = rand.nextInt(length);
                  }
                  d0=incorrect;
                  arrayList1.add(quiz_names[incorrect]);
            }
            else if(i==1){
                while (incorrect == x||d0==incorrect) {
                    incorrect = rand.nextInt(length);
                }
                d1=incorrect;
                arrayList1.add(quiz_names[incorrect]);
            }
            else if(i==2){
                while (incorrect == x||d0==incorrect||d1==incorrect) {
                    incorrect = rand.nextInt(length);
                }
                d2=incorrect;
                arrayList1.add(quiz_names[incorrect]);
            }
            else {
                while (incorrect == x||d0==incorrect||d1==incorrect||d2==incorrect) {
                    incorrect = rand.nextInt(length);
                }
                d3=incorrect;
                arrayList1.add(quiz_names[incorrect]);
            }
            }
        }

        button0.setText(arrayList1.get(0));
        button1.setText(arrayList1.get(1));
        button2.setText(arrayList1.get(2));
        button3.setText(arrayList1.get(3));
        arrayList1.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bb)
            count_dn.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (bb)
            count_dn.pause();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
