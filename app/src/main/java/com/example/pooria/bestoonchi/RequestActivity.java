package com.example.pooria.bestoonchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pooria.bestoonchi.parse.parseConstant;
import com.parse.ParseObject;

import java.util.Calendar;

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



                //put data in objcet
                if (name_editText.getText().toString().isEmpty() || expireTime_editText.getText().toString().isEmpty() || tozihat_editText.getText().toString().isEmpty())
                {
                    Toast.makeText(RequestActivity.this,"error!! some field is Empty",Toast.LENGTH_LONG).show();

                }else {

                    //create object to access parse class
                    ParseObject requestObject = new ParseObject(parseConstant.request_Class_Name);

                    //put data in objcet
                    requestObject.put(parseConstant.request_Field_Name, name_editText);
                    requestObject.put(parseConstant.request_Field_Tozihat, tozihat_editText);
                    Calendar c = Calendar.getInstance();
                    int seconds = c.get(Calendar.SECOND);

               //     requestObject.put(parseConstant.request_Field_Tozihat, Calendar.getInstance());

                    //save & send data to parse in backgroud (new thread )
                    requestObject.saveInBackground();

                }
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
