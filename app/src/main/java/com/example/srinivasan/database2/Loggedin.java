package com.example.srinivasan.database2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.LogRecord;

import static java.util.Locale.*;

public class Loggedin extends AppCompatActivity {
    DatabaseHelperTwo myDb;
    public static final int REQUEST_CAPTURE =1;
    ImageView resultPhoto;
    EditText date,time,query;
    TextView textloc,address1;
    LocationManager locationManager;
    LocationListener listener;
    TrackGPS gps;
    Button add,loc;
    List<Address> addresses;
    private static final int MY_PERMISSION_REQUEST_LOCATION =1 ;
    byte[] a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        myDb = new DatabaseHelperTwo(this);
        final Button click = (Button) findViewById(R.id.take);
        resultPhoto = (ImageView) findViewById(R.id.photo);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        query = (EditText) findViewById(R.id.query);
        textloc = (TextView) findViewById(R.id.textloc);
        address1 =(TextView)findViewById(R.id.address);

        loc = (Button)findViewById(R.id.loc);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //textloc.setText(location.getLongitude() + " " + location.getLatitude());
                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();
                Geocoder geocoder;
                geocoder = new Geocoder(Loggedin.this, getDefault());
                try{
                    addresses = geocoder.getFromLocation(latitude,longitude,1);
                    String address = addresses.get(0).getAddressLine(0);
                    String area= addresses.get(0).getLocality();
                    String city = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalcode = addresses.get(0).getPostalCode();
                    String fulladress = address +","+area+","+city+","+country+","+postalcode;
                    address1.setText(fulladress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        configure_button();

        /*loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Double latitude = 18.944620;
            Double longitude = 72.822278;
            Geocoder geocoder;
                geocoder = new Geocoder(Loggedin.this, getDefault());
                try{
                    addresses = geocoder.getFromLocation(latitude,longitude,1);
                    String address = addresses.get(0).getAddressLine(0);
                    String area= addresses.get(0).getLocality();
                    String city = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalcode = addresses.get(0).getPostalCode();
                    String fulladress = address +","+area+","+city+","+country+","+postalcode;
                    address.setText(fulladress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

            /*    if (ContextCompat.checkSelfPermission(Loggedin.this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Loggedin.this, android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )) {
                        ActivityCompat.requestPermissions(Loggedin.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSION_REQUEST_LOCATION);
                    } else {
                        ActivityCompat.requestPermissions(Loggedin.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSION_REQUEST_LOCATION);
                    }
                } else {
                    LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        textloc.setText(location(location.getLatitude(), location.getLongitude()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Loggedin.this, "Not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
     public void onRequestPermissionsResult(int requestCode,String[] permissions , int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSION_REQUEST_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        if(ContextCompat.checkSelfPermission(Loggedin.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)==
                                PackageManager.PERMISSION_GRANTED){
                            LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            try{
                                textloc.setText(location(location.getLatitude(),location.getLongitude()));
                            }catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(Loggedin.this,"Not found",Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(Loggedin.this,"No permission found",Toast.LENGTH_SHORT).show();

                    }

                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
        */

                if (!hasCamera()) {
                    click.setEnabled(false);
                }
                AddData();

            }

            protected void onDestroy() {
                super.onDestroy();
                gps.stopUsingGPS();
            }
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
@NonNull int[] grantResults) {
        switch (requestCode) {
        case 10:
        configure_button();
        break;
default:
        break;
        }
}

        void configure_button() {
            // first check for permissions
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                            , 10);
                }
                return;
            }
            // this code won'textView execute IF permissions are not allowed, because in the line above there is return statement.
            loc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //noinspection MissingPermission
                    locationManager.requestLocationUpdates("gps", 5000, 0, listener);


                }
            });
        }

           /* private void getLocation() {
                loc = (Button) findViewById(R.id.loc);
                loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String curcity = "";
                        double lat = 0, log = 0;
                        Geocoder geocoder = new Geocoder(Loggedin.this, getDefault());
                        List<Address> addressList;
                        try {
                            addressList = geocoder.getFromLocation(lat, log, 1);
                            if (addressList.size() > 0) {
                                curcity = addressList.get(0).getLocality();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public String location(double lat, double log) {

                String curcity = "";
                Geocoder geocoder = new Geocoder(Loggedin.this, getDefault());
                List<Address> addressList;
                try {
                    addressList = geocoder.getFromLocation(lat, log, 1);
                    if (addressList.size() > 0) {
                        curcity = addressList.get(0).getLocality();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return curcity;
            }*/

            public boolean hasCamera() {
                return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
            }

            public void launchCamera(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CAPTURE);
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap photo = (Bitmap) extras.get("data");
                    a = getBytes(photo);
                    resultPhoto.setImageBitmap(photo);
                }
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void showDatePickerDialog(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }

            public void showTimePickerDialog(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }

            public void AddData() {
                add = (Button) findViewById(R.id.add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean inserted = myDb.addEntry(date.getText().toString(), time.getText().toString(), query.getText().toString(),address1.getText().toString(), a);
                        if (inserted == true) {
                            Intent intent = new Intent(Loggedin.this, Float.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Your feedback has been sent ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data not inserted", Toast.LENGTH_SHORT).show();
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

