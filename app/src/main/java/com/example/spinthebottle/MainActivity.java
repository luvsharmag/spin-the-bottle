package com.example.spinthebottle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView bottle;
    private Random random = new Random();
    private int lastdir;
    private boolean spinning;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottle = findViewById(R.id.bottle);
        mediaPlayer = MediaPlayer.create(this,R.raw.spinsound);
    }
    public void spinbottle(View v) {
        if (!spinning) {
            int newdir = random.nextInt(1800);
            float pivotx = bottle.getWidth() / 2;
            float pivoty = bottle.getHeight() / 2;
            Animation rotate = new RotateAnimation(lastdir, newdir, pivotx, pivoty);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                    mediaPlayer.start();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                    mediaPlayer.pause();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastdir = newdir;
            bottle.startAnimation(rotate);
        }
    }
}