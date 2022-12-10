package com.example.androidfinalprojectqrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int nCurrentPermission = 0;
    static  final int PERMISSIONS_REQUEST = 0x0000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       OnChexkPermission();


        EditText idIn = findViewById(R.id.idinputText);
        EditText passwordIn = findViewById(R.id.passwordinputText);
        Button SignInBtn = findViewById(R.id.signinBtn);
        Button SignUpBtn = findViewById(R.id.signupBtn);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });

        //if success
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_scanner.class);
                startActivity(intent);
            }
        });


    }

    public void OnChexkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                Toast.makeText(this, "앱 실행을 위해서는 권한을 설정해야 합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST
                        );

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSIONS_REQUEST:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "앱 실행을 위한 권한이 설정 되었습니다.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "권한이 취소 되었습니다.", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}