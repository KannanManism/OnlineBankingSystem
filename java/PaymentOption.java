package com.example.pavee;

import android.app.Activity;
//AIzaSyBDqY7RVBWt6He1ZSejiyTMMhhXMadjXes
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import android.os.AsyncTask;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentOption extends AppCompatActivity {

    String TAG="Document";
    private TextView text;
    private Activity activity;
    TextView  amountLeft,toPersonView;

    private EditText money;
    String toPerson;

    FirebaseFirestore  db = FirebaseFirestore.getInstance();


    String Balance1="", Balance2="",mon="";
    String  loanamount1,loanamount2,period1,period2,pend1,pend2,type1,type2;
    int balance1,balance2, tempMon, time=5;
    private TextView finalResult;

    CollectionReference loandb = db.collection("loandb");
    Button pay,interact;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);
        pay = findViewById(R.id.secondButton);

        amountLeft = findViewById(R.id.amountLeft);
        finalResult = findViewById(R.id.displayMobile);
        toPersonView = findViewById(R.id.toperson);
        money = findViewById(R.id.money);


        Intent intent = getIntent();
        toPerson = intent.getStringExtra("toPerson");
        toPersonView.setText("Beneficiary: " + toPerson);

        SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
        final String p=sf.getString("mobile","NA");
        finalResult.setText("Your Account: "+p);

        DocumentReference docRef = db.collection("loandb").document(p);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Balance1 =  document.getData().get("money").toString();
                        loanamount1 = document.getData().get("Loan Amount").toString();
                        period1 = document.getData().get("Loan Period").toString();
                        pend1=document.getData().get("Loan Pending amount").toString();
                        type1=document.getData().get("Loan Type").toString();

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        amountLeft.setText("Balance: " + Balance1);


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        DocumentReference docRef1 = db.collection("loandb").document(toPerson);
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Balance2 =  document.getData().get("money").toString();
                        loanamount2 = document.getData().get("Loan Amount").toString();
                        period2 = document.getData().get("Loan Period").toString();
                        pend2=document.getData().get("Loan Pending amount").toString();
                        type2=document.getData().get("Loan Type").toString();



                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mon = money.getText().toString();
                //Toast.makeText(getApplicationContext(),mon,Toast.LENGTH_SHORT).show();
                if(! mon.matches("" )){
                    tempMon = Integer.parseInt(mon);
                }



                if( mon.matches("") ||mon.length()==0 || tempMon < 0 && tempMon > 1000 ){

                    money.setError("Enter Amount < 1000");
                    money.requestFocus();
                    return;
                }


                balance1 = Integer.parseInt(Balance1) - Integer.parseInt(money.getText().toString()) ;
                Balance1 = String.valueOf(balance1);
                Map<String, Object> data1 = new HashMap<>();
                data1.put("money", Balance1);
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

                Balance1 = Integer.toString(balance1);
                //amountLeft.setText("Updated Balance: " + Balance1);



                balance2 = Integer.parseInt(Balance2) + Integer.parseInt(money.getText().toString()) ;
                Balance2 = String.valueOf(balance2);
                Map<String, Object> data2 = new HashMap<>();
                data2.put("money", Balance2);
                if (loanamount2==null || period2 ==null || pend2 == null || type2 == null ){
                    data1.put("Loan Pending amount",0);
                    data1.put("Loan Amount",0);
                    data1.put("Loan Period",0);
                    data1.put("Loan Type",0);
                }


                data2.put("Loan Pending amount",pend2);
                data2.put("Loan Amount",loanamount2);
                data2.put("Loan Period",period2);
                data2.put("Loan Type",type2);
                loandb.document(toPerson).set(data2);


                PaymentOption.AsyncTaskRunner runner = new PaymentOption.AsyncTaskRunner();

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

            Toast.makeText(PaymentOption.this, "Payment Completed", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPostExecute called");

            // execution of result of Long time consuming operation

            progressDialog.dismiss();

            finalResult.setText(result);

            Intent home = new Intent(getApplicationContext(),KannanListOption.class);
            startActivity(home);

        }


        @Override

        protected void onPreExecute() {

            Toast.makeText(PaymentOption.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            Log.d("AsyncTask", "onPreExecute called");

            progressDialog = ProgressDialog.show(PaymentOption.this,

                    "Payment in Process",

                    "Don't close for " + Integer.toString(time) + " seconds");

        }


        @Override

        protected void onProgressUpdate(String... text) {

            Log.d("AsyncTask", "onProgressUpdate");

            Toast.makeText(PaymentOption.this, "Payment in Process", Toast.LENGTH_SHORT).show();

            finalResult.setText(text[0]);


        }

    }

    public void homep(View v) {
        Intent in1 = new Intent(getApplicationContext(), homepage.class);
        startActivity(in1);
    }

}
