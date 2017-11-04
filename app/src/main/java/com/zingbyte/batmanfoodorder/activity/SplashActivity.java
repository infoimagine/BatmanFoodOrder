package com.zingbyte.batmanfoodorder.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zingbyte.batmanfoodorder.R;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                //onLoadingDataEnded();
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);
              //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
  /*  private void onLoadingDataEnded() {

        if (false) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            finish();
        }

    }*/


}
