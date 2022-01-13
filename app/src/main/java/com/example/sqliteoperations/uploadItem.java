package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class uploadItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_item);
    }



    public void accountPageActivity(View view) {
        Intent intent = new Intent(this, accountPageActivity.class);
        startActivity(intent);
    }
}