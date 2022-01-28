package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class uploadItem extends AppCompatActivity {

    EditText name, desc, url,payment;
    ItemsDB helper;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_item);
        name = (EditText) findViewById(R.id.editText8);
        desc = (EditText) findViewById(R.id.editText9);
        payment = (EditText) findViewById(R.id.editText10);
        //url = (EditText) findViewById(R.id.editText11);
        helper = new ItemsDB(this);

        create = (Button) findViewById(R.id.createbutton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h2 = name.getText().toString();
                String h3 = desc.getText().toString();
                String h4 = payment.getText().toString();
                String h5 = url.getText().toString();
                if (h2.isEmpty()||h3.isEmpty()||h4.isEmpty()||h5.isEmpty()) {
                    Message.message(getApplicationContext(), "Enter Data");
                } else {
                    helper.insertData(h2,h3,h4,h5);
                    Toast toast= Toast.makeText(getApplicationContext(), "data entered", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public void update( View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String h2 = name.getText().toString();
        String h3 = desc.getText().toString();
        String h4 = payment.getText().toString();
        String h5 = url.getText().toString();
        if (h2.isEmpty()||h3.isEmpty()||h4.isEmpty()||h5.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            helper.insertData(h2,h3,h4,h5);
            Toast toast= Toast.makeText(getApplicationContext(), "data entered", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void accountPageActivity(View view) {
        Intent intent = new Intent(this, accountPageActivity.class);
        startActivity(intent);
    }
}