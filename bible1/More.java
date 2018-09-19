package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class More extends AppCompatActivity {

    Button Bsafe,Bcred,Babout,Bpfile,Bspt,Bhist,Bhm,Bpark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Bsafe= findViewById(R.id.st);
        Bsafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),SafetyTips.class);
                startActivity(int1);
            }
        });

        Bpark= findViewById(R.id.parking3);
        Bpark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),parkingPointsMap.class);
                startActivity(int1);
            }
        });

        Babout= findViewById(R.id.abt);
        Babout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),About.class);
                startActivity(int1);
            }
        });

        Bcred= findViewById(R.id.crd);
        Bcred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),Credit.class);
                startActivity(int1);
            }
        });

        Bspt= findViewById(R.id.spt);
        Bspt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),Support.class);
                startActivity(int1);
            }
        });

/*
        Bhist= findViewById(R.id.hist);
        Bhist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),History.class);
                startActivity(int1);
            }
        });

        */

        Bhm= findViewById(R.id.hm1);
        Bhm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
        });


        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.moretoolbar);
        setSupportActionBar(toolbar);



    }

    //*******************MENU****************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }

    //terms and conds wesite link

    public void TACwebsite(View view){
    Intent TACweblink=new Intent(Intent.ACTION_VIEW, Uri.parse("http/www.google.com"));
    startActivity(TACweblink);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.usermenu:
                Intent int1 =new Intent(getApplicationContext(),Profile.class);
                startActivity(int1);

        }
        return super.onOptionsItemSelected(item);
    }
}
