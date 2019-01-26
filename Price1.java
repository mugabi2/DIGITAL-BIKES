package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Price1 extends AppCompatActivity {

    String payme="1";
    static JSONObject jObjc = null;
    String prx,pmmethod="1",durati;
    static JSONObject jObj = null;
    static String json = "",message;
    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";


    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    private SharedPreferences prefs,prefl;
    private String prefName ="preProfile";
    private String preflogin="preflogin";

    String Sname,Fname,Phone,Email,Resi, agent,Duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price1);

        Bundle extras=getIntent().getExtras();
        String meso=extras.getString("all");
        durati=extras.getString("duration");
        agent=extras.getString("agent");

        try {
            jObjc = new JSONObject(meso);
            JSONArray userArray=jObjc.getJSONArray("user");
            JSONObject user=userArray.getJSONObject(0);
            prx=user.getString("PX");
            Duration=user.getString("DS");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView tprice=findViewById(R.id.textprice2);
        tprice.append(prx+" UGX");
        TextView tduration=findViewById(R.id.textduration2);
        tduration.append(Duration);

    }

    public  void request(View view){

        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        Sname=prefs.getString(SURNAME_KEY,"");
        Fname=prefs.getString(FIRST_NAME_KEY,"");
        Phone=prefs.getString(PHONE_NUMBER_KEY,"");
        Email=prefs.getString(EMAIL_ADDRESS_KEY,"");
        Resi=prefs.getString(RESIDENCE_KEY,"");

        new Price1.backgroundrequest(this).execute(Sname,Fname,Phone,Email,Resi,durati,pmmethod,agent);
    }

    class backgroundrequest extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;
        public backgroundrequest(Context context){
            this.context=context;

        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Bike renting status");
        }

        @Override
        protected void onPostExecute(String s) {
            json = s.toString();

            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            Log.e("POST", s);

            try {
                jObj = new JSONObject(json);
                int  successs = jObj.getInt("success");

                switch (successs){
                    case 0:
                        break;
                    case 4:
                        Intent int1 =new Intent(getApplicationContext(),Mapsimport1.class);
                        int1.putExtra("bikesin",s);
                        startActivity(int1);
                        break;
                    case 2:
                        Intent int2 =new Intent(getApplicationContext(),fine.class);
                        startActivity(int2);
                        break;
                    case 3:
                        Intent int3 =new Intent(getApplicationContext(),finalRegistration.class);
                        startActivity(int3);
                        break;
                }
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error creating the json object " + e.toString());
            }

        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String sname =voids[0];
            String fname= voids[1];
            String phonenum=voids[2];
            String mail=voids[3];
            String residence=voids[4];
            String durat=voids[5];
            String payM=voids[6];
            String acode=voids[7];
//            String geary1=voids[8];


//            String connstr="http://stardigitalbikes.com/bike_request1.php";
            String connstr="http://192.168.43.113/pdobikephp/bike_request_pdo.php";
            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(sname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(phonenum,"UTF-8")
                        +"&&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(residence,"UTF-8")
                        +"&&"+ URLEncoder.encode("duration","UTF-8")+"="+URLEncoder.encode(durat,"UTF-8")
                        +"&&"+ URLEncoder.encode("payment_method","UTF-8")+"="+URLEncoder.encode(payM,"UTF-8")
                        +"&&"+ URLEncoder.encode("cash","UTF-8")+"="+URLEncoder.encode(prx,"UTF-8")
                        +"&&"+ URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(acode,"UTF-8")
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

//                        if(success==1){
//                            rentStatus=true;
//                            JSONArray userArray=jObj.getJSONArray("user");
//                            JSONObject user=userArray.getJSONObject(0);
//                            String bike=user.getString("BN");
//                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefb.edit();
//                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                            editor.putString(BIKE_NUMBER_KEY,bike);
//                            editor.commit();
//
//                        }else{
//                            rentStatus=false;
//                            prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefb.edit();
//                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                            editor.commit();
//                            Log.d("JSONStatus","Login failure");
//                            message=jObj.getString("message");
//                            Log.d("JSONStatus",message);
//                        }

                    }else{
//                        rentStatus=false;
//                        prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                        SharedPreferences.Editor editor=prefb.edit();
//                        editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                        message=jObj.getString("message");
                    }
                } catch (JSONException e) {
//                    rentStatus=false;
//                    prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                    SharedPreferences.Editor editor=prefb.edit();
//                    editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());

                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
//                rentStatus=false;
//                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefb.edit();
//                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
//                rentStatus=false;
//                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefb.edit();
//                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
//                rentStatus=false;
//                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefb.edit();
//                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }
}
