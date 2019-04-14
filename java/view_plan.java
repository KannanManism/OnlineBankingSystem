package com.example.pavee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class view_plan extends AppCompatActivity {

    FirebaseFirestore  db = FirebaseFirestore.getInstance();
    CollectionReference users = db.collection("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }

//    db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//            if (task.isSuccessful()) {
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Log.d(TAG, document.getId() + " => " + document.getData());
//                }
//            } else {
//                Log.w(TAG, "Error getting documents.", task.getException());
//            }
//        }
//    });



    public void applylo1(View v) {

        Intent in = new Intent(view_plan.this, homeloan.class);
        startActivity(in);
    }

    public void applylo2(View v) {
        Intent in = new Intent(view_plan.this, homeloan.class);
        startActivity(in);
    }

    public void applylo3(View v) {
        Intent in = new Intent(view_plan.this, educationloan.class);
        startActivity(in);
    }

    public void applylo4(View v) {
        Intent in = new Intent(view_plan.this, propertyloan.class);
        startActivity(in);
    }


    public void homep(View v) {
        Intent in1 = new Intent(view_plan.this, homepage.class);
        startActivity(in1);
    }


}


