package com.example.androidfinalprojectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class getInfotoDBActivity extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name;
    String withWho;
    String location;
    String datetime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdb_activity);

        Button userinfo_btn = findViewById(R.id.button2);

        Intent secondIntent = getIntent();
        String addperson = secondIntent.getStringExtra("addperson");


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //name = document.getData("name");
                                Log.d("emailauth", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("emailauth", "Error getting documents.", task.getException());
                        }
                    }
                });
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
