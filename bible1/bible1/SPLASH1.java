package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class SPLASH1 extends AppCompatActivity {


    String number;

    private SharedPreferences prefl,prefs;
    private String preflogin="preflogin";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    private String prefName ="preProfile";

    private String prefName2 ="preBike";
    SharedPreferences prefb;
    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";

    static JSONObject jObj = null;
    static String json = "";

    private static final String PHONE_NUMBER_KEY ="Phone Number";
    int sucksey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        prefl=getSharedPreferences(preflogin, MODE_PRIVATE);
//        prefb=getSharedPreferences(prefName2,MODE_PRIVATE);


        if (prefl.getBoolean(LOGIN_STATUS_KEY,false)){
                        new SPLASH1.backgroundBikesIn(SPLASH1.this).execute();

                    }else{
                    Intent intent= new Intent(SPLASH1.this,LOGIN.class);
                    startActivity(intent);
                    finish();
                }

    }

    class backgroundBikesIn extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgroundBikesIn(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Connection error");

            prefs=getSharedPreferences(prefName, MODE_PRIVATE);
            number=prefs.getString(PHONE_NUMBER_KEY,"");

        }

        @Override
        protected void onPostExecute(String s) {
//            dialog.setMessage(s);
//            dialog.show();

            json = s.toString();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            try {
                jObj = new JSONObject(json);
                if(json!=null){
                    sucksey=jObj.getInt("success");


                }else{
                    Log.e("JSON Parser", "RETURNED JSON IS NULL ");

                }
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error creating the json object " + e.toString());
                dialog.setMessage("Please connect to the internet and then try again");
                dialog.show();
            }

            switch (sucksey) {
                case 0:
                    Toast.makeText(getApplicationContext(),"Not connected!",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    Intent int111=new Intent(SPLASH1.this,Mapsimport1.class);
                    int111.putExtra("bikesin",s);
                    startActivity(int111);
                    break;
                case 2:
                    Intent int22 =new Intent(getApplicationContext(),fine.class);
                    int22.putExtra("fines",s);
                    startActivity(int22);
                    break;
                case 3:
                    Intent int33 = new Intent(SPLASH1.this, finalRegistration.class);
                    startActivity(int33);
                    break;
                case 4:
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    Intent int444=new Intent(SPLASH1.this,Mapsimport1.class);
                    int444.putExtra("bikesin",s);
                    startActivity(int444);
                    break;

            }

        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String geary;

//            String acode=voids[0];
//            String connstr="http://stardigitalbikes.com/user_bikes_in.php";
            String connstr="http://192.168.43.113/pdobikephp/user_bikes_in_pdo.php";


            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("serverKey","UTF-8")+"="+URLEncoder.encode(serverKey,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(number,"UTF-8");

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
