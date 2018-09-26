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
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    Button Bconfirm,Bbalancing,Brevenue,Bmap,Bprofile,Bparking;

    String serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS";
    Button Bhom,Bmore,Blogin;
    EditText ecode,epassword;

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String AGENT_CODE_KEY ="Agent Code";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    private SharedPreferences prefs;
    private String prefName ="preProfile";
    static JSONObject jObj = null;
    static String json = "";
    String usersurname,userfirstname,userphonenumb,useremailadd,userresidence,userduration,userpaymeth,userbikeno,usercash,message;
    Boolean loginStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bconfirm= findViewById(R.id.confirm1);
//        Bconfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent int1 =new Intent(getApplicationContext(),confirmRequests.class);
//                startActivity(int1);
//            }
//        });

        Bbalancing= findViewById(R.id.bikebalancing1);
        Bbalancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),bikeBalancing.class);
                startActivity(int1);
            }
        });

        Button Bin= findViewById(R.id.bicyclesIn);
        Bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),bicyclesIn.class);
                startActivity(int1);
            }
        });

        Button Bout= findViewById(R.id.bicyclesOut);
        Bout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),bicyclesOut.class);
                startActivity(int1);
            }
        });

        Button Brev= findViewById(R.id.revenue1);
        Brev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),revenue.class);
                startActivity(int1);
            }
        });

        Bprofile= findViewById(R.id.profile1);
        Bprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),profile.class);
                startActivity(int1);
            }
        });
    }
    public void getRequests(View v){
        prefs=getSharedPreferences(prefName,MODE_PRIVATE);
        String Acode =prefs.getString(AGENT_CODE_KEY,"");
        new MainActivity.backgroundGetreq(this).execute(Acode);
    }

    class backgroundGetreq extends AsyncTask<String, Void,String> {

        AlertDialog dialog;
        Context context;
        public backgroundGetreq(Context context){
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

            Intent int2=new Intent(MainActivity.this,confirmRequests.class);
            int2.putExtra("getInfo",s);
            startActivity(int2);

        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";

            String acode=voids[0];
            String connstr="http://192.168.43.113/bikephp/get_requests.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(acode,"UTF-8")
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
                            JSONArray userArray=jObj.getJSONArray("user");
                            JSONObject user=userArray.getJSONObject(0);
                            usersurname=user.getString("SN");
                            userfirstname=user.getString("FN");
                            userphonenumb=user.getString("PN");
                            useremailadd=user.getString("EM");
                            userresidence=user.getString("RD");
                            userduration=user.getString("DR");
                            userpaymeth=user.getString("PM");
                            usercash=user.getString("CS");
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


    //RETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURNRETURN
    public void getReturnRequests(View v){

        prefs=getSharedPreferences(prefName,MODE_PRIVATE);
        String Agcode =prefs.getString(AGENT_CODE_KEY,"");
        new MainActivity.backgroundreturnReq(this).execute(Agcode);
    }
    class backgroundreturnReq extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;
        public backgroundreturnReq(Context context){
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
//            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            Log.d("JSON Exception","IN MAIN ACTIVITY POST EXEC METHOD");
            Intent int2=new Intent(MainActivity.this,returnBike.class);
            int2.putExtra("getInform",s);
            startActivity(int2);
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";

            String acode=voids[0];
            String connstr="http://192.168.43.113/bikephp/get_return_requests.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(acode,"UTF-8")
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
                            JSONArray userArray=jObj.getJSONArray("user");
                            JSONObject user=userArray.getJSONObject(0);
                            usersurname=user.getString("SN");
                            userfirstname=user.getString("FN");
                            userphonenumb=user.getString("PN");
                            useremailadd=user.getString("EM");
                            userresidence=user.getString("RD");
                            userbikeno=user.getString("BN");
                            Log.d("JSONStatus","got requests");

                        }else{
                            Log.d("JSONStatus","get requests failure");
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
}
