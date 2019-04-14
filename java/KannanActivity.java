package com.example.pavee;

// 9751494250
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KannanActivity extends AppCompatActivity {



    private EditText editTextMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannan);


        editTextMobile = findViewById(R.id.phoneno);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.length()>10|| mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }
                SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit=sf.edit();
                edit.putString("mobile",mobile);
                edit.commit();
                Intent intent = new Intent(KannanActivity.this, KannanVerifyPhoneActivity.class);
                //Intent intent = new Intent(MainActivity.this, PaymentOption.class);
                //Intent intent = new Intent(MainActivity.this,ListOption.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });


    }

    public void homep(View v) {
        Intent in1 = new Intent(getApplicationContext(), homepage.class);
        startActivity(in1);
    }
}