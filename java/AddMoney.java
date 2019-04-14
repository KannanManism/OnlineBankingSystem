package com.example.pavee;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import android.os.AsyncTask;

public class AddMoney extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference loandb = db.collection("loandb");

    private EditText money;
    private String Balance="", mon="" ;
    private int balance;
    private TextView showbal;
    private Button addMoney;
    Button btnShow, btnClear;


    NotificationManager manager;


    Notification myNotication;
    String  loanamount1,loanamount2,period1,period2,pend1,pend2,type1,type2;

    int tempMon,time=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        addMoney = findViewById(R.id.add);
        money = findViewById(R.id.money);
        showbal = findViewById(R.id.balance);


        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        final String p=sf.getString("mobile","NA");


        DocumentReference docRef = db.collection("loandb").document(p);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Balance =  document.getData().get("money").toString();
                        loanamount1 = document.getData().get("Loan Amount").toString();
                        period1 = document.getData().get("Loan Period").toString();
                        pend1=document.getData().get("Loan Pending amount").toString();
                        type1=document.getData().get("Loan Type").toString();
                        //Balance = Balance.replaceAll("[^a-zA-Z0-9]|[:]", "");
                        Log.d("Docs", "DocumentSnapshot data: " + document.getData());
                        showbal.setText("Balance: "+Balance);
                        //Toast.makeText(getApplicationContext(),p+balance,Toast.LENGTH_SHORT).show();



                    } else {
                        Log.d("Docs", "No such document");
                    }
                } else {
                    Log.d("Docs", "get failed with ", task.getException());
                }
            }
        });


        addMoney.setOnClickListener(new View.OnClickListener() {


            // || tempMon < 0
            @Override
            public void onClick(View v) {
                mon = money.getText().toString().trim();
                Toast.makeText(getApplicationContext(),mon,Toast.LENGTH_SHORT).show();
                tempMon = Integer.parseInt(mon);

                if( mon.length()==0  || tempMon < 0 && tempMon > 1000 ){

                    money.setError("Enter Amount < 1000");
                    money.requestFocus();
                    return;
                }


                balance = Integer.parseInt(Balance) + Integer.parseInt(money.getText().toString()) ;

                Map<String, Object> data1 = new HashMap<>();
                data1.put("money", balance);
                if (loanamount1==null || period1 ==null || pend1 == null || type1 == null ){
                    data1.put("Loan Pending amount",0);
                    data1.put("Loan Amount",0);
                    data1.put("Loan Period",0);
                    data1.put("Loan Type",0);
                }

                data1.put("Loan Pending amount",pend1);
                data1.put("Loan Amount",loanamount1);
                data1.put("Loan Period",period1);
                data1.put("Loan Type",type1);

                loandb.document(p).set(data1);

                Balance = Integer.toString(balance);
                showbal.setText("Updated Balance: " + Balance);
                money.setText("");

                AddMoney.AsyncTaskRunner runner = new AddMoney.AsyncTaskRunner();

                String sleepTime =  Integer.toString(time);

                runner.execute(sleepTime);


            }

        });



    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {


        private String resp;

        ProgressDialog progressDialog;


        @Override

        protected String doInBackground(String... params) {

            // NO UI related stuff here

            try {

                int timeinsec = Integer.parseInt(params[0]);
                int timeinms = timeinsec * 1000;


                while (timeinsec > 0) {

                    publishProgress("Payment in Progress for " + timeinsec + "  seconds"); // Calls onProgressUpdate()

                    Thread.sleep(1000);

                    timeinsec--;


                }

                resp = "Payment  Completed";

            } catch (InterruptedException e) {

                e.printStackTrace();

                resp = e.getMessage();

            } catch (Exception e) {

                e.printStackTrace();

                resp = e.getMessage();

            }

            return resp;

        }


        @Override

        protected void onPostExecute(String result) {

            Toast.makeText(AddMoney.this, "Payment Completed", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPostExecute called");

            // execution of result of Long time consuming operation

            progressDialog.dismiss();

            //finalResult.setText(result);

            Intent home = new Intent(getApplicationContext(),KannanListOption.class);
            startActivity(home);
            addNotification();
        }


        @Override

        protected void onPreExecute() {

            Toast.makeText(AddMoney.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPreExecute called");

            progressDialog = ProgressDialog.show(AddMoney.this,

                    "Payment in Process",

                    "Don't close for " + Integer.toString(time) + " seconds");

        }


        @Override

        protected void onProgressUpdate(String... text) {

            Log.d("AsyncTask", "onProgressUpdate");

            Toast.makeText(AddMoney.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            // finalResult.setText(text[0]);


        }

    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, AddMoney.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}