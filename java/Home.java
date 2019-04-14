package com.example.pavee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {
EditText email,password;
    EditText ema,pw;
    Button login, create_account;
    String TAG="Document";
    int flag=1;
    List<String> list;
    String number,pws,usch,pwch;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usr = db.collection("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = findViewById(R.id.login);
        create_account = findViewById(R.id.createaccount);
        ema = findViewById(R.id.email);
        pw= findViewById(R.id.password);
        number =ema.getText().toString().trim();

        pws =pw.getText().toString().trim();

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(Home.this, create_acc.class);
//                startActivity(in);
//            }
//        });

    }

    public void ock(View v) {
                Intent in = new Intent(Home.this, create_acc.class);
                startActivity(in);
            }


    public void verify(View v) {

        Toast.makeText(getApplicationContext(), "Account Exists", Toast.LENGTH_LONG).show();
        Intent in = new Intent(Home.this, homepage.class);
        startActivity(in);
    }
//        if (flag == 1) {
//
//            db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        list = new ArrayList<String>();
//
//
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//
//                            list.add(document.getId());
//
//                        }
//
//                    }
//                    flag = 2;
//                }
//            });
//
//        }
//
//        if (flag == 2) {
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).equals(number)) {
//
//                    startActivity(in);
//                }
//
//            }
//
//        }



//        DocumentReference docRef = db.collection("users").document(number);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//
//                       usch=document.getData().get("Email").toString().trim();
//                       pwch= document.getData().get("Password").toString().trim();
//
//                        Toast.makeText(getApplicationContext(),  usch+" SUCC  "+pwch, Toast.LENGTH_LONG).show();
////                        if ((emails=="papu.cena7@gmail.com")&&(pws=="game123")) {
////
////
////                            Toast.makeText(getApplicationContext(),  " LOGIN SUCCESSFUL", Toast.LENGTH_LONG).show();
////                            Intent in = new Intent(Home.this, homepage.class);
////                            startActivity(in);
////                        }
////                        else
////                        {
////                            //Toast.makeText(getApplicationContext(),  emails+" SUCC  "+pws, Toast.LENGTH_LONG).show();

////                            ema.setError("Invalid username/Password");
////                            ema.requestFocus();
//
//                         //   return;
//
//
//                       // }
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });


}
