package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Price extends AppCompatActivity {

    Button Bsub;
    TextView Tprice,Ttime,Tbike;
    int time;

    private static final String TIMER_KEY ="Timer Key";

    private SharedPreferences preft;
    private String prefTimerName ="preTimer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);


        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.pricetoolbar);
        setSupportActionBar(toolbar);

        Bundle extras=getIntent().getExtras();
        int cash=extras.getInt("px");
        time=extras.getInt("yt");
        String bikeno=extras.getString("bn");

        preft=getSharedPreferences(prefTimerName, MODE_PRIVATE);
        SharedPreferences.Editor editor=preft.edit();

        //---save the values in the EditText view to preferences---
        editor.putInt(TIMER_KEY, time);
        //---saves the values---
        editor.commit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent intent= new Intent(Price.this,returnBike.class);
//                intent.putExtra("timer", time);
                    startActivity(intent);
                    finish();}

        }, 9000);

        Tprice=(TextView)findViewById(R.id.textprice1);
        Tprice.setText(Integer.toString(cash));

        Ttime=(TextView)findViewById(R.id.textyourtime);
        Ttime.setText(Integer.toString(time)+" Hrs");

        Tbike=(TextView)findViewById(R.id.textbikenumber);
        Tbike.setText(bikeno);


    }

    //*******************MENU****************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }

}
