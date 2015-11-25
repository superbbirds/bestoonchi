package com.example.pooria.bestoonchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pooria.bestoonchi.Parse.parseConstant;
import com.parse.ParseObject;

import java.util.Date;

public class RequestActivity extends AppCompatActivity {
    EditText name_editText;
    EditText tozihat_editText;
    EditText expireTime_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

         name_editText  = (EditText) findViewById(R.id.request_name);
        tozihat_editText  = (EditText) findViewById(R.id.request_tozihat);
         expireTime_editText  = (EditText) findViewById(R.id.request_expireTime);

        Button btnsubmit=(Button)findViewById(R.id.request_submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create object to access parse class
                ParseObject requestObject = new ParseObject(parseConstant.request_Class_Name);

                //put data in objcet
                requestObject.put(parseConstant.request_Field_Name, "test name");
                requestObject.put(parseConstant.request_Field_Tozihat, "test tozihat");

                //save & send data to parse in backgroud (new thread )
                requestObject.saveInBackground();

                // back to previous activity
                navigateToMain();

            }
        });

    }

    private void navigateToMain() {
       // Intent intent =new Intent(RequestActivity.this,MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
        RequestActivity.this.finish();
    }
}
