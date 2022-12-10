package com.example.androidfinalprojectqrcode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class activity_scanner extends AppCompatActivity {

    TextView textView;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    String locationInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Button getLocation_Btn = findViewById(R.id.getLocation_btn);
        EditText addperson = findViewById(R.id.addperson);
        textView = findViewById(R.id.qrcode_text);
        surfaceView = findViewById(R.id.surfaceView);
        barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setRequestedPreviewSize(640, 480).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try {
                    cameraSource.start(surfaceHolder);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if (qrcode.size() != 0){
                    boolean post = textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("where is your location");
                            textView.setText(qrcode.valueAt(0).displayValue);
                            locationInfo = qrcode.valueAt(0).displayValue;
                            addperson.setText("who have you met? (can Null)");
                            finish();
                        }

                    });
                }

            }


        });

        getLocation_Btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (locationInfo != null) {
                    Intent intent = new Intent(activity_scanner.this, getInfotoDBActivity.class);
                    //intent로 면접번호 넘김 accessToken 수정필요
                    //intent.putExtra("INTERVIEW_NUM", ResponseNum);


                    //intent.putExtra("INTERVIEW_NUM", "120512-12511");
                    startActivity(intent);
                }


            }});
    }
}


