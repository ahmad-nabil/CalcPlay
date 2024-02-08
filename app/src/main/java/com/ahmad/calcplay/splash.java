package com.ahmad.calcplay;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.ahmad.calcplay.calculator.calculator;
import com.ahmad.calcplay.userData.login;
import com.ahmad.calcplay.userData.signup;
import com.ahmad.calcplay.videoplayer.videoplayer;

public class splash extends AppCompatActivity {
ImageView first_img,second_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //initialize img to create animate
        first_img=findViewById(R.id.first_img);
        second_img=findViewById(R.id.second_img);
        animate(first_img, 1000, 500, 0); // Swipe up for imageView1
        animate(second_img, 2000, -500, 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(splash.this, login.class));
                finish();
            }
        },5000);
    }
    private void animate(ImageView imageView,long delay,float start_Y,float end_Y){
        imageView.setAlpha(0f);

//create object animator
        ObjectAnimator translationAnimate=ObjectAnimator.ofFloat(imageView,"translationY",start_Y,end_Y);
        ObjectAnimator alphaAnimate=ObjectAnimator.ofFloat(imageView,"alpha",0f,1f);
//create animate set
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(alphaAnimate,translationAnimate);
        animatorSet.setDuration(1000);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.setStartDelay(delay);
            imageView.setVisibility(View.VISIBLE);
animatorSet.start();

    }
}