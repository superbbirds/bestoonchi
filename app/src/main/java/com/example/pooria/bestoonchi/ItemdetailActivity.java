package com.example.pooria.bestoonchi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooria.bestoonchi.MylistPackage.RVAdapter;
import com.example.pooria.bestoonchi.model.Darkhast;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

public class ItemdetailActivity extends AppCompatActivity {

    TextView requestTitle;
    TextView requestdescripton;
    ParseImageView requestPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail);

         requestTitle = (TextView) findViewById(R.id.request_detail_text);
         requestdescripton = (TextView) findViewById(R.id.request_detail_description);
         requestPhoto = (ParseImageView) findViewById(R.id.request_detail_photo);

        updateDetail();

    }

    private void updateDetail() {
        Darkhast darkhast = RVAdapter.darkhast;
        requestTitle .setText(darkhast.title);
        requestdescripton .setText(darkhast.gheymat);
        ParseFile photoFile = darkhast.photoFile;
        if (photoFile != null) {
            requestPhoto.setParseFile(photoFile);
            requestPhoto.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }


    }
}
