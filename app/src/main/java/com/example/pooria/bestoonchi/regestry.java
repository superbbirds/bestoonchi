package com.example.pooria.bestoonchi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class regestry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestry);

        Button btnsend=(Button)findViewById(R.id.button5);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(regestry.this,MainActivity.class);
                startActivity(intent);
                regestry.this.finish();

            }
        });

    }
}
