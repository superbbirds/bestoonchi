package com.example.pooria.bestoonchi;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afkar.sundatepicker.*;
import com.example.pooria.bestoonchi.parse.parseConstant;
import com.parse.ParseObject;


import java.util.Calendar;
import java.util.TimeZone;

public class RequestActivity extends AppCompatActivity {
    EditText name_editText;
    EditText tozihat_editText;
    EditText expireTime_editText;
    Calendar calendarParse = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

         name_editText  = (EditText) findViewById(R.id.request_name);
        tozihat_editText  = (EditText) findViewById(R.id.request_tozihat);
        findViewById(R.id.pick_date_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Datepicker must be return calender type to using into saveData_Parse method
               calendarParse = show_DatePicker();
            }
        });


        Button btnsubmit=(Button)findViewById(R.id.request_submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name_editText.getText().toString().isEmpty() || tozihat_editText.getText().toString().isEmpty()) {
                    ToastMe(R.string.error_fieldsEmpty);

                } else {

                    //put data in objcet
                    saveData_Parse();
                    // back to previous activity
                    navigateToMain();
                }


            }


        });

    }


    private void saveData_Parse() {
        //put data in objcet
        //create object to access parse class
        ParseObject requestObject = new ParseObject(parseConstant.request_Class_Name);

        //put data in objcet
        requestObject.put(parseConstant.request_Field_Name, name_editText.getText().toString());
        requestObject.put(parseConstant.request_Field_Tozihat, tozihat_editText.getText().toString());

        requestObject.put(parseConstant.request_Field_ExpireTime, calendarParse.getTime());
        //     requestObject.put(parseConstant.request_Field_Tozihat, Calendar.getInstance());

        //save & send data to parse in backgroud (new thread )
        requestObject.saveInBackground();
    }

    public Calendar show_DatePicker() {
        final Calendar[] tempCalendar = new Calendar[1];
        tempCalendar[0] = Calendar.getInstance();
       com.afkar.sundatepicker. DatePickerDialog dp =  com.afkar.sundatepicker.DatePickerDialog.newInstance(new  com.afkar.sundatepicker.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(int id, Calendar calendar, int year, int month, int day) {

                //Date date = new Date(calendar.getTimeInMillis());
                // java.util.Date date2 = calendar.getTime();
                //  Toast.makeText(MainActivity.this, "date :"+ date.toString() , Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, "day " +day+ "month " +month+ "year"+year,Toast.LENGTH_LONG ).show();
                calendar.add(calendar.MONTH, -1);
                calendar.add(calendar.HOUR, 4);
                tempCalendar[0] = calendar;
                Toast.makeText(RequestActivity.this, "Calendar :" + calendar.getTime().toString(), Toast.LENGTH_SHORT).show();
            }
        }, 2);
        dp.setYearRange(1394, 1396);
       // dp.setTypeFace(Typeface.createFromAsset(getAssets(), "pFont.ttf"));
        dp.setVibrate(false);
        dp.show(getSupportFragmentManager(), "");
        return tempCalendar[0];
    }

    private void navigateToMain() {
       // Intent intent =new Intent(RequestActivity.this,MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
        RequestActivity.this.finish();
    }

    //Fast Toast methode for R.id and String message
    private void ToastMe(String message) {
        Toast.makeText(RequestActivity.this, message, Toast.LENGTH_LONG).show();
    }
    private void ToastMe(int message) {
        Toast.makeText(RequestActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
