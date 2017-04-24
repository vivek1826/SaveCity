package com.example.srinivasan.database2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.editTextStyle;
import static android.R.attr.handle;

public class SingleView extends AppCompatActivity implements View.OnClickListener{
    DatabaseHelperTwo db2;
    String[] date, time, query , locat;
    TextView date1, time1, com1 ,location ,comments,typecom,butcom;
    ProgressBar progressBar;
    FloatingActionButton fab;
    ProgressDialog progress;
    private int progressStatus = 0;
    private TextView textView;
    PopupWindow popupWindow;
    private Handler handler = new Handler();
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String textsave = null;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
        db2 = new DatabaseHelperTwo(this);
        date1 = (TextView) findViewById(R.id.textdate);
        time1 = (TextView) findViewById(R.id.texttime);
        com1 = (TextView) findViewById(R.id.comm);
        location = (TextView)findViewById(R.id.location);
        comments = (TextView)findViewById(R.id.comments);
        typecom = (EditText)findViewById(R.id.typecom);
        butcom = (Button)findViewById(R.id.butcom);
        butcom.setOnClickListener(this);
        // Get intent data
        popupWindow = new PopupWindow(this);
        Intent i = getIntent();
        // Selected image id
        position = i.getExtras().getInt("i");
        //ImageAdapter imageAdapter = new ImageAdapter(this);
        ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
        bitmapArray = db2.searchpass();
        date = db2.dat();
        time = db2.tim();
        query = db2.com();
        locat = db2.locat();
        /*progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress(50);
            }
        });
        */
        sharedpreferences = getSharedPreferences("text",0);
        //SharedPreferences.Editor sedt = sharedpreferences.edit();
        String newtw = sharedpreferences.getString("text","");

        comments.setText(newtw);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        //imageView.setImageResource(imageAdapter.mThumbIds[position]);
        imageView.setImageBitmap(bitmapArray.get(position));
        date1.setText(date[position]);
        time1.setText(time[position]);
        com1.setText(query[position]);
        location.setText(locat[position]);
    }

    /*public void Comment(View v){
        String a;
        a = typecom.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(textsave, a);
        editor.commit();
        comments.append(++i + " . "+ a + "\n\n");

    }
    public void pro(View v) {

        progress = new ProgressDialog(SingleView.this);
        progress.setMax(100);
        progress.setMessage("Work on Progress");
        progress.setTitle("Response for your Query");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progress.getProgress() <= progress
                            .getMax()) {
                        Thread.sleep(100);
                        handle.sendMessage(handle.obtainMessage());
                        if (progress.getProgress() == progress
                                .getMax()) {
                            progress.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress.incrementProgressBy(1);
        }
    }*/

    @Override
    public void onClick(View v) {
        String a;
        a = typecom.getText().toString();
        comments.append("->"+ a + "\n\n");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("text",comments.getText().toString());
        editor.commit();
        typecom.setText("");
    }
}

