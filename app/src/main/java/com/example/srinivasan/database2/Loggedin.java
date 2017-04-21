package com.example.srinivasan.database2;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Loggedin extends AppCompatActivity {
    DatabaseHelperTwo myDb;
    public static final int REQUEST_CAPTURE =1;
    ImageView resultPhoto;
    EditText date,time,query;
    Button add;
    byte[] a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        myDb=new DatabaseHelperTwo(this);
        Button click = (Button)findViewById(R.id.take);
        resultPhoto = (ImageView)findViewById(R.id.photo);
        date = (EditText)findViewById(R.id.date);
        time = (EditText)findViewById(R.id.time);
        query = (EditText)findViewById(R.id.query);
        if(!hasCamera()){
            click.setEnabled(false);
        }
        AddData();
    }
    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
    public void launchCamera(View view){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK){
            Bundle extras= data.getExtras();
            Bitmap photo = (Bitmap)extras.get("data");
            a=getBytes(photo);
            resultPhoto.setImageBitmap(photo);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }
    public void showTimePickerDialog(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");
    }
    public void AddData(){
        add =(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean inserted= myDb.addEntry(date.getText().toString(),time.getText().toString(),query.getText().toString(),a);
               if(inserted == true){
                   Intent intent=new Intent(Loggedin.this,Float.class);
                   startActivity(intent);
                   Toast.makeText(getApplicationContext(),"Your feedback has been sent ",Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
