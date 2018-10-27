package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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

import static com.example.samuelhimself.bible1.R.id.cash1;
import static com.example.samuelhimself.bible1.R.id.digitaltime1;
import static com.example.samuelhimself.bible1.R.id.fulltime;

import static com.example.samuelhimself.bible1.R.id.radio_female;

public class Rent1 extends AppCompatActivity {

    private ProgressBar progBar;
    private TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;

    int gearInt,checkG=0;
    String gear;

    Button Bhom,Bmore,Bproceed;
    String duration="half",payment="cash";
    TextView Tcurrency;
     int checkP=0,checkD=0,price,durationInt,paymentInt;
     static final int rentPrice1=1500,getRentPrice2=2000;
    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";

    static JSONObject jObj = null;
    static String json = "";
    String pkafrica,pkcedat,pkcit,pkfema,pklibrary,pklivingstone,pklumumba,pkmaingate,pkmarystuart,pkmitchell,pknkrumah,pkuh,message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent1);

        progBar= (ProgressBar)findViewById(R.id.progressBar1);

        pogless();

        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.rentbar);
        setSupportActionBar(toolbar);
    }

    //*******************MENU****************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.usermenu:
                Intent int1 =new Intent(getApplicationContext(),Profile.class);
                startActivity(int1);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClickedDuration(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.halftime:
                if (checked){
                    duration="half";
                    durationInt=1;
                    checkD++;
                }
                break;
            case fulltime:
                if (checked){
                    duration="full";
                    durationInt=2;
                    checkD++;
                }
                break;
        }

    }

    public void onRadioButtonClickedPayment(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cash1:
                if (checked){
                    payment="cash";
                    paymentInt=1;
                    checkP++;
                }
                break;
            case digitaltime1:
                if (checked){
//                    payment="DT";
//                    paymentInt=2;
//                    checkP++;
                    Toast.makeText(getApplicationContext(), "Not yet available Coming soon", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
//    public void onRadioButtonClickedGear(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch (view.getId()) {
//            case R.id.singlegear1:
//                if (checked){
//                    gear="S";
//                    gearInt=1;
//                    checkG++;
//                }
//                break;
//            case multiplegear1:
//                if (checked){
//                   gear="M";
//                    gearInt=2;
//                    checkG++;
//                }
//                break;
//        }
//
//    }

    public void getBikesIn(View v){

        if (checkP>=1&checkD>=1) {
//LATEST ISSUEI IS HERER ERER HERE HRERE
//                    if (duration.equals("half")) {
//                        price=rentPrice*durationInt;
            RadioButton Rdigitime=(RadioButton)findViewById(R.id.digitaltime1);
            if (Rdigitime.isChecked()) {
                Toast.makeText(getApplicationContext(), "Please use cash Digital Time coming soon", Toast.LENGTH_SHORT).show();
            }
            else {
                ProgressBar pb =findViewById(R.id.progressBar1);
                pb.setVisibility(ProgressBar.VISIBLE);
                new Rent1.backgroundBikesIn(this).execute();
                Toast.makeText(getApplicationContext(), "Preparing your Map...", Toast.LENGTH_SHORT).show();

            }
//                    }
        }
        else {
            Toast.makeText(getApplicationContext(), "please check the buttons", Toast.LENGTH_SHORT).show();
        }
    }

//

    class backgroundBikesIn extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgroundBikesIn(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
//            dialog= new AlertDialog.Builder(context).create();
//            dialog.setTitle("login status");
        }

        @Override
        protected void onPostExecute(String s) {
//            dialog.setMessage(s);
//            dialog.show();
//            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            Intent int2=new Intent(Rent1.this,Mapsimport1.class);
            int2.putExtra("bikesin",s);
            int2.putExtra("DurationInt", durationInt);
            int2.putExtra("Duration", duration);
            int2.putExtra("PaymethodInt", paymentInt);
            int2.putExtra("Paymethod", payment);
//            int2.putExtra("gearInt", gearInt);
            startActivity(int2);
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String geary;

//            String acode=voids[0];
            String connstr="http://stardigitalbikes.com/user_bikes_in.php";
            if(gearInt==1){
                 geary="1";
            }else {
                 geary="2";
            }

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8");

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
                            JSONArray userArray=jObj.getJSONArray("user");
                            JSONObject user=userArray.getJSONObject(0);
                            pkafrica=user.getString("AF");
                            pkcedat=user.getString("CD");
                            pkcit=user.getString("IT");
                            pkfema=user.getString("FM");
                            pklibrary=user.getString("LB");
                            pklivingstone=user.getString("LV");
                            pklumumba=user.getString("LM");
                            pkmaingate=user.getString("MG");
                            pkmarystuart=user.getString("MS");
                            pkmitchell=user.getString("MT");
                            pknkrumah=user.getString("NK");
                            pkuh=user.getString("UH");
                            Log.d("JSONStatus","Login success");

                        }else{
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }
            return result;
        }
    }

    public void pogless() {

        new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < 100) {
                    mProgressStatus += 5;
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

    @Override
    public void onResume(){
        super.onResume();
        ProgressBar pb =findViewById(R.id.progressBar1);
        pb.setVisibility(ProgressBar.INVISIBLE);
    }
}
