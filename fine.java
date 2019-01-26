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

public class fine extends AppCompatActivity {

    static JSONObject jObjc = null;
    String bikeNumb,fineCash,duraa,timetaken,timeShouldReturn,timeRet,resid,ema,sname,fname,phonenum,extrat;


    private SharedPreferences prefs,prefer;
    private String prefName ="preProfile";
    private String prefName2 ="preBike";

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    String gearS;
    static JSONObject jObj = null;
    static String json = "",message;
    int availableBikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);

        TextView bn,fc,dr,tt,tsr,tr;

        Bundle extras=getIntent().getExtras();
        String meso=extras.getString("fines");

        try {
            jObjc = new JSONObject(meso);

            JSONArray userArray=jObjc.getJSONArray("user");
            JSONObject user=userArray.getJSONObject(0);
            phonenum=user.getString("PN");// THIS IS GIVING NULL
            bikeNumb=user.getString("BN");
            fineCash=user.getString("FC");
            duraa=user.getString("DR");
            timetaken=user.getString("TT");
            timeShouldReturn=user.getString("TSR");
            timeRet=user.getString("TR");
            resid=user.getString("RD");
            ema=user.getString("EM");
            sname=user.getString("SN");
            fname=user.getString("FN");
            extrat=user.getString("ET");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        bn=findViewById(R.id.finebikeno1);
        fc=findViewById(R.id.finecash1);
        dr=findViewById(R.id.fineduration1);
        tt=findViewById(R.id.timetaken1);
        tsr=findViewById(R.id.timeshouldreturn1);
        tr=findViewById(R.id.timereturned1);

        bn.append(bikeNumb);
        fc.append(fineCash);
        dr.append(duraa);
        tt.append(timetaken);
        tsr.append(timeShouldReturn);
        tr.append(timeRet);
    }

    public void clearfine(View view){
        new fine.backgroundclear(this).execute();
    }

    public void illegal(View view){
        Intent int11 = new Intent(fine.this, Profile.class);
        startActivity(int11);
    }

    class backgroundclear extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;
        public backgroundclear(Context context){
            this.context=context;

        }

        @Override
        protected void onPostExecute(String s) {
            json = s.toString();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


            try {
                jObj = new JSONObject(json);
                int  success = jObj.getInt("success");

                switch (success){
                    case 0:
                        break;
                    case 1:
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
//            String sname =voids[0];
//            String fname= voids[1];
//            String phonenum=voids[2];
//            String mail=voids[3];
//            String residence=voids[4];
//            String durat=voids[5];
//            String payM=voids[6];
//            String acode=voids[7];
//            String geary1=voids[8];


//            String connstr="http://stardigitalbikes.com/bike_request1.php";
            String connstr="http://192.168.43.113/pdobikephp/fines_clear_pdo.php";
            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                Log.d("PHONE NUMBER ISSH"," "+phonenum+" "+extrat+" "+bikeNumb+" "+fineCash);

                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(sname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(phonenum,"UTF-8")
                        +"&&"+ URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(ema,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(resid,"UTF-8")
                        +"&&"+ URLEncoder.encode("duration","UTF-8")+"="+URLEncoder.encode(duraa,"UTF-8")
                        +"&&"+ URLEncoder.encode("timetaken","UTF-8")+"="+URLEncoder.encode(timetaken,"UTF-8")
                        +"&&"+ URLEncoder.encode("timereturned","UTF-8")+"="+URLEncoder.encode(timeRet,"UTF-8")
                        +"&&"+ URLEncoder.encode("timeshouldreturn","UTF-8")+"="+URLEncoder.encode(timeShouldReturn,"UTF-8")
                        +"&&"+ URLEncoder.encode("extratime","UTF-8")+"="+URLEncoder.encode(extrat,"UTF-8")
                        +"&&"+ URLEncoder.encode("bikenumber","UTF-8")+"="+URLEncoder.encode(bikeNumb,"UTF-8")
                        +"&&"+ URLEncoder.encode("finecash","UTF-8")+"="+URLEncoder.encode(fineCash,"UTF-8");
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
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                        message=jObj.getString("message");
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

}
