package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SPLASH1 extends AppCompatActivity {


    private SharedPreferences prefl;
    private String preflogin="preflogin";
    private static final String LOGIN_STATUS_KEY ="Login Status";

    private String prefName2 ="preBike";
    SharedPreferences prefb;
    private static final String BIKE_NUMBER_KEY ="Bike Number";
    private static final String RENT_BIKE_KEY ="Rent Bike";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        prefl=getSharedPreferences(preflogin, MODE_PRIVATE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                prefb=getSharedPreferences(prefName2,MODE_PRIVATE);

                    if (prefl.getBoolean(LOGIN_STATUS_KEY,false)){

                        if (prefb.getBoolean(RENT_BIKE_KEY,true)){
                            Intent int3=new Intent(SPLASH1.this,returnBike.class);
                            startActivity(int3);
                            finish();
                        }else {
                            Intent intent= new Intent(SPLASH1.this,MainActivity.class);
                startActivity(intent);
                finish();
                        }
                    }else{
                    Intent intent= new Intent(SPLASH1.this,LOGIN.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);


    }
}
