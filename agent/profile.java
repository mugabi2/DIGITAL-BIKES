package com.example.samuelhimself.agent;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class profile extends AppCompatActivity {

    Button Bhom,Bmore;

    String serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS";

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";
    private static final String LOGIN_STATUS_KEY ="Login Status";
    private static final String AGENT_CODE_KEY ="Agent Code";

    private SharedPreferences prefs,prefl;
    private String prefName ="preProfile";
    private String preflogin="preflogin";

    static JSONObject jObj = null;
    static String json = "";

    Boolean loginStatus=Boolean.TRUE;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        prefs = getSharedPreferences(prefName, MODE_PRIVATE);

        TextView Tname=(TextView)findViewById(R.id.textinname);
        Tname.append(prefs.getString(SURNAME_KEY,"")+" "+ prefs.getString(FIRST_NAME_KEY,""));

        TextView Tcode=(TextView)findViewById(R.id.textincode);
        Tcode.append(prefs.getString(AGENT_CODE_KEY,""));

        TextView Tphone=(TextView)findViewById(R.id.textinphone);
        Tphone.append(prefs.getString(PHONE_NUMBER_KEY,""));

        TextView Temail=(TextView)findViewById(R.id.textinemail);
        Temail.append(prefs.getString(EMAIL_ADDRESS_KEY,""));

        TextView Tresidence=(TextView)findViewById(R.id.textinresidence);
        Tresidence.append(prefs.getString(RESIDENCE_KEY,""));
        Bhom= findViewById(R.id.hm11);
        Bhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
        });

    }


    public void signMeOut(View view){
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        String phone=prefs.getString(PHONE_NUMBER_KEY,"");
        new profile.backgroundSignout(this).execute(phone);
    }

    class backgroundSignout extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgroundSignout(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String s) {
            prefl=getSharedPreferences(preflogin, MODE_PRIVATE);
            if(!prefl.getBoolean(LOGIN_STATUS_KEY,Boolean.TRUE)){
                Intent int1 =new Intent(getApplicationContext(),LOGIN.class);
                startActivity(int1);
            }
            else {
                Toast.makeText(getApplicationContext(),"Sign out unsuccessful",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";

            String phonenum=voids[0];

            String connstr="http://192.168.43.113/bikephp/agent_sign_out.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(phonenum,"UTF-8")
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
                            prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefl.edit();
                            loginStatus=Boolean.FALSE;
                            editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                            editor.commit();
                            Log.d("JSONStatus","Log out success");
                        }else{
                            prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefl.edit();
                            loginStatus=Boolean.TRUE;
                            editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                            editor.commit();
                            Log.d("JSONStatus","Log out failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefl.edit();
                        loginStatus=Boolean.TRUE;
                        editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefl.edit();
                    loginStatus=Boolean.TRUE;
                    editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefl.edit();
                loginStatus=Boolean.TRUE;
                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefl.edit();
                loginStatus=Boolean.TRUE;
                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefl.edit();
                loginStatus=Boolean.TRUE;
                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }


}
