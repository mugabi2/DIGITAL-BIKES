package com.example.samuelhimself.agent;

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

    String serverKey="2y10pN0pj28Q9WNoLrPCI3mIwtDkHhBmfpFGWshiuHxvqmzsltGQKzS";
    private static final String AGENT_CODE_KEY ="Agent Code";
    static String json = "";
    static JSONObject jObj = null;
    boolean confirm;

    private SharedPreferences prefs;
    private String prefName ="preProfile";
    String meso;


    String usersurname,userfirstname,userphonenumb,useremailadd,userresidence,userbikeno,usercash,userduration,userpaymeth,message;
    Boolean loginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_bike);

        Bundle extras=getIntent().getExtras();
        meso=extras.getString("getInform");

        try {
            jObj = new JSONObject(meso);
            JSONArray userArray=jObj.getJSONArray("user");
            JSONObject user=userArray.getJSONObject(0);
            usersurname=user.getString("SN");
            userfirstname=user.getString("FN");
            userphonenumb=user.getString("PN");
            useremailadd=user.getString("EM");
            userresidence=user.getString("RD");
            userbikeno=user.getString("BN");

            TextView Treq=(TextView)findViewById(R.id.textViewReturn);
            Treq.append(usersurname+" "+userfirstname+" "+userphonenumb+" "+useremailadd+" "+userresidence+" "+userbikeno);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void confirmReturn(View view){
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        String Acode=prefs.getString(AGENT_CODE_KEY,"");

        EditText Ebikeno=(EditText)findViewById(R.id.editTextbikeR);
        String GivenBikeNo=Ebikeno.getText().toString();

        //Checking if all fields have been filled
        if(!GivenBikeNo.isEmpty()){

            new returnBike.backgroundReturnConfirm(this).execute(Acode,GivenBikeNo);
            Log.d("Request status","GOOD INPUT am gonna make the request");
            Toast.makeText(getApplicationContext(),"Confirming.............",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please fill in the bike number",Toast.LENGTH_LONG).show();
        }

    }
    //##################BACK GROUND CLASSS$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$444444444
    class backgroundReturnConfirm extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;
        public backgroundReturnConfirm(Context context){
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            dialog= new AlertDialog.Builder(context).create();
            dialog.setTitle("Bike request status");
        }

        @Override
        protected void onPostExecute(String s) {

//      new registration().savingToSharedPrefs(usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,loginStatus);
            if (confirm){
//                dialog.setMessage("CONFIRMED");
//                dialog.show();
                Intent intent= new Intent(returnBike.this,MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Not confirmed please try again",Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... voids) {
            String result="";
            String  AgCode=voids[0];
            String  Bike= voids[1];

            String connstr="http://192.168.43.113/bikephp/confirm_return_request.php";

            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data = URLEncoder.encode("agent_code","UTF-8")+"="+URLEncoder.encode(AgCode,"UTF-8")
                        +"&&"+ URLEncoder.encode("bikenumber","UTF-8")+"="+URLEncoder.encode(Bike,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(userphonenumb,"UTF-8")
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
                            confirm=true;
                        }else{
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
                        confirm=false;
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
                    confirm=false;
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
                confirm=false;
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
                confirm=false;
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
                confirm=false;
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }

}
