package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private static final String SURNAME_KEY ="Surname:";
    private static final String FIRST_NAME_KEY ="First name:";
    private static final String PHONE_NUMBER_KEY ="Phone number:";
    private static final String EMAIL_ADDRESS_KEY ="Email:";
    private static final String RESIDENCE_KEY ="Residence:";
    private static final String GENDER_KEY ="Gender:";
    Button Bhom,Bmore;
    private String prefName ="prefprofile";

    TextView textname=(TextView)findViewById(R.id.textname);
    TextView textphone=(TextView)findViewById(R.id.textphone);
    TextView textemail=(TextView)findViewById(R.id.textemail);
    TextView textresidence=(TextView)findViewById(R.id.textresidence);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button Bsignout =(Button)findViewById(R.id.signout);



        loadprefs();
        Bhom= findViewById(R.id.hm11);
        Bhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
        });


        Bmore= findViewById(R.id.more11);
        Bmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),More.class);
                startActivity(int1);
            }
        });
        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.profiletoolbar);
        setSupportActionBar(toolbar);


    }
    //*******************MENU****************8
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }

//    public void sigingOut(View view){
//
//        SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
//        prefs.edit().clear().commit();
//    }

    public void loadprefs(){

        SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        textname.append(prefs.getString(SURNAME_KEY,"")+" "+prefs.getString(FIRST_NAME_KEY,""));
        textphone.append(prefs.getString(PHONE_NUMBER_KEY,""));
        textemail.append(prefs.getString(EMAIL_ADDRESS_KEY,""));
        textresidence.append(prefs.getString(RESIDENCE_KEY,""));
        Log.d("SHARED PREFS","I HAVE RETRIEVED FROM PREF IN PROFILE");
    }

}
