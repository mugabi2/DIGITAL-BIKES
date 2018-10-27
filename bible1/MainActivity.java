package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    Button Brent,Bevents,Bpromo,Binst,Bmore,Bparking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);


        Brent= findViewById(R.id.rab);
        Brent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),Rent1.class);
                startActivity(int1);
            }
        });



        Bparking= findViewById(R.id.safety1);
        Bparking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent int1 =new Intent(getApplicationContext(),SafetyTips.class);
                startActivity(int1);

            }
        });

        Bmore= findViewById(R.id.more1);
        Bmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),More.class);
                startActivity(int1);
            }
        });

        Binst= findViewById(R.id.inst);
        Binst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),Instructions.class);
                startActivity(int1);
            }
        });

        Bevents= findViewById(R.id.evt);
        Bevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),Events.class);
                startActivity(int1);
            }
        });

        Bpromo= findViewById(R.id.prom);
        Bpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(Intent.ACTION_SEND);
                int1.setType("text/plain");
                String shareBody ="Click on the link below to download Digital Bikes app";
                int1.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                int1.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(int1, "Share"));
            }
        });




    }
//*******************MENU****************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
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

    public void onBackPressed(){
        moveTaskToBack(true);
    }

    /* EditText username = (EditText)findViewById(R.id.edit1);

    public void login(View view){

        }*/
    }
