package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass , updateold, updatenew, delete, loginName, loginPass;
    myDbAdapter helper;
    private Button loginbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        loginName= (EditText) findViewById(R.id.loginName);
        loginPass= (EditText) findViewById(R.id.loginPass);
        //updateold= (EditText) findViewById(R.id.editText3);
        //updatenew= (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        helper = new myDbAdapter(this);


        loginbutton = (Button) findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerHost();
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



            long id = helper.insertData(t1,t2);
            if(id<=0)
            {
                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                loginName.setText("");
                loginPass.setText("");
            } else
            {
                Message.message(getApplicationContext(),"Insertion Successful");
                loginName.setText("");
                loginPass.setText("");
            }
        }
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }

    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
                Message.message(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
            } else {
                Message.message(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
            }
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


    public void recyclerHost() {
        Intent intent = new Intent(this, Recycler.class);
        startActivity(intent);
    }

}
