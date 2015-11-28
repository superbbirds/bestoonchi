package com.example.pooria.bestoonchi;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by mm on 11/25/2015.
 */
public class parseConstant extends Application{
    public static String request_Class_Name = "darkhast";
    public static String request_Field_Name = "name";
    public static String request_Field_Codekharidar = "codekharidar";
    public static String request_Field_CodeDarkhast= "codedarkhast";
    public static String request_Field_Senf = "senf";
    public static String request_Field_Shahr = "shahr";
    public static String request_Field_Tozihat = "tozihat";
    public static String request_Field_Picture = "xae";
    public static String request_Field_ExpireTime = "extime";



    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "1xUbMQYxhB3lwZwjz2BB10FqbbublPFIQ0fynxrc", "GN6TJIQ43Lcli6IrqTOYguO3Zz78dKlYU0uI4uTR");

    }
}

