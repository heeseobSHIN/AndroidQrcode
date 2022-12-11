package com.example.androidfinalprojectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class signUp extends AppCompatActivity {

    private FirebaseAuth mAuth;


    //로그인이 감지되면
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText userName = findViewById(R.id.upname_Text);
        EditText userId = findViewById(R.id.upid_Text);
        EditText userPassword = findViewById(R.id.uppassword_Text);
        EditText userEmail = findViewById(R.id.upemail_Text);
        Button signUp_btn = findViewById(R.id.signup_btn);


        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userName.toString();
                String id = userId.toString();
                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("emailauth", "회원가입 성공!");
                                    Toast.makeText(signUp.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.d("emailauth", "password : " + password);
                                    updateUI(user);
//                                    Intent intent = new Intent(signUp.this, MainActivity.class);
//                                    startActivity(intent);
                                } else {

                                    //Toast.makeText(getContext(),"Registration successful", Toast.LENGTH_SHORT).show();
                                    try {
                                        throw task.getException();
                                    }
                                    // if user enters wrong email.
                                    catch (FirebaseAuthWeakPasswordException weakPassword) {
                                        Log.d("emailauth", "비밀번호는 6자 이상이어야 합니다.");
                                        Toast.makeText(signUp.this, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();

                                        // TODO: take your actions!
                                    }
                                    // if user enters wrong password.
                                    catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                                        Log.d("emailauth", "이메일 형식이 맞지 않습니다.");
                                        Toast.makeText(signUp.this, "이메일 형식이 맞지 않습니다.", Toast.LENGTH_SHORT).show();

                                        // TODO: Take your action
                                    }
                                    catch (FirebaseAuthUserCollisionException existEmail) {
                                        Log.d("emailauth", "이미 가입된 이메일입니다.");
                                        Toast.makeText(signUp.this, "이미 가입된 이메일입니다.", Toast.LENGTH_SHORT).show();

                                        // TODO: Take your action
                                    }
                                    catch (Exception e) {
                                        Log.d("emailauth", "onComplete: " + e.getMessage());
                                    }
                                    // If sign in fails, display a message to the user.
                                    Log.w("emailauth", "회원가입 실패..", task.getException());
                                    updateUI(null);
                                }
                            }
                        });
            }
        });


    }



}
