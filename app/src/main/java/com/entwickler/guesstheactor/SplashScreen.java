package com.entwickler.guesstheactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView splash_screen_title;
    private ImageView splash_screen_image;
    Animation upper_animation, lower_animation;
    AnimationDrawable rocketAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        splash_screen_title = findViewById(R.id.splash_screen_title);
        splash_screen_image = findViewById(R.id.splash_screen_image);

        upper_animation = AnimationUtils.loadAnimation(this,R.anim.upper_animation);
        lower_animation = AnimationUtils.loadAnimation(this,R.anim.lower_animation);

        //splash_screen_image.setAnimation(upper_animation);
        splash_screen_title.setAnimation(lower_animation);

        splash_screen_image.setImageDrawable(getResources().getDrawable(R.drawable.image_animation));
        rocketAnimation = (AnimationDrawable)splash_screen_image.getDrawable();

        rocketAnimation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rocketAnimation.stop();
            }
        },3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MenuActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}