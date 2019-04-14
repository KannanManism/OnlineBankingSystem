package com.example.pavee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }

    public void viewplann(View v) {
        Intent in = new Intent(MainActivity.this, view_plan.class);
        startActivity(in);
    }

    public void cnclplann(View v) {
        Intent in = new Intent(MainActivity.this, cancel_loan.class);
        startActivity(in);
    }
    public void homep(View v) {
        Intent in1 = new Intent(MainActivity.this, homepage.class);
        startActivity(in1);
    }

}
