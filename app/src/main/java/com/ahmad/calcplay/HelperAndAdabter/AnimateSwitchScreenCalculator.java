package com.ahmad.calcplay.HelperAndAdabter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class AnimateSwitchScreenCalculator {
    TextView screenResult,screenOperation;
    ObjectAnimator fade_in,fade_out;


    public AnimateSwitchScreenCalculator(TextView screenResult, TextView screenOperation) {
        this.screenResult = screenResult;
        this.screenOperation = screenOperation;
    }

    public void screenResultOn() {
        fade_in= ObjectAnimator.ofFloat(screenResult,"alpha",0f,1f);
        fade_in.setDuration(2000);
        fade_in.setInterpolator(new DecelerateInterpolator());
        fade_in.start();
        screenResult.setVisibility(View.VISIBLE);
        screenOperation.setVisibility(View.VISIBLE);

    }

    public void screenResultoff() {
        fade_out=ObjectAnimator.ofFloat(screenResult,"alpha",1f,0f);
        fade_out.setDuration(1000);
        fade_out.setInterpolator(new DecelerateInterpolator());
        fade_out.start();
        fade_out.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                screenResult.setVisibility(View.INVISIBLE);
                screenOperation.setVisibility(View.INVISIBLE);

            }
        });
    }

}
