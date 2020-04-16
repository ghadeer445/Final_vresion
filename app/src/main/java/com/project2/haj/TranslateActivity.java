package com.project2.haj;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.google.android.gms.vision.CameraSource;
//import com.google.android.gms.vision.Detector;
//import com.google.android.gms.vision.text.TextBlock;
//import com.google.android.gms.vision.text.TextRecognizer;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.project2.haj.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TranslateActivity extends AppCompatActivity  {


    SurfaceView mCameraView;
    ImageView backImageView;
    ImageView homeImageView;
    TextView mTextView;
    //    TextView resultTextView;
    //    CameraSource mCameraSource;
    private static final String TAG = "MainActivity";
    private static final int requestPermissionID = 101;

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    String result;
    String result_en;
    String result_fr;
    String rule;
    String rule_en;
    String rule_fr;


    Spinner languageSpinner;
    ImageView selectAllButton;
    ImageView clearButton;
    ImageView translateButton;

    String[] languages;
    String selected_lang;


    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);


        languageSpinner = findViewById(R.id.languageSpinner);
        this.imageView = findViewById(R.id.imageView1);
        selectAllButton = findViewById(R.id.selectAllButton);
        clearButton = findViewById(R.id.clearButton);
        mTextView = findViewById(R.id.mTextView);
        translateButton = findViewById(R.id.translateButton);
//        resultTextView = findViewById(R.id.resultTextView);


//        translate("Hello", "ar", this);


        AndroidNetworking.initialize(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        selected_lang = "ar";
        languages = new String[]{
                getString(R.string.arabic),
                getString(R.string.english),
                getString(R.string.french),
                "Turkish",
                "Urdu",
                "China"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.simple_spinner_item_2, languages);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selected_lang = (String) parent.getItemAtPosition(position);
                if (selected_lang.equals(getString(R.string.arabic))) {
                    selected_lang = "ar";
                } else if (selected_lang.equals(getString(R.string.english))) {
                    selected_lang = "en";
                } else {
                    selected_lang = "fr";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }

        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(TranslateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startCameraSource();
                Intent mainIntent = new Intent(getApplicationContext(), ResultActivity.class);
                mainIntent.putExtra("result", result);
                mainIntent.putExtra("result_en", result_en);
                mainIntent.putExtra("result_fr", result_fr);
                mainIntent.putExtra("rule", rule);
                mainIntent.putExtra("rule_en", rule_en);
                mainIntent.putExtra("rule_fr", rule_fr);
                mainIntent.putExtra("selected_lang", selected_lang);
                startActivity(mainIntent);
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.logo2);
            }
        });

        backImageView = findViewById(R.id.backImageView);
        homeImageView = findViewById(R.id.homeImageView);

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

    private void showAlert() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }
                    }
                } else if (items[item].equals("Choose from Gallery")) {
                    openGallery();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            upload();
        }


    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    private void upload() {

        pDialog.setMessage("Please Wait ...");
        showDialog();


        File imageFileOne = new File(getRealPathFromURI(imageUri, this));

        AndroidNetworking.upload(AppConfig.UPLOAD_IMAGE)
                .addMultipartParameter("test", "test")
                .addMultipartFile("pic", imageFileOne)
                .addHeaders("Content-Type", "multipart/form-data")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("***************************");
                            System.out.println("***************************");
                            result = response.getString("result");
                            result_en = response.getString("result_en");
                            result_fr = response.getString("result_fr");
                            rule = response.getString("rule");
                            rule_en = response.getString("rule_en");
                            rule_fr = response.getString("rule_fr");

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error in json obj! " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                        hideDialog();
                    }

                    @Override
                    public void onError(ANError error) {
                        System.out.println("***************************");
                        System.out.println(error.getResponse());
                        System.out.println(error.getErrorBody());
                        System.out.println(error.getErrorDetail());
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error while calling api! " + error.getErrorBody(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                });


    }



    public String getRealPathFromURI(Uri contentURI, Activity context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = context.managedQuery(contentURI, projection, null,
                null, null);
        if (cursor == null)
            return null;
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
            String s = cursor.getString(column_index);

            return s;
        }

        return null;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



//    private void startCameraSource() {
//
//        //Create the TextRecognizer
//        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
//
//        if (!textRecognizer.isOperational()) {
//            Log.w("aa", "Detector dependencies not loaded yet");
//        } else {
//
//            //Initialize camerasource to use high resolution and set Autofocus on.
//            mCameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
//                    .setFacing(CameraSource.CAMERA_FACING_BACK)
//                    .setRequestedPreviewSize(1280, 1024)
//                    .setAutoFocusEnabled(true)
//                    .setRequestedFps(2.0f)
//                    .build();
//
//            /**
//             * Add call back to SurfaceView and check if camera permission is granted.
//             * If permission is granted we can start our cameraSource and pass it to surfaceView
//             */
//            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//                @Override
//                public void surfaceCreated(SurfaceHolder holder) {
//                    try {
//
//                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
//                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//                            ActivityCompat.requestPermissions(TranslateActivity.this,
//                                    new String[]{Manifest.permission.CAMERA},
//                                    requestPermissionID);
//                            return;
//                        }
//                        mCameraSource.start(mCameraView.getHolder());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                }
//
//                /**
//                 * Release resources for cameraSource
//                 */
//                @Override
//                public void surfaceDestroyed(SurfaceHolder holder) {
//                    mCameraSource.stop();
//                }
//            });
//
//            //Set the TextRecognizer's Processor.
//            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
//                @Override
//                public void release() {
//                }
//
//                /**
//                 * Detect all the text from camera using TextBlock and the values into a stringBuilder
//                 * which will then be set to the textView.
//                 * */
//                @Override
//                public void receiveDetections(Detector.Detections<TextBlock> detections) {
//                    final SparseArray<TextBlock> items = detections.getDetectedItems();
//                    if (items.size() != 0) {
//
//                        mTextView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                StringBuilder stringBuilder = new StringBuilder();
//                                for (int i = 0; i < items.size(); i++) {
//                                    TextBlock item = items.valueAt(i);
//                                    stringBuilder.append(item.getValue());
//                                    stringBuilder.append("\n");
//                                }
//                                mTextView.setText(stringBuilder.toString());
//                            }
//                        });
//                    }
//                }
//            });
//        }
//    }
}
