package com.example.pavee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {

    Button transaction,loans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        transaction = findViewById(R.id.transaction);
        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KannanActivity.class);
                startActivity(i);
            }
        });
    }

    public void vplans(View v) {

        Intent in1 = new Intent(homepage.this, view_plan.class);
        startActivity(in1);
    }



}
