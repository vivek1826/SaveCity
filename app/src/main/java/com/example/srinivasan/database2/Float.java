package com.example.srinivasan.database2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

public class Float extends AppCompatActivity {
    Session s;
    DatabaseHelperTwo databaseHelperTwo;
    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                Intent i = new Intent(Float.this, SingleView.class);
                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

*/
        button2= (Button)findViewById(R.id.button2);
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.Adapter adapter;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

        button=(Button)findViewById(R.id.button);
        databaseHelperTwo=new DatabaseHelperTwo(getApplicationContext());
        //databaseHelperTwo.searchpass();
        s=new Session(this);

        if (!s.loggedIn()){
            logout();
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Float.this,Navmenu.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent=new Intent(Float.this,Loggedin.class);
                 startActivity(intent);
            }
        });
    }
    private void logout(){
        s.setLoggedIn(false);
        finish();
        startActivity(new Intent(Float.this,Login.class));
    }
}
