package com.example.androidfinalprojectqrcode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class activity_scanner extends AppCompatActivity {

    //파이어베이스 연동
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    TextView textView;
    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private BarcodeDetector barcodeDetector;
    String locationInfo;

    //Step1. 현재 시간 가져오기.

    long now = System.currentTimeMillis();
    //Step2. Date 생성하기

    Date date = new Date(now);
    //Step3. 가져오고 싶은 형식으로 가져오기

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    String getTime = sdf.format(date);

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
                            addperson.setHint("who have you met? (can Null)");
                        }

                    });
                }

            }


        });

        getLocation_Btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String Addperson = addperson.getText().toString();
                String locationMe = locationInfo.toString();
                userData("nameinDatabase",locationInfo,addperson.getText().toString(),getTime);
                Log.d("emailauth", locationInfo);
                Log.d("emailauth", getTime);
                Log.d("emailauth", Addperson);

                if (locationInfo != null) {
                    Intent intent = new Intent(activity_scanner.this, getInfotoDBActivity.class);
                    intent.putExtra("addperson", Addperson);
                    startActivity(intent);
                }


            }});
    }


    //값을 파이어베이스 Realtime database로 넘기는 함수
    public void userData(String name, String location, String withWho, String datetime) {
        //여기에서 직접 변수를 만들어서 값을 직접 넣는것도 가능합니다.
        // ex) 갓 태어난 동물만 입력해서 int age=1; 등을 넣는 경우
        //animal.java에서 선언했던 함수.
        userData Userdata = new userData(name,location,withWho,datetime);
        //child는 해당 키 위치로 이동하는 함수입니다.
        //키가 없는데 "zoo"와 name같이 값을 지정한 경우 자동으로 생성합니다.
        databaseReference.child("heeseob").child(name).setValue(Userdata);
    }
}


