package com.example.srinivasan.database2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SingleView extends AppCompatActivity {
    DatabaseHelperTwo db2;
    String[] date,time,query;
    TextView date1,time1,com1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        db2=new DatabaseHelperTwo(this);
        date1 = (TextView)findViewById(R.id.textdate);
        time1 = (TextView)findViewById(R.id.texttime);
        com1 = (TextView)findViewById(R.id.comm);
        // Get intent data
       Intent i = getIntent();
        // Selected image id
        int position = i.getExtras().getInt("i");
        //ImageAdapter imageAdapter = new ImageAdapter(this);
        ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
        bitmapArray = db2.searchpass();
        date = db2.dat();
        time = db2.tim();
        query = db2.com();


        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
      //  imageView.setImageResource(imageAdapter.mThumbIds[position]);
        imageView.setImageBitmap(bitmapArray.get(position));
        date1.setText(date[position]);
        time1.setText(time[position]);
        com1.setText(query[position]);
    }

}
