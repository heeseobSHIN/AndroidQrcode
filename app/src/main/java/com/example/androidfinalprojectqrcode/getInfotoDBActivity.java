package com.example.androidfinalprojectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class getInfotoDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdb_activity);

        Button userinfo_btn = findViewById(R.id.button2);

        Intent secondIntent = getIntent();
        String addperson = secondIntent.getStringExtra("addperson");
        userinfo_btn.setText(addperson);


        userinfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getInfotoDBActivity.this, showhistory.class);
                startActivity(intent);

            }
        });



        //String GETNUM = getIntent().getStringExtra("INTERVIEW_NUM");


    }

}
