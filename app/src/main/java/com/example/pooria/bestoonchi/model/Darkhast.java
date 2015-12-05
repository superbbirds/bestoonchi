package com.example.pooria.bestoonchi.model;

import android.graphics.Bitmap;

import com.parse.ParseFile;

/**
 * Created by mm on 12/2/2015.
 *
 */
public class Darkhast {

    public    String title;
    public   String gheymat;
    public int photoId;
    public Bitmap photobmp;
    public  ParseFile photoFile;

    public Darkhast(String title, String gheymat, int photoId, Bitmap photobmp) {
        this.title = title;
        this.gheymat = gheymat;
        this.photoId = photoId;
        this.photobmp = photobmp;
    }

    public Darkhast(String title, String gheymat, ParseFile photoFile) {
        this.title = title;
        this.gheymat = gheymat;
        this.photoFile = photoFile;
    }

    public Darkhast(String title, String gheymat, int photoId) {
            this.title = title;
            this.gheymat = gheymat;
            this.photoId = photoId;
        }


}
