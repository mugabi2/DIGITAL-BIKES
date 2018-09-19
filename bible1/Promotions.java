package com.example.samuelhimself.bible1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Promotions extends AppCompatActivity {

    Button Bhom,Bmore,Bshare;
    TextView Tdigitaltime;
    private String prefName ="prefprofile";
    private static final String RESIDENCE_KEY ="Residence:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);
        SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        String store=prefs.getString(RESIDENCE_KEY,"");

        Tdigitaltime.append(store);

        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.promotoolbar);
        setSupportActionBar(toolbar);


        Bhom= findViewById(R.id.hm12);
        Bhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
        });



        Bmore= findViewById(R.id.more12);
        Bmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),More.class);
                startActivity(int1);
            }
        });

        Bshare= findViewById(R.id.sharebutton);
        Bshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(Intent.ACTION_SEND);
                int1.setType("text/plain");
                String shareBody ="your body here";
                int1.putExtra(Intent.EXTRA_SUBJECT,shareBody);
                int1.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(int1, "Share using"));
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
}
