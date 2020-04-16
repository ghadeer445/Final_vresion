package com.project2.haj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidnetworking.AndroidNetworking;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    Button sosButton;
    Button translateButton;
    Button exploreButton;

    ImageView homeImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sosButton = findViewById(R.id.sosButton);
        translateButton = findViewById(R.id.translateButton);
        homeImageView = findViewById(R.id.homeImageView);
        exploreButton = findViewById(R.id.exploreButton);


        AndroidNetworking.initialize(getApplicationContext());

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), SOSActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), TranslateActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps"));
                startActivity(intent);

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

    }
}
