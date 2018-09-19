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
import android.widget.EditText;
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

import static com.example.samuelhimself.bible1.R.id.radio_female;

public class registration extends AppCompatActivity {


    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    private static final String SURNAME_KEY ="Surname:";
    private static final String FIRST_NAME_KEY ="First name:";
    private static final String PHONE_NUMBER_KEY ="Phone number:";
    private static final String EMAIL_ADDRESS_KEY ="Email:";
    private static final String RESIDENCE_KEY ="Residence:";
    private static final String GENDER_KEY ="Gender:";
    private static final String LOGIN_STATUS_KEY ="Login Status:";

    private SharedPreferences prefs;
    private String prefName ="prefProfile";
    static JSONObject jObj = null;
    static String json = "";
    String usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,message;
    Boolean loginStatus;

    String sex="M";
    EditText esname,efname,ephone,eemail,epassword,eresi;
    Button Bsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        esname=(EditText)findViewById(R.id.sname);
        efname=(EditText)findViewById(R.id.fname);
        ephone=(EditText)findViewById(R.id.phonenum);
        eemail=(EditText)findViewById(R.id.Eemail);
        epassword=(EditText)findViewById(R.id.Ppassword);
        eresi=(EditText)findViewById(R.id.resi);
        Bsignup=(Button)findViewById(R.id.signup);


    }
    public void sendIntent(String s){
        Intent intent=new Intent(this,Profile.class);
        intent.putExtra("meso",s);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked){
                    sex="M";
                }
                    // Pirates are the best
                    break;
            case radio_female:
                if (checked){
                    sex="F";
                }
                    // Ninjas rule
                    break;
        }

    }

        public void regMe(View view){

        String sname =esname.getText().toString();
        String fname =efname.getText().toString();
        String phone =ephone.getText().toString();
        String email =eemail.getText().toString();
        String psword =epassword.getText().toString();
        String resid =eresi.getText().toString();

//        backgroundreg bg = new backgroundreg(this);
//        bg.execute(sname,fname,phone,email,psword,resid,sex);
            new backgroundregistration(this).execute(sname,fname,phone,email,psword,resid,sex);


//           TextView resultDisplay=(TextView)findViewById(R.id.textdisplay);
//            resultDisplay.setText(prefs.getString(SURNAME_KEY,""));


//            prefs=getSharedPreferences(prefName, MODE_PRIVATE);


            prefs=getSharedPreferences(prefName, MODE_PRIVATE);
            Toast.makeText(getApplicationContext(),prefs.getString(SURNAME_KEY,""),Toast.LENGTH_SHORT).show();

    }
    //##################BACK GROUND CLASSS$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$444444444
    class backgroundregistration extends AsyncTask<String, Void,String> {


        AlertDialog dialog;
        Context context;
        public backgroundregistration(Context context){
            this.context=context;

        }

        @Override
        protected void onPreExecute() {
//                dialog= new AlertDialog.Builder(context).create();
//                   dialog.setTitle("login status");
        }

        @Override
        protected void onPostExecute(String s) {
//                    dialog.setMessage(s);
//                    dialog.show();
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


            String connstr="http://192.168.43.189/bikephp/sign_up.php";

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

                      savingToSharedPrefs(usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,loginStatus);


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

    public  void savingToSharedPrefs( String Ssurname,String Sfirstname,String Sphonenumb,String Semail,String Sresidence,String Sgender, Boolean SloginStatus){
        //shared prefs#########################################
        prefs=getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();

        //---save the values in the EditText view to preferences---
        editor.putString(SURNAME_KEY, Ssurname);
        editor.putString(FIRST_NAME_KEY, Sfirstname);
        editor.putString(PHONE_NUMBER_KEY, Sphonenumb);
        editor.putString(EMAIL_ADDRESS_KEY, Semail);
        editor.putString(RESIDENCE_KEY, Sresidence);
        editor.putString(GENDER_KEY, Sgender);
        editor.putBoolean(LOGIN_STATUS_KEY,SloginStatus);

        //---saves the values---
        editor.commit();

        Log.d("JSONStatus","saved to prefs successfully");
    }


}
