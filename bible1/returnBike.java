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
import android.widget.EditText;
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

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    static JSONObject jObj = null;
    static String json = "",message;
    private String prefName2 ="preBike";
    private String prefName ="preProfile";
    private SharedPreferences prefs,prefer;

    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";

    Boolean rentStatus=false;
    EditText Eagcode;
    String Usurname,Ufirstname,Uphone,Uemail,Uresidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_bike);

        Eagcode =(EditText)findViewById(R.id.editTextAgCode);

    }

    public void returnBike(View view){
        String Acode =Eagcode.getText().toString();

        if(!Acode.isEmpty()){
            new returnBike.backgroundReturn(this).execute(Acode);
            Log.d("Request status","GOOD INPUT am gonna make the request");
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill in agent receiving the bike",Toast.LENGTH_LONG).show();
        }

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
            dialog.setTitle("login status");
        }

        @Override
        protected void onPostExecute(String s) {
            json = s.toString();
            prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
            try {
                jObj = new JSONObject(json);
                message=jObj.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(!prefs.getBoolean(RENT_BIKE_KEY,false)){
//                dialog.setMessage(message);
//                dialog.show();
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
            else {
                dialog.setMessage(message);
                dialog.show();

            }

        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String AgCode =voids[0];

            String connstr="http://192.168.43.113/bikephp/bike_return.php";

            prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
            String bikey=prefs.getString(BIKE_NUMBER_KEY,"");
            SharedPreferences.Editor editor=prefs.edit();

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
                            rentStatus=false;
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
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

