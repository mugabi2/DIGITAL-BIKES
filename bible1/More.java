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

    Button Bsafe,Bcred,Babout,Bpfile,Bspt,Bhist,Bhm,Bpark, Bsafe1;

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

        Bsafe1= findViewById(R.id.st1);
        Bsafe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),SafetyTips.class);
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
                finish();
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
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://stardigitalbikes.com/terms_and_conditions.php"));
        startActivity(browserIntent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.usermenu:
                Intent int1 =new Intent(getApplicationContext(),Profile.class);
                startActivity(int1);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
