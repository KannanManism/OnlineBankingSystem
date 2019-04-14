package com.example.pavee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class cancel_loan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_loan);
        Toast.makeText(getApplicationContext(),"PLEASE VISIT THE NEAREST BRANCH FOR FURTHER AUTHENTICATION",Toast.LENGTH_SHORT).show();
//NEXT SPRINT MAKE THIS CANCEL PAGE TO REDIRECT TO MONEY TRANSFER PAGE WHERE THE MONEY (LOAN AMOIUNT+_ INTEREST) (IF LESS THAN 20 LAKHS ,,,IF >20L..ONLY CHEQUE) SHOULD BE PAID TO BANK AND THEN ONLY CAN CANCEL...OR CAN VISIT NEAREST DEPOSIT MACHINE AND PAY TO BANK ( VIA CHECK ).AND AFTER ADMIN VALIDATES...LOAN GETS CANCELLED


    }


}
