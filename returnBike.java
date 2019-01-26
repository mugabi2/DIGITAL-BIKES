package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class returnBike extends AppCompatActivity {

    private ProgressBar progBar;
    private TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    static JSONObject jObj = null;
    static String json = "",message,jjon="";
    private String prefName2 ="preBike";
    private String prefName ="preProfile";
    private SharedPreferences prefs,prefer,prefb;
    String Acode;

    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";

    Boolean rentStatus=true;
    EditText Eagcode;
    String Usurname,Ufirstname,Uphone,Uemail,Uresidence;
    Button bafrica,bcedat,bcit,bfema,blibrary,blivingstone,blumumba,bmaingate,bmarystuart,bmitchell,bnkrumah,buh;

    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds=21600000; //6 hrs
    private boolean timerRunning;

    private static final String TIMER_KEY ="Timer Key";
    private SharedPreferences preft;
    private String prefTimerName ="preTimer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_bike);

        progBar= (ProgressBar)findViewById(R.id.progressBar5);
        pogless();

        countdownText = findViewById(R.id.time2);

        preft=getSharedPreferences(prefTimerName,MODE_PRIVATE);

        int timere=preft.getInt(TIMER_KEY,0);

//        Bundle extras=getIntent().getExtras();
//        int timere=extras.getInt("timer");
//
        if (timere==6) {
            updateTimer();
            startStop();
        }else {
            countdownText.append("Please return bike by 6 pm");
        }
        bafrica=findViewById(R.id.bafrica);
        bcedat=findViewById(R.id.bcedat);
        bcit=findViewById(R.id.bcit);
        bfema=findViewById(R.id.bfema);
        blibrary=findViewById(R.id.blibrary);
        blivingstone=findViewById(R.id.blivingstone);
        blumumba=findViewById(R.id.blumumba);
        bmaingate=findViewById(R.id.bmaingate);
        bmarystuart=findViewById(R.id.bmarystuart);
        bmitchell=findViewById(R.id.bmitchell);
        bnkrumah=findViewById(R.id.bnkrumah);
        buh=findViewById(R.id.buh);

        bafrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="1";
                returnBike(Acode);
            }
        });
        bcedat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="2";
                returnBike(Acode);
            }
        });
        bcit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="3";
                returnBike(Acode);
            }
        });
        bfema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="4";
                returnBike(Acode);
            }
        });
        blibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="5";
                returnBike(Acode);
            }
        });
        blivingstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="6";
                returnBike(Acode);
            }
        });
        blumumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="7";
                returnBike(Acode);
            }
        });
        bmaingate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="8";
                returnBike(Acode);
            }
        });
        bmarystuart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="9";
                returnBike(Acode);
            }
        });
        bmitchell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="10";
                returnBike(Acode);
            }
        });
        bnkrumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="11";
                returnBike(Acode);
            }
        });
        buh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acode="12";
                returnBike(Acode);
            }
        });

    }

    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void pogless() {

        new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < 100) {
                    mProgressStatus += 10;
                    if(mProgressStatus==100){
                        mProgressStatus=0;
                    }
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progBar.setProgress(mProgressStatus);
//                            text.setText(""+mProgressStatus+"%");
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void startStop(){

        startTimer();
//        if(timerRunning){
//            stopTimer();
//        }
//        else{
//            startTimer();
//        }

    }

    public void startTimer(){
        countDownTimer =new CountDownTimer(timeLeftInMilliseconds,60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds=millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
//        countdownButton.setText("START");
        timerRunning=true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning=false;
//        countdownButton.setText("STOP");

    }

    public void updateTimer(){
        int hours=(int)timeLeftInMilliseconds/3600000;
        int minutes=(int)timeLeftInMilliseconds%3600000/60000;

        String timeLeftText;

        timeLeftText=""+hours;
        timeLeftText+=":";
        if (minutes<10)timeLeftText+="0";
        timeLeftText+=minutes;
        countdownText.setText(timeLeftText);
    }

    public void returnBike(String Acodey){

        ProgressBar pb =findViewById(R.id.progressBar5);
        pb.setVisibility(ProgressBar.VISIBLE);
            new returnBike.backgroundReturn(this).execute(Acodey);
            Toast.makeText(getApplicationContext(), "Requesting....", Toast.LENGTH_SHORT).show();
            Log.d("Request status","GOOD INPUT am gonna make the request");

    }

    class backgroundReturn extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;

        public backgroundReturn(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Bike return status");
        }

        @Override
        protected void onPostExecute(String s) {
            jjon = s.toString();
            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            try {
                jObj = new JSONObject(jjon);
                message=jObj.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(!prefb.getBoolean(RENT_BIKE_KEY,true)){
//                dialog.setMessage(message);
//                dialog.show();
                ProgressBar pb =findViewById(R.id.progressBar5);
                pb.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
                finish();
            }
            else {
//                ProgressBar pb =findViewById(R.id.progressBar5);
//                pb.setVisibility(ProgressBar.INVISIBLE);
//                dialog.setMessage(message);
//                dialog.show();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            }

        }
        public void onBackPressed(){
            moveTaskToBack(true);
        }
        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String AgCode =voids[0];

//            String connstr="http://stardigitalbikes.com/bike_return.php";
            String connstr="http://192.168.43.113/pdobikephp/bike_return_pdo.php";


            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
            String bikey=prefb.getString(BIKE_NUMBER_KEY,"");
            SharedPreferences.Editor editor=prefb.edit();

            prefer=getSharedPreferences(prefName,MODE_PRIVATE);
            Usurname=prefer.getString(SURNAME_KEY,"");
            Ufirstname=prefer.getString(FIRST_NAME_KEY,"");
            Uphone=prefer.getString(PHONE_NUMBER_KEY,"");
            Uemail=prefer.getString(EMAIL_ADDRESS_KEY,"");
            Uresidence=prefer.getString(RESIDENCE_KEY,"");


            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(AgCode,"UTF-8")
                        +"&&"+ URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(Usurname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(Ufirstname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(Uphone,"UTF-8")
                        +"&&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(Uemail,"UTF-8")
                        +"&&"+ URLEncoder.encode("bikenumber","UTF-8")+"="+URLEncoder.encode(bikey,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(Uresidence,"UTF-8")
                        +"&&"+ URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                ops.close();
                Log.d("JSON Exception","DONE SENDING");

                InputStream ips =http.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(ips,"ISO-8859-1"));
                String line ="";
                while ((line=reader.readLine()) !=null){
                    result +=line;

                }
//#######INTRODICING THE READING OOF THE RETURNED JSON
                ips.close();
                reader.close();
                json = result.toString();

                try {
                    jObj = new JSONObject(json);
                    if(json!=null){
                        int success=jObj.getInt("success");

                        Log.d("JSONStatus", "JSON RETURNED");

                        if(success==1){
                            editor.clear().commit();
                            rentStatus=false;
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            message=jObj.getString("message");
                            editor.commit();

                        }else{
                            rentStatus=true;
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.commit();
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        rentStatus=true;
                        editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    rentStatus=true;
                    editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                rentStatus=true;
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }


}

