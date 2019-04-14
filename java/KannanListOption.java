package com.example.pavee;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class KannanListOption extends AppCompatActivity  {

    private ListView list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannan_list_option);
        list = (ListView) findViewById(R.id.listview);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.paymentOptions, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    Intent intent = new Intent(KannanListOption.this, ShowBalance.class);

                    startActivity(intent);
                }
                else if(position ==1 ){
                    Intent intent = new Intent(KannanListOption.this, AddMoney.class);

                    startActivity(intent);
                }
                else if (position == 2){
                    Intent intent = new Intent(KannanListOption.this, Transaction.class);

                    startActivity(intent);
                }
                else if (position == 3){
                    Intent intent = new Intent(KannanListOption.this, History.class);

                    startActivity(intent);
                }
                else if (position == 4){
                    Intent intent = new Intent(KannanListOption.this, AddMoney.class);

                    startActivity(intent);
                }
                else if (position == 5){
                    Intent intent = new Intent(KannanListOption.this, Transaction.class);

                    startActivity(intent);
                }
            }
        });


    }

    public void homep(View v) {
        Intent in1 = new Intent(getApplicationContext(), homepage.class);
        startActivity(in1);
    }
}