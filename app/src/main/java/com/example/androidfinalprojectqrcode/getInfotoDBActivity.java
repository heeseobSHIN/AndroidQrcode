package com.example.androidfinalprojectqrcode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class getInfotoDBActivity extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference cities = db.collection("users");
    String name;
    String withWho;
    String location;
    String datetime;
    RecyclerView recyclerView;
    GetDbAdapter adapter;
    private ArrayList<ButtonListitem> buttonListitems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdb_activity);
//        setContentView(R.layout.samplegetdb);
//        Log.d("emailauth", "getDBActivity");
//
//        Button button = findViewById(R.id.getBtn);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getInfotoDBActivity.this, showhistory.class);
//                startActivity(intent);
//            }
//        });

        Intent secondIntent = getIntent();
        String addperson = secondIntent.getStringExtra("addperson");
        Intent thirdIntent = getIntent();
        String getTime = secondIntent.getStringExtra("getTime");


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GetDbAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

        /* adapt data */
        buttonListitems = new ArrayList<>();
        for(int i=1;i<=10;i++){
            buttonListitems.add(new ButtonListitem(getTime,ButtonListitem.button,"sdsd"));
            //adapter.addItem(new ButtonListitem(getTime,ButtonListitem.button,"sdsd"));
        }

        //GetDbAdapter.setButtonList(buttonListitems);

        //버튼에 input할 datetime data
        Map<String, Object> data1 = new HashMap<>();
        data1.put("datetime", datetime);
        cities.document("SF").set(data1);
        Log.d("emailauth", "현재 가져온 시각은" + data1);

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
        //userinfo_btn.setText(addperson);




//        userinfo_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getInfotoDBActivity.this, showhistory.class);
//                startActivity(intent);
//
//            }
//        });


    }

}
