package com.project2.haj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    ImageView backImageView;
    ImageView homeImageView;
    String result;
    String result_en;
    String result_fr;
    String rule;
    String rule_en;
    String rule_fr;
    String selected_lang;
    EditText translateEditText;
    EditText ruleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backImageView = findViewById(R.id.backImageView);
        homeImageView = findViewById(R.id.homeImageView);
        translateEditText = findViewById(R.id.translateEditText);
        ruleEditText = findViewById(R.id.ruleEditText);


        Intent in = getIntent();
        result = in.getStringExtra("result");
        result_en = in.getStringExtra("result_en");
        result_fr = in.getStringExtra("result_fr");
        rule = in.getStringExtra("rule");
        rule_en = in.getStringExtra("rule_en");
        rule_fr = in.getStringExtra("rule_fr");
        selected_lang = in.getStringExtra("selected_lang");


        Date date = new Date(); // Gregorian date

        Calendar cl = Calendar.getInstance();
        cl.setTime(date);

        String hijri = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of
            (cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
            hijri = islamyDate.toString().substring(islamyDate.toString().length() - 5, islamyDate.toString().length()).trim();

        }

        if (selected_lang.equals("en")) {
            translateEditText.setText(result_en);
        } else if (selected_lang.equals("fr")) {
            translateEditText.setText(result_fr);
        } else {
            translateEditText.setText(result);
        }


        if (hijri.length() > 0) {
            if (result_en.equals("Start of Muzdalifa")) {
                if (hijri.equals("12-09")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Stay");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Rester");
                    } else {
                        ruleEditText.setText("البقاء");
                    }
                }

            }                                                                                                                        else if (result_en.equals("The end of Mena")) {
                if (hijri.equals("12-09")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Leave to Arafat ");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Partez à Arafat");
                    } else {
                        ruleEditText.setText("ارحل لعرفات");
                    }
                } else if (hijri.equals("12-11")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Leave to kaaba");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Laisser à la kaaba");
                    } else {
                        ruleEditText.setText("غادر إلى الكعبة");
                    }
                }
            } else if (result_en.equals("The end of Arafat")) {
                if (hijri.equals("12-09")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Leave to Muzdalifa");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Départ pour Muzdalifa");
                    } else {
                        ruleEditText.setText("غادر إلى مزدلفة");
                    }
                }
            } else if (result_en.equals("The beginning of Arafat")) {
                if (hijri.equals("12-09")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Stay");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Rester");
                    } else {
                        ruleEditText.setText("البقاء");
                    }
                }
            } else if (result_en.equals("The beginning of Mena")) {
                if (hijri.equals("12-7")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Stay");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Rester");
                    } else {
                        ruleEditText.setText("البقاء");
                    }
                } else if (hijri.equals("12-10")) {
                    if (selected_lang.equals("en")) {
                        ruleEditText.setText("Stay");
                    } else if (selected_lang.equals("fr")) {
                        ruleEditText.setText("Rester");
                    } else {
                        ruleEditText.setText("البقاء");
                    }
                }
            }
        }


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), TranslateActivity.class);
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
        Intent mainIntent = new Intent(getApplicationContext(), TranslateActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
