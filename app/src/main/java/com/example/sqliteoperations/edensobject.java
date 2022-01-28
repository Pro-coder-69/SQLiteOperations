package com.example.sqliteoperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

public class edensobject {
    private byte[] image;
    private String name, desc, pay;

    public Drawable getImage() {
        byte[] b = image;
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        Drawable drw = Drawable.createFromStream(is, "articleImage");
        return drw;
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

    public void setImage(byte[] image) {
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

}
