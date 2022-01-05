package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class accountPageActivity extends AppCompatActivity {

    public EditText updateNew;
    private myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_account_page);
        helper = new myDbAdapter(this);
        super.onCreate(savedInstanceState);
        updateNew = (EditText) findViewById(R.id.editText7);
    }

    public void update( View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String h2 = updateNew.getText().toString();
        if (h2.isEmpty()) {
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = helper.updateName(preferences.getString("USER_KEY", ""), h2);
            if (a <= 0) {
                Message.message(getApplicationContext(), "Unsuccessful");
                updateNew.setText("");
            } else {
                Message.message(getApplicationContext(), "Username Updated!");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("USER_KEY", h2);
                editor.apply();
                updateNew.setText("");
            }
        }
    }

    public void delete_user(View view) {

    }

    public void view_data(View view) {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void mainReyclerActivity(View view) {
        Intent intent = new Intent(this, MainRecyclerActivity.class);
        startActivity(intent);
    }

}