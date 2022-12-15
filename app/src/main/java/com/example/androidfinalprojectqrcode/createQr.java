package com.example.androidfinalprojectqrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.common.BitMatrix;
//import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.annotation.Nullable;


public class createQr extends AppCompatActivity {

    private ImageView iv;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createqr_activity);

        iv = (ImageView)findViewById(R.id.imageView);
        text = "https://www.naver.com";

//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try{
//            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            iv.setImageBitmap(bitmap);
//        }catch (Exception e){}
    }
    }

