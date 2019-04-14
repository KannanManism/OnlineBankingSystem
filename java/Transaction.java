package com.example.pavee;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Transaction extends AppCompatActivity  {


    List<String> list;

    private Button btnSubmit;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String TAG="Transaction";
    CollectionReference loandb = db.collection("loandb");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        final String mobile=sf.getString("mobile","NA");
        db.collection("loandb").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    list = new ArrayList<String>();
                    //int i=0;

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        list.add(document.getId());

                    }
                    list.remove(mobile);
                    final ListView list1 = (ListView) findViewById(R.id.listview);
                    ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,list);
                    adapter.notifyDataSetChanged();
                    list1.setAdapter(adapter);
                    list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                            if (position == 0) {
                                String toPerson =  list1.getItemAtPosition(position).toString();
                                Intent intent = new Intent(Transaction.this, PaymentOption.class);
                                intent.putExtra("toPerson",toPerson);
                                startActivity(intent);

                            } else if (position == 1) {
                                String toPerson =  list1.getItemAtPosition(position).toString();
                                Intent intent = new Intent(Transaction.this, PaymentOption.class);
                                intent.putExtra("toPerson",toPerson);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });


    }
    public void homep(View v) {
        Intent in1 = new Intent(getApplicationContext(), homepage.class);
        startActivity(in1);
    }

}
