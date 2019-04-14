    package com.example.pavee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

    public class applynow extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applynow);
        Toast.makeText(getApplicationContext()," YOUR REQUEST HAS BEEN SUCCESSFULLY SUBMITTED. " +
                "\n PLEASE VISIT THE NEAREST BRANCH FOR DOCUMENTS VERIFICATION.",Toast.LENGTH_SHORT).show();

    }

}
