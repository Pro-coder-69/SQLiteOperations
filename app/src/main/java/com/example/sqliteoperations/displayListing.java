package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class displayListing extends AppCompatActivity {
    ItemsDB helper;
    int position = 0;
    TextView textView3, textView4, textView5;
    String title, description, payment, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_listing);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        helper = new ItemsDB(this);
        Intent intent = getIntent();
        position = intent.getIntExtra("page", 0);
        populateListing();
    }

    public void populateListing(){
        ArrayList<String[]> data = helper.getData();
        title = data.get(position)[0];
        textView3.setText(title);
        description = data.get(position)[1];
        textView4.setText(description);
        payment = data.get(position)[2];
        textView5.setText(payment);
        user = data.get(position)[4];
        Message.message(getApplicationContext(), user+"");
    }
}