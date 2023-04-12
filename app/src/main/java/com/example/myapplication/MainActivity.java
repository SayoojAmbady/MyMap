package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnGetLocation,showMaps,btnLocationListner;
    TextView tvLocation;

    LocationManager locationManager;
    String lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat=lng="";

        btnGetLocation=findViewById(R.id.button);
        tvLocation=findViewById(R.id.textView2);
        btnLocationListner=findViewById(R.id.button3);

        showMaps=findViewById(R.id.button);


        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providers = locationManager.getProviders(new Criteria(),false);

        String provider ="";
        for (String pro :providers){
             provider += pro +"\n";
          }
        Toast.makeText(getApplicationContext(),provider,Toast.LENGTH_SHORT).show();

        btnLocationListner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean providerEnabled =locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!providerEnabled){
                    EnableGPS();
                }  else
                    GetLocation();
            }
        });
    }

    private void GetLocation() {
    }

    private void EnableGPS() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            final AlertDialog alertDialog=builder.create();
            alertDialog.show();
    }
}