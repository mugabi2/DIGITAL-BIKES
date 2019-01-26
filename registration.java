package com.example.samuelhimself.bible1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
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

import static com.example.samuelhimself.bible1.R.id.continueshare;
import static com.example.samuelhimself.bible1.R.id.radio_female;

public class registration extends AppCompatActivity {

    private ProgressBar progBar;
    private TextView text;
    private Handler mHandler = new Handler();
    private int mProgressStatus=0;

    private SharedPreferences prefer;
    private String prefName2 ="preBike";
    String thecode,send;


    String serverKey="2y10f2Kkl1GRi5si0AAsgvsgJWyqXsUszC3DuvRLwZZ";
    private static final String SURNAME_KEY ="Surname";
    private static final String FIRST_NAME_KEY ="First Name";
    private static final String PHONE_NUMBER_KEY ="Phone Number";
    private static final String EMAIL_ADDRESS_KEY ="Email";
    private static final String RESIDENCE_KEY ="Residence";
    private static final String GENDER_KEY ="Gender";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    private SharedPreferences prefs,prefl;
    private String prefName ="preProfile";
    private String preflogin="preflogin";
    static JSONObject jObj = null;
    static String json = "";
    String usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender,message;
    Boolean loginStatus;
    static JSONObject jObjc = null;

    Dialog shareDialog;



    String sex="M",mesg,all,msg;
    EditText esname,efname,ephone,eemail,epassword,eresi,esharecode;
    Button Bsignup,Bcontinueshare;
    int suckinda,sucks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        shareDialog = new Dialog(this);
        shareDialog.setContentView(R.layout.share_code);

        progBar= (ProgressBar)findViewById(R.id.progressBar4);
        pogless();

