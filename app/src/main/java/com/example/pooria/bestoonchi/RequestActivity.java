package com.example.pooria.bestoonchi;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestActivity extends AppCompatActivity {
    EditText name_editText;
    EditText tozihat_editText;
    Calendar calendarParse ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        name_editText = (EditText) findViewById(R.id.request_name);
        tozihat_editText = (EditText) findViewById(R.id.request_tozihat);
        findViewById(R.id.pick_date_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Datepicker must be return calender type to using into saveData_Parse method
                show_DatePicker();
            }
        });
        findViewById(R.id.request_pick_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image picker -> pick from camera and any Intent
                openImageIntent();
            }
        });

        //Submit button
        findViewById(R.id.request_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name_editText.getText().toString().isEmpty() || tozihat_editText.getText().toString().isEmpty()
                        ||calendarParse == null||selectedImageUri == null)
                {
                    if (name_editText.getText().toString().isEmpty() || tozihat_editText.getText().toString().isEmpty()) {
                        ToastMe(R.string.error_fieldsEmpty);
                    }
                    if (calendarParse ==null) {
                        ToastMe("Calendar error!!");
                    }

                    if (selectedImageUri == null) {
                        ToastMe("Image Picker not Selected!!");
                    }
                }else{
                    //put data in requestObject
                    saveData_Parse();
                    // back to previous activity
                    navigateToMain();
                    ToastMe("Save Success!!");

                }
            }
        });
    }

    //  save input fields from users to ParseObject and send to Parse server
    private void saveData_Parse() {
        //put data in objcet
        //create object to access parse class
        ParseObject requestObject = new ParseObject(parseConstant.request_Class_Name);

        //Read picked image Uri and return bytes
        byte[] image = get_Image_picked_Bytes();

        // Create the ParseFile
        ParseFile file = new ParseFile("img_" + System.currentTimeMillis() + ".jpg", image);

        // save file in saveInBackground thread
        file.saveInBackground();

        //put data in requestObject
        requestObject.put(parseConstant.request_Field_Name, name_editText.getText().toString());
        requestObject.put(parseConstant.request_Field_Tozihat, tozihat_editText.getText().toString());
        requestObject.put(parseConstant.request_Field_ExpireTime, calendarParse.getTime());
        requestObject.put(parseConstant.request_Field_Picture, file);

        //save & send data to parse in saveInBackground (new thread )
        requestObject.saveInBackground();
    }

    private byte[] get_Image_picked_Bytes() {
        Uri selectedImage = selectedImageUri;
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();

                    //ERROR when recent image picked
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);

        //save image file from outputFileUri


        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    //dataPicker Dialog
    public void show_DatePicker() {

        com.afkar.sundatepicker.DatePickerDialog dp = com.afkar.sundatepicker.DatePickerDialog.newInstance(new com.afkar.sundatepicker.DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(int id, Calendar calendar, int year, int month, int day) {

                //Date date = new Date(calendar.getTimeInMillis());
                // java.util.Date date2 = calendar.getTime();
                //  Toast.makeText(MainActivity.this, "date :"+ date.toString() , Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this, "day " +day+ "month " +month+ "year"+year,Toast.LENGTH_LONG ).show();
                calendar.add(calendar.MONTH, -1);
                calendar.add(calendar.HOUR, 4);
                calendarParse = calendar;
                Toast.makeText(RequestActivity.this, "Calendar :" + calendar.getTime().toString(), Toast.LENGTH_SHORT).show();
            }
        }, 2);
        dp.setYearRange(1394, 1396);
        // dp.setTypeFace(Typeface.createFromAsset(getAssets(), "pFont.ttf"));
        dp.setVibrate(false);
        dp.show(getSupportFragmentManager(), "");
    }

    //navigate to mainActivity
    private void navigateToMain() {
        // Intent intent =new Intent(RequestActivity.this,MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //startActivity(intent);
        RequestActivity.this.finish();
    }

    //Fast Toast method for R.id and String message
    private void ToastMe(String message) {
        Toast.makeText(RequestActivity.this, message, Toast.LENGTH_LONG).show();
    }
    private void ToastMe(int message) {
        Toast.makeText(RequestActivity.this, message, Toast.LENGTH_LONG).show();
    }

    //camera output URI
    private Uri outputFileUri;

    //ImagePicker output URI to using ...
    private Uri selectedImageUri;

    //imagePicker method
    //http://stackoverflow.com/questions/2507898/how-to-pick-an-image-from-gallery-sd-card-for-my-app
    private void openImageIntent() {

// Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = "img_" + System.currentTimeMillis() + ".jpg";
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, 999);
    }

    //imagePicker Result callBack
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 999) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;

                } else {
                    final String action = data.getAction();

                    if (action == null) {

                        isCamera = false;
                    } else {

                        isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                }


                if (isCamera) {
                    selectedImageUri = outputFileUri;
                } else {
                    selectedImageUri = data == null ? null : data.getData();


                }
            }
        }
    }

}
