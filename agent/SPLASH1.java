package com.example.samuelhimself.agent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SPLASH1 extends AppCompatActivity {


    private SharedPreferences prefl;
    private String preflogin="preflogin";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        prefl=getSharedPreferences(preflogin, MODE_PRIVATE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (prefl.getBoolean(LOGIN_STATUS_KEY,false)){
                    Intent intent= new Intent(SPLASH1.this,MainActivity.class);
                    startActivity(intent);}
                else{
                    Intent intent= new Intent(SPLASH1.this,LOGIN.class);
                    startActivity(intent);
                }
            }
        }, 3000);


    }
}

