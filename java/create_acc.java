
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.regex.Pattern;

public class create_acc extends AppCompatActivity {

    Button regs;
    EditText nam, ag, num, em, pw, cn;
    String names, ages, numb, emails, pwds, cntr;
    List<String> list;

    String TAG = "Document";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usr = db.collection("users");
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

    }


    public void update_users(View v) {
        nam = findViewById(R.id.name);
        names = nam.getText().toString().trim();
        ag = findViewById(R.id.age);
        ages = ag.getText().toString().trim();
        num = findViewById(R.id.num);
        numb = num.getText().toString().trim();

        em = findViewById(R.id.email);
        emails = em.getText().toString().trim();
        isValid(emails);
        pw = findViewById(R.id.pwd);
        pwds = pw.getText().toString().trim();
        cn = findViewById(R.id.cnt);
        cntr = cn.getText().toString().trim();

        if( numb.length() < 10 || numb.length() > 10 ){
            num.setError("Enter a valid mobile");
            num.requestFocus();
            return;
        }

        if (flag == 1) {
            db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        list = new ArrayList<String>();
                        //int i=0;

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            list.add(document.getId());
                            Log.d("CH", numb);

                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).equals(numb)) {
                                    Toast.makeText(getApplicationContext(), "Account Exists", Toast.LENGTH_LONG).show();
//                                    num.setError("Account Exists");
//                                    num.requestFocus();
//                                    return;
                                    flag = 0;

//                                Intent in = new Intent(create_acc.this, Home.class);
//                                startActivity(in);
                                }
                            }

                        }
                        flag = 2;

                    }
                }
            });
        }

        if (flag == 2) {
            Map<String, String> data1 = new HashMap<>();
            data1.put("Name", names);

            data1.put("Age", ages);
            data1.put("Number", numb);
            data1.put("Email", emails);
            data1.put("Password", pwds);
            data1.put("Country", cntr);

            db.collection("users").document(numb).set(data1)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });


            Intent in = new Intent(create_acc.this, Home.class);
            startActivity(in);

        }

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        return pat.matcher(email).matches();
    }
}