        esname=(EditText)findViewById(R.id.sname);
        efname=(EditText)findViewById(R.id.fname);
        ephone=(EditText)findViewById(R.id.phonenum);
        eemail=(EditText)findViewById(R.id.Eemail);
        epassword=(EditText)findViewById(R.id.Ppassword);
        eresi=(EditText)findViewById(R.id.resi);
        Bsignup=(Button)findViewById(R.id.signup);


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked){
                    sex="M";
                }
                    break;
            case radio_female:
                if (checked){
                    sex="F";
                }
                    break;
        }
    }
    public void TACwebsite(View view){
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://stardigitalbikes.com/terms_and_conditions.php"));
        startActivity(browserIntent);
    }

    public void pogless() {

        new Thread(new Runnable() {
            public void run() {
                final int presentage=0;
                while (mProgressStatus < 100) {
                    mProgressStatus += 10;
                    if(mProgressStatus==100){
                        mProgressStatus=0;
                    }
                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            progBar.setProgress(mProgressStatus);
//                            text.setText(""+mProgressStatus+"%");
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

        public void regMe(View view){

        String sname =esname.getText().toString();
        String fname =efname.getText().toString();
        String phone =ephone.getText().toString();
        String email =eemail.getText().toString();
        String psword =epassword.getText().toString();
        String resid =eresi.getText().toString();
 

            //Checking if all fields have been filled
            if(!sname.isEmpty() && !fname.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !psword.isEmpty() &&!resid.isEmpty()){
                ProgressBar pb =findViewById(R.id.progressBar4);
                pb.setVisibility(ProgressBar.VISIBLE);
                        new backgroundregistration(this).execute(sname,fname,phone,email,psword,resid,sex);
                        Log.d("Request status","GOOD INPUT am gonna make the request");
            }
            else {
                Toast.makeText(getApplicationContext(),"Please fill in all fields",Toast.LENGTH_LONG).show();
            }

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
        }

        @Override
        protected void onPostExecute(String s) {
            prefl=getSharedPreferences(preflogin, MODE_PRIVATE);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            try {
                jObjc = new JSONObject(s);
                suckinda=jObjc.getInt("success");
                mesg=jObjc.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            switch (suckinda) {
                case 0:
                    Toast.makeText(getApplicationContext(),mesg,Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    all=s;
                    showshare();
                    break;

            }

//            if (prefl.getBoolean(LOGIN_STATUS_KEY,true)){
//                ProgressBar pb =findViewById(R.id.progressBar4);
//                pb.setVisibility(ProgressBar.INVISIBLE);
//                Intent intent= new Intent(registration.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//            else {
//                ProgressBar pb =findViewById(R.id.progressBar4);
//                pb.setVisibility(ProgressBar.INVISIBLE);
//                Toast.makeText(getApplicationContext(),"Phone number already used",Toast.LENGTH_LONG).show();
//            }
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


//            String connstr="http://stardigitalbikes.com/user_sign_up.php";
            String connstr="http://192.168.43.113/pdobikephp/user_sign_up_pdo.php";

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

//                            prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefl.edit();
//                            loginStatus=Boolean.TRUE;
//                            editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                            editor.commit();
                            Log.d("JSONStatus","Login success");



//make SHARED PREFS A METHOD

                      savingToSharedPrefs(usersurname,userfirstname,userphonenumb,useremailadd,userresidence,usergender);


                        }else{
//                            prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=prefl.edit();
//                            loginStatus=Boolean.FALSE;
//                            editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                            editor.commit();
                            Log.d("JSONStatus","Login failure");
                            message=jObj.getString("message");
                            Log.d("JSONStatus",message);
                        }
                    }else{
//                        prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                        SharedPreferences.Editor editor=prefl.edit();
//                        loginStatus=Boolean.FALSE;
//                        editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                        editor.commit();
                        Log.e("JSON Parser", "RETURNED JSON IS NULL ");
                    }
                } catch (JSONException e) {
//                    prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                    SharedPreferences.Editor editor=prefl.edit();
//                    loginStatus=Boolean.FALSE;
//                    editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                    editor.commit();
                    Log.e("JSON Parser", "Error creating the json object " + e.toString());
                }
//##################################################################33
                http.disconnect();
                return result;

            } catch (MalformedURLException e) {
//                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefl.edit();
//                loginStatus=Boolean.FALSE;
//                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (ProtocolException e) {
//                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefl.edit();
//                loginStatus=Boolean.FALSE;
//                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            } catch (IOException e) {
//                prefl=getSharedPreferences(preflogin,MODE_PRIVATE);
//                SharedPreferences.Editor editor=prefl.edit();
//                loginStatus=Boolean.FALSE;
//                editor.putBoolean(LOGIN_STATUS_KEY,loginStatus);
//                editor.commit();
                Log.d("JSON Exception",e.toString());
                result =e.getMessage();
            }

            return result;
        }


    }

    public  void savingToSharedPrefs( String Ssurname,String Sfirstname,String Sphonenumb,String Semail,String Sresidence,String Sgender){
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

        //---saves the values---
        editor.commit();

        Log.d("JSONStatus","saved to prefs successfully");
    }

    public void logMeIn(View view){
        Intent intent= new Intent(this,LOGIN.class);
        startActivity(intent);
    }

    public void sharing(){
        new registration.backgroundsharecode(registration.this).execute();
    }

    public void showshare() {

//        shareDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        esharecode=shareDialog.findViewById(R.id.sharecode111);
        Bcontinueshare=shareDialog.findViewById(R.id.continueshare);
        Bcontinueshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scode=esharecode.getText().toString();
                thecode=scode;

                if(!scode.isEmpty()){
                    sharing();
                    Log.d("Request status","INPUT PRESENT;;;CONTINUE");
                }else {

                    Toast.makeText(getApplicationContext(),all,Toast.LENGTH_LONG).show();
                    Intent int111=new Intent(registration.this,Mapsimport1.class);
                    int111.putExtra("bikesin",all);
                    startActivity(int111);
                    finish();
                    Log.d("Request status","THIS IS INCASE THERE IS NO INPUT");
                }

            }
        });
        shareDialog.show();
    }

    class backgroundsharecode extends AsyncTask<String, Void,String> {
        AlertDialog dialog;
        Context context;

        public backgroundsharecode(Context context){
            this.context=context;
        }

        @Override
        protected void onPostExecute(String s) {
            String jjon = s.toString();
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            try {
                jObjc = new JSONObject(s);
                sucks=jObjc.getInt("success");
                msg=jObjc.getString("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            switch (sucks) {
                case 1://CALL THE INTENT OF MAPSIMPORT
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    Intent int111=new Intent(registration.this,Mapsimport1.class);
                    int111.putExtra("bikesin",s);
                    startActivity(int111);
                    finish();
                    break;
                default:
                    Toast.makeText(getApplicationContext(),"Try again",Toast.LENGTH_LONG).show();
                    break;
            }

        }
        public void onBackPressed(){
            moveTaskToBack(true);
        }
        @Override
        protected String doInBackground(String... voids) {
            String result="";
//            String AgCode =voids[0];

//            String connstr="http://stardigitalbikes.com/bike_return.php";
            String connstr="http://192.168.43.113/pdobikephp/share_code_get.php";


            prefer=getSharedPreferences(prefName,MODE_PRIVATE);
            String Usurname=prefer.getString(SURNAME_KEY,""),
                    Ufirstname=prefer.getString(FIRST_NAME_KEY,""),
                    Uphone=prefer.getString(PHONE_NUMBER_KEY,""),
                    Uresidence=prefer.getString(RESIDENCE_KEY,"");
            String Ugender=prefer.getString(GENDER_KEY,"");


            try {
                URL url =new URL(connstr);
                HttpURLConnection http =(HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);


                OutputStream ops =http.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
                String data =URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(Usurname,"UTF-8")
                        +"&&"+ URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(Ufirstname,"UTF-8")
                        +"&&"+ URLEncoder.encode("phonenumber","UTF-8")+"="+URLEncoder.encode(Uphone,"UTF-8")
                        +"&&"+ URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(Ugender,"UTF-8")
                        +"&&"+ URLEncoder.encode("code","UTF-8")+"="+URLEncoder.encode(thecode,"UTF-8")
                        +"&&"+ URLEncoder.encode("residence","UTF-8")+"="+URLEncoder.encode(Uresidence,"UTF-8");
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
