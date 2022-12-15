package com.example.androidfinalprojectqrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    int nCurrentPermission = 0;
    static  final int PERMISSIONS_REQUEST = 0x0000001;
    private FirebaseAuth mAuth;

    //앱을 시작할 때 로그인 되어 있는지 확인
    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        if(currentUser != null){
            //--??//
            currentUser.reload();
        }
    }

    //로그인이 감지되면
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null){
        Intent intent = new Intent(MainActivity.this, activity_scanner.class);
        startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnChexkPermission();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        EditText emailIn = findViewById(R.id.emailInputText);
        EditText passwordIn = findViewById(R.id.passwordinputText);
        Button SignInBtn = findViewById(R.id.signinBtn);
        Button SignUpBtn = findViewById(R.id.goSignupBtn);


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

                String email = emailIn.getText().toString().trim();
                String password = passwordIn.getText().toString().trim();

                if (email != null && password != null) {

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //추가해야함, android:textStyle="italic"스타일추가
                                    //if (emailIn != null)
                                    if (task.isSuccessful()) {

                                        Log.d("emailauth", "로그인 성공");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        try {
                                            throw task.getException();
                                        } catch (FirebaseAuthInvalidCredentialsException e) {
                                            Toast.makeText(MainActivity.this, "이메일 또는 비밀번호가 잘못되었습니다.",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (FirebaseAuthUserCollisionException e) {
                                            Toast.makeText(MainActivity.this, "사용중인 계정입니다.",
                                                    Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(MainActivity.this, "사용자가 없거나 등록되지 않은 이메일입니다",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                        Log.w("emailauth", "로그인 실패..", task.getException());
                                        Toast.makeText(MainActivity.this, "로그인 실패..",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }
                                }
                            });
                }else{
                    updateUI(null);
                }
            }
        });


//        //현재 사용자 정보 호출
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            Log.d("emailauth", name);
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//        }


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