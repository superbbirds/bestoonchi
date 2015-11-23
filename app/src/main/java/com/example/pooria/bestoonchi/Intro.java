package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.renderscript.ScriptIntrinsic;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.TypeVariable;
import java.util.Timer;
import java.util.concurrent.Delayed;

public class Intro extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 3 seconds
                    sleep(SPLASH_TIME_OUT);

                    // After 3 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),help.class);
                    startActivity(i);
                }
                catch (Exception e) {
                }
            }
        };

        // start thread
        background.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}






