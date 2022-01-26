package com.example.sqliteoperations;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class edensobject {
    private String image;
    private String name, desc, pay;

    public String getImage() {
        return image;
    }

    public String getNames(){
        return name;
    }

    public String getDesc(){
        return name;
    }

    public String getPay(){
        return pay;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public Drawable getMap(){
        try {
            InputStream is = (InputStream) new URL(image).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            Log.d("check", "check");
            return d;
        } catch (Exception e) {
            Log.d("check", "error");
            return null;

        }
    }
}
