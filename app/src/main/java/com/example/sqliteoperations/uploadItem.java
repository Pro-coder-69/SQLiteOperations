package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class uploadItem extends AppCompatActivity {

    EditText name, desc,payment;
    ItemsDB helper;
    ImageView pic;
    Button create, mCamera;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Bitmap finalPhoto;
    private byte[] Image, newImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_item);
        name = (EditText) findViewById(R.id.editText8);
        desc = (EditText) findViewById(R.id.editText9);
        payment = (EditText) findViewById(R.id.editText10);
        pic = (ImageView) findViewById(R.id.image2);
        helper = new ItemsDB(this);

        create = (Button) findViewById(R.id.createbutton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h2 = name.getText().toString();
                String h3 = desc.getText().toString();
                String h4 = payment.getText().toString();

                if (h2.isEmpty()||h3.isEmpty()||h4.isEmpty()) {
                    Message.message(getApplicationContext(), "Enter Data");
                } else {
                    helper.insertData(h2,h3,h4, newImage);
                    Toast toast= Toast.makeText(getApplicationContext(), "data entered", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        mCamera = (Button) findViewById(R.id.uploadbutton);
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "There is no app that support this action", Toast.LENGTH_SHORT).show();
                }
            }
        });
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    finalPhoto = (Bitmap) bundle.get("data");
                    pic.setImageBitmap(finalPhoto);
                }
                try{
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    finalPhoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    newImage = byteArray;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
    public void update( View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String h2 = name.getText().toString();
        String h3 = desc.getText().toString();
        String h4 = payment.getText().toString();

        if (h2.isEmpty()||h3.isEmpty()||h4.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            helper.insertData(h2,h3,h4, newImage);
            Toast toast= Toast.makeText(getApplicationContext(), "data entered", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void accountPageActivity(View view) {
        Intent intent = new Intent(this, accountPageActivity.class);
        startActivity(intent);
    }


}