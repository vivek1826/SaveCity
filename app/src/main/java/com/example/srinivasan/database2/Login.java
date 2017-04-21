package com.example.srinivasan.database2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {
Session s;
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText logname,logpass;
    Button signin,logreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signin = (Button)findViewById(R.id.signin);
        s=new Session(this);

        if (s.loggedIn()){
            Intent intent=new Intent(Login.this,Float.class);
            startActivity(intent);
        }
        logreg = (Button)findViewById(R.id.logreg);
        signin.setOnClickListener(this);
        logreg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == signin){
            logname = (EditText)findViewById(R.id.logname);
            logpass = (EditText)findViewById(R.id.logpass);
            String strname = logname.getText().toString();
            String strpass = logpass.getText().toString();

            String password = helper.searchpass(strname);
            if(strpass.equals(password)){
                s.setLoggedIn(true);
                Intent i = new Intent(Login.this,Navmenu.class);
                i.putExtra("name",strname);
                startActivity(i);
            }
        }
        if(v == logreg){
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
        }
    }
}
