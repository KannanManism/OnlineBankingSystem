package com.example.pavee;
//k < 1500000|| k > 15000000
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

import java.util.HashMap;
import java.util.Map;

public class homeloan extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG = "Document";
    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
    CollectionReference loandb = db.collection("loandb");
    CollectionReference loancount = db.collection("loancount");
    String p, p1, ps2;
    String pending = "0";
    //String val_check="";
    int count = 0, p2 = 0;
    String money;
    int k,k1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeloan);

    }


    public void update_db(View v) {
        count++;
        EditText e = findViewById(R.id.editText1);
        p = e.getText().toString().trim();
        EditText e1 = findViewById(R.id.editText2);
        p1 = e1.getText().toString().trim();
        pending=String.valueOf((Integer.parseInt(p)+5120));
        if(p.length()!=0 && p1.length()!=0)
        {
             k = Integer.parseInt(p);
             k1 = Integer.parseInt(p1);

        }
        else
        {
            e.setError("Invalid Loan amount. Please check the amount details.");
            e.requestFocus();
            count = 0;
            return;
        }

        if (k < 2500000 || k > 7500000) {

            e.setError("Invalid Loan amount. Please check the amount details.");
            e.requestFocus();
            count = 0;
            return;

        }
        //8015150522
        //9751494250
        else if (p.length()!=0 && p1.length()!=0 && (k >2500000||k ==2500000) && (k < 7500000||k==7500000)) {
            DocumentReference docRef = db.collection("loandb").document("9751494250");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            money =  document.getData().get("money").toString();

                            //Balance = Balance.replaceAll("[^a-zA-Z0-9]|[:]", "");
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                            Toast.makeText(getApplicationContext(), p + " " + document.getData().get("Loan Amount"), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),  " CHECKKKK" + document.getData().get("Home Loan Amount"), Toast.LENGTH_SHORT).show();
                            int val_check= Integer.parseInt(document.getData().get("Loan Amount").toString());
                            if (val_check== 0) {
                                Map<String, Object> data1 = new HashMap<>();
                                data1.put("Loan Type","Home");
                                data1.put("Loan Amount", p);
                                data1.put("Loan Period", p1);
                                data1.put("Loan Pending amount", pending);
                                data1.put("money",money);
                                loandb.document("9751494250").set(data1);
                                Toast.makeText(getApplicationContext(), p + " " + p1, Toast.LENGTH_SHORT).show();

                                p2 += 1;
                                ps2 = String.valueOf(p2);
                                Map<String, Object> data2 = new HashMap<>();
                                data2.put("count", ps2);
                                loancount.document("9751494250").set(data2);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "LOAN APPLICATION ALREADY EXISTS", Toast.LENGTH_SHORT).show();


                            }
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }
//

//set restricton (ranges for numbers in entering values)--done
        //0.create an column loan_to_be_paid which has the LOAN AMT+ INTEREST  and that should be paid till end
        //1.FOR EVERY USER....IF THERE IS NO MONEY TO PULL FORM ACCOUNT...THEN THAT MONEY WILL KEEP ADDING IN
        // THE LOAN PENDING COLUMN...
        // 2. TO CANCEL LOAN I MUST first remove loan_to_be_paid amt form account balance ..if not inform him that it can not be cancelled


    }

}
