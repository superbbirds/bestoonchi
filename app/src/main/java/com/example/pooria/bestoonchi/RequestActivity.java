package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        Button btnsubmit=(Button)findViewById(R.id.button6);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RequestActivity.this,MainActivity.class);
                startActivity(intent);
                RequestActivity.this.finish();
            }
        });

    }
}
