package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.renderscript.ScriptIntrinsic;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.TypeVariable;
import java.util.Timer;
import java.util.concurrent.Delayed;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


    }

    @Override
    protected void onStart() {
        super.onStart();

        int i=0;

        while (i<30000) {
            i++;
        }
        if (i == 30000) {
            Toast.makeText(Intro.this,"number300",Toast.LENGTH_SHORT).show();
            Log.i("log", "number300");
            Intent intent=new Intent(Intro.this,help.class);
            startActivity(intent);
            Intro.this.finish();

        }
    }


}






