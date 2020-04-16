package com.project2.haj;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SOSActivity extends AppCompatActivity {

    ImageView backImageView;
    ImageView homeImageView;


    LinearLayout phone1;
    LinearLayout phone2;
    LinearLayout phone3;
    LinearLayout phone4;
    LinearLayout phone5;
    LinearLayout phone6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        backImageView = findViewById(R.id.backImageView);
        homeImageView = findViewById(R.id.homeImageView);
        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
        phone3 = findViewById(R.id.phone3);
        phone4 = findViewById(R.id.phone4);
        phone5 = findViewById(R.id.phone5);
        phone6 = findViewById(R.id.phone6);


        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("999");
            }
        });
        phone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("998");
            }
        });
        phone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("997");
            }
        });
        phone4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("911");
            }
        });
        phone5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("800430444");
            }
        });
        phone6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone("00966920002814");
            }
        });


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainIntent);
        finish();

    }


    public void callPhone(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
        callIntent.setData(Uri.parse("tel:" + phone));    //this is the phone number calling
        //check permission
        //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
        //the system asks the user to grant approval.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //request permission from user if the app hasn't got the required permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        } else {     //have got permission
            try {
                startActivity(callIntent);  //call activity and make phone call
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "your Activity is not founded", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
