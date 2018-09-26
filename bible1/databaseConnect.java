package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

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

public class databaseConnect extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private SharedPreferences prefs;
    private String prefName ="preProfile";
    private String prefName2 ="preBike";


    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    static JSONObject jObj = null;
    static String json = "",message;

    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";

    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";
    static final int rentPrice=1500;
    int sente,yourTime;
    String usname,ufname,uphone,umail,uresi,udura,upaymeth,uagcode;
    Boolean rentStatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_connect);

        Bundle extras=getIntent().getExtras();
        int dura=extras.getInt("DurationInt");
        int method=extras.getInt("PaymethodInt");

        if (method==1) {
            sente = dura * rentPrice;
        }
        else {
            sente=dura;
        }
        if (dura==1) {
            yourTime=6;
        }
        else {
            yourTime=12;
        }

        prefs=getSharedPreferences(prefName,MODE_PRIVATE);

        usname=prefs.getString(SURNAME_KEY,"");
        ufname=prefs.getString(FIRST_NAME_KEY,"");
        uphone=prefs.getString(PHONE_NUMBER_KEY,"");
        umail=prefs.getString(EMAIL_ADDRESS_KEY,"");
        uresi=prefs.getString(RESIDENCE_KEY,"");
        udura=extras.getString("Duration");
        upaymeth=extras.getString("Paymethod");


        mTextViewResult=findViewById(R.id.textViewDB);
        Button bagent1 =findViewById(R.id.agent1);

//        mQueue = Volley.newRequestQueue(this);

        bagent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                jsonParse();
                uagcode="1";
                new backgroundrequest(databaseConnect.this).execute(usname,ufname,uphone,umail,uresi,udura,upaymeth,uagcode);
                Toast.makeText(getApplicationContext(),"requesting.............",Toast.LENGTH_SHORT).show();
                Log.d("JSONStatus", "requestING");
            }
        });

        Button bagent2=findViewById(R.id.agent2);
        bagent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                jsonParse();
                uagcode="2";
                new backgroundrequest(databaseConnect.this).execute(usname,ufname,uphone,umail,uresi,udura,upaymeth,uagcode);
                Toast.makeText(getApplicationContext(),"requesting.............",Toast.LENGTH_SHORT).show();
                Log.d("JSONStatus", "requestING");
            }
        });
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
            prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
            if(prefs.getBoolean(RENT_BIKE_KEY,true)){
                String biko=prefs.getString(BIKE_NUMBER_KEY,"");
                Intent int1 =new Intent(getApplicationContext(),Price.class);
                int1.putExtra("px",sente);
                int1.putExtra("yt",yourTime);
                int1.putExtra("bn",biko);
                startActivity(int1);
            }
            else {
                try {
                    jObj = new JSONObject(json);
                    message=jObj.getString("message");
                    dialog.setMessage(message);
                    dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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


            String connstr="http://192.168.43.113/bikephp/bike_request.php";

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

                        if(success==1){
                            rentStatus=true;
                            JSONArray userArray=jObj.getJSONArray("user");
                            JSONObject user=userArray.getJSONObject(0);
                            String bike=user.getString("BN");
                            prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefs.edit();
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.putString(BIKE_NUMBER_KEY,bike);
                            editor.commit();

                        }else{
                            rentStatus=false;
                            prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                            SharedPreferences.Editor editor=prefs.edit();
                            editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                            editor.commit();
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        rentStatus=false;
                        prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    rentStatus=false;
                    prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                rentStatus=false;
                prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                rentStatus=false;
                prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                rentStatus=false;
                prefs=getSharedPreferences(prefName2,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putBoolean(RENT_BIKE_KEY,rentStatus);
                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }

//    private void jsonParse(){
//       // String url ="https://api.myjson.com/bins/1fkq5k";
//       // String url="https://api.myjson.com/bins/73es0";
//        String url="http://192.168.43.189/bikephp/getting_info.php";
//        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            JSONArray jsonArray =response.getJSONArray("user");
//                            for (int i=0;i<jsonArray.length();i++){
//                                JSONObject insideArray =jsonArray.getJSONObject(i);
//
//                                String surname= insideArray.getString("SN");
//                                String firstname=insideArray.getString("FN");
//                                String phonenumber=insideArray.getString("PN");
//                                String emailadd =insideArray.getString("EM");
//                                String residence =insideArray.getString("RD");
//                                String gender =insideArray.getString("GD");
//
//
//
//                                //shared prefs#########################################
//                                prefs=getSharedPreferences(prefName, MODE_PRIVATE);
//                                SharedPreferences.Editor editor=prefs.edit();
//
//                                //---save the values in the EditText view to preferences---
//                                editor.putString(SURNAME_KEY, surname);
//                                editor.putString(FIRST_NAME_KEY, firstname);
//                                editor.putString(PHONE_NUMBER_KEY, phonenumber);
//                                editor.putString(EMAIL_ADDRESS_KEY, emailadd);
//                                editor.putString(RESIDENCE_KEY, residence);
//                                editor.putString(GENDER_KEY, gender);
//                                Log.d("JSONStatus", "IN SHARED PREFS");
//
//                                //---saves the values---
//                                editor.commit();
//                                //#################################################################3
//                                mTextViewResult.append(prefs.getString(SURNAME_KEY,""));
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.d("JSONStatus", "JSON ERROR..");
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//              /*  error.printStackTrace();
//                Log.d("JSONStatus", "ERROR ON JSON RETURN");*/
//
//                if (error instanceof NetworkError) {
//                    Log.d("JSONStatus", "NETWORK ERROR");
//                } else if (error instanceof ServerError) {
//                    Log.d("JSONStatus", "SERVER ERROR");
//                } else if (error instanceof AuthFailureError) {
//                    Log.d("JSONStatus", "AUTH FAILURE ERROR");
//                } else if (error instanceof ParseError) {
//                    Log.d("JSONStatus", "PARSE ERROR");
//                } else if (error instanceof NoConnectionError) {
//                    Log.d("JSONStatus", "NO CONNECTION");
//                } else if (error instanceof TimeoutError) {
//                    Log.d("JSONStatus", "TIME OUT");
//                }
//
//            }
//        });
//
//        mQueue.add(request);
//    }

}
