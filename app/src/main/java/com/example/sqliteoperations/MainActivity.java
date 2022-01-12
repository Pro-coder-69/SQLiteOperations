package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//v1.2
public class MainActivity extends AppCompatActivity {
    public static final String USER_KEY = "USER_KEY";
    public static final String PASS_KEY = "PASS_KEY";
    SharedPreferences sharedpreferences;
    EditText Name, Pass , delete, loginName, loginPass;
    private myDbAdapter helper;
    private Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        loginName= (EditText) findViewById(R.id.loginName);
        loginPass= (EditText) findViewById(R.id.loginPass);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new myDbAdapter(this);


        loginbutton = (Button) findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recyclerHost();
                loginUser(v);
            }
        });

    }
    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            long id = helper.insertData(t1,t2);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                Name.setText("");
                Pass.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void loginUser(View view)
    {
        String t1 = loginName.getText().toString();
        String t2 = loginPass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Both Name and Password");
        }
        else
        {
            if(helper.verifyEntry(t1, t2)){
                saveInfo(t1, t2);
                Message.message(getApplicationContext(),"Login Successful");
                accountPageActivity();
            }
            else Message.message(getApplicationContext(),"Try Again");
        }
    }

    public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful");
                delete.setText("");
            }
            else
            {
                Message.message(this, "DELETED");
                delete.setText("");
            }
        }
    }

    private void saveInfo(String user, String pass) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_KEY, user);
        editor.putString(PASS_KEY, pass);
        editor.apply();
    }


    public void accountPageActivity() {
        Intent intent = new Intent(this, accountPageActivity.class);
        startActivity(intent);
    }

   /* public void recyclerHost() {
        Intent intent = new Intent(this, Recycler.class);
        startActivity(intent);
    }*/

}
