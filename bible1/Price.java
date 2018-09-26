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
import android.widget.TextView;

public class Price extends AppCompatActivity {

    Button Bsub;
    TextView Tprice,Ttime,Tbike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        Bsub= findViewById(R.id.sub1);
        Bsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),returnBike.class);
                startActivity(int1);
            }
        });
        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.pricetoolbar);
        setSupportActionBar(toolbar);

        Bundle extras=getIntent().getExtras();
        int cash=extras.getInt("px");
        int time=extras.getInt("yt");
        String bikeno=extras.getString("bn");

        Tprice=(TextView)findViewById(R.id.textprice1);
        Tprice.setText(Integer.toString(cash));

        Ttime=(TextView)findViewById(R.id.textyourtime);
        Ttime.setText(Integer.toString(time));

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
