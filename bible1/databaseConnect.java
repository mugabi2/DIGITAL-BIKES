package com.example.samuelhimself.bible1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;

public class databaseConnect extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private SharedPreferences prefs;
    String prefName="prefprofile";

    private static final String SURNAME_KEY ="Surname:";
    private static final String FIRST_NAME_KEY ="First name:";
    private static final String PHONE_NUMBER_KEY ="Phone number:";
    private static final String EMAIL_ADDRESS_KEY ="Email:";
    private static final String RESIDENCE_KEY ="Residence:";
    private static final String GENDER_KEY ="Gender:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_connect);



        mTextViewResult=findViewById(R.id.textViewDB);
        Button buttonParse =findViewById(R.id.parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
                Toast.makeText(getApplicationContext(),"parsing.............",Toast.LENGTH_SHORT).show();
                Log.d("JSONStatus", "PARSING");



            }
        });

    }
    private void jsonParse(){
       // String url ="https://api.myjson.com/bins/1fkq5k";
       // String url="https://api.myjson.com/bins/73es0";
        String url="http://192.168.43.189/bikephp/getting_info.php";
        JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray =response.getJSONArray("user");


                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject insideArray =jsonArray.getJSONObject(i);

                                String surname= insideArray.getString("SN");
                                String firstname=insideArray.getString("FN");
                                String phonenumber=insideArray.getString("PN");
                                String emailadd =insideArray.getString("EM");
                                String residence =insideArray.getString("RD");
                                String gender =insideArray.getString("GD");



                                //shared prefs#########################################
                                prefs=getSharedPreferences(prefName, MODE_PRIVATE);
                                SharedPreferences.Editor editor=prefs.edit();

                                //---save the values in the EditText view to preferences---
                                editor.putString(SURNAME_KEY, surname);
                                editor.putString(FIRST_NAME_KEY, firstname);
                                editor.putString(PHONE_NUMBER_KEY, phonenumber);
                                editor.putString(EMAIL_ADDRESS_KEY, emailadd);
                                editor.putString(RESIDENCE_KEY, residence);
                                editor.putString(GENDER_KEY, gender);
                                Log.d("JSONStatus", "IN SHARED PREFS");

                                //---saves the values---
                                editor.commit();
                                //#################################################################3
                                mTextViewResult.append(prefs.getString(SURNAME_KEY,""));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSONStatus", "JSON ERROR..");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              /*  error.printStackTrace();
                Log.d("JSONStatus", "ERROR ON JSON RETURN");*/

                if (error instanceof NetworkError) {
                    Log.d("JSONStatus", "NETWORK ERROR");
                } else if (error instanceof ServerError) {
                    Log.d("JSONStatus", "SERVER ERROR");
                } else if (error instanceof AuthFailureError) {
                    Log.d("JSONStatus", "AUTH FAILURE ERROR");
                } else if (error instanceof ParseError) {
                    Log.d("JSONStatus", "PARSE ERROR");
                } else if (error instanceof NoConnectionError) {
                    Log.d("JSONStatus", "NO CONNECTION");
                } else if (error instanceof TimeoutError) {
                    Log.d("JSONStatus", "TIME OUT");
                }

            }
        });

        mQueue.add(request);
    }



}
