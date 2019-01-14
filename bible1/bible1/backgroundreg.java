package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
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
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class backgroundreg extends AsyncTask<String, Void,String> {



    static JSONObject jObj = null;
    static String json = "";
    String usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,message;
    Boolean loginStatus;

    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    private static final String SURNAME_KEY ="Surname:";
    private static final String FIRST_NAME_KEY ="First name:";
    private static final String PHONE_NUMBER_KEY ="Phone number:";
    private static final String EMAIL_ADDRESS_KEY ="Email:";
    private static final String RESIDENCE_KEY ="Residence:";
    private static final String GENDER_KEY ="Gender:";

    private static final String PREF_SURNAME_KEY ="Surname:";
    private static final String PREF_FIRSTNAME_KEY ="First name:";
    private static final String PREF_PHONENUMBER_KEY ="Phone number:";
    private static final String PREF_EMAIL_KEY ="Email:";
    private static final String PREF_RESIDENCE_KEY ="Residence:";
    private static final String PREF_GENDER_KEY ="Gender:";
    private SharedPreferences prefs;
    private String prefName ="prefProfile";

    AlertDialog dialog;
    Context context;
    public backgroundreg(Context context){
        this.context=context;

    }

    @Override
    protected void onPreExecute() {
       dialog= new AlertDialog.Builder(context).create();
      dialog.setTitle("login status");
    }

    @Override
    protected void onPostExecute(String s) {
       dialog.setMessage(s);
       dialog.show();


//      new registration().savingToSharedPrefs(usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,loginStatus);


    }

    @Override
    protected String doInBackground(String... voids) {
        String result="";
        String sname =voids[0];
        String  fname= voids[1];
        String phonenum=voids[2];
        String mail=voids[3];
        String pass=voids[4];
        String residence=voids[5];
        String sex=voids[6];



        String connstr="http://stardigitalbikes.com/bikephp/sign_up.php";

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
                    +"&&"+ URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")
                    +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(residence,"UTF-8")
                    +"&&"+ URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8")
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
                        usergender=user.getString("GD");
                        loginStatus=Boolean.TRUE;
                        Log.d("JSONStatus","Login success");



//make SHARED PREFS A METHOD

//                        new registration().savingToSharedPrefs(usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,loginStatus);



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

