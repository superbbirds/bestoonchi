package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);


        Button btnnext=(Button)findViewById(R.id.button3);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(help2.this,MainActivity.class);
                startActivity(intent);
                help2.this.finish();
            }
        });
    Button btnback=(Button)findViewById(R.id.button4);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(help2.this,help.class);
                startActivity(intent);

            }
        });
    }
}
