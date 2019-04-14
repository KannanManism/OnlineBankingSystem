package com.example.pavee;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowBalance extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView finalResult;
    String Balance="",TAG="Document";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_balance);
        finalResult = findViewById(R.id.balance);

        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        String p=sf.getString("mobile","NA");

        DocumentReference docRef = db.collection("loandb").document(p);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Balance =  document.getData().get("money").toString();

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        finalResult.setText("Balance: " + Balance);
                        Toast.makeText(getApplicationContext(),Balance, Toast.LENGTH_SHORT).show();


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    public void homep(View v) {
        Intent in1 = new Intent(getApplicationContext(), homepage.class);
        startActivity(in1);
    }
}