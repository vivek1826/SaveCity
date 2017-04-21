package com.example.srinivasan.database2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText regname,regpass;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        regname = (EditText)findViewById(R.id.regname);
        regpass = (EditText)findViewById(R.id.regpass);
        add = (Button)findViewById(R.id.add);
        AddData();
    }
    public void AddData(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertdata(regname.getText().toString(), regpass.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


