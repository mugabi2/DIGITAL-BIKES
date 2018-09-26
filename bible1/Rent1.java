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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.samuelhimself.bible1.R.id.cash1;
import static com.example.samuelhimself.bible1.R.id.digitaltime1;
import static com.example.samuelhimself.bible1.R.id.fulltime;
import static com.example.samuelhimself.bible1.R.id.radio_female;

public class Rent1 extends AppCompatActivity {

    Button Bhom,Bmore,Bproceed;
    String duration="half",payment="cash";
    TextView Tcurrency;
     int checkP=0,checkD=0,price,durationInt,paymentInt;
     static final int rentPrice=1500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent1);

        Bhom= findViewById(R.id.hm13);
        Bhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(int1);
            }
        });


        Bproceed= findViewById(R.id.proceed);
        Bproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkP>=1&checkD>=1) {
//LATEST ISSUEI IS HERER ERER HERE HRERE
//                    if (duration.equals("half")) {
//                        price=rentPrice*durationInt;

                  RadioButton Rdigitime=(RadioButton)findViewById(R.id.digitaltime1);
                            if (Rdigitime.isChecked()) {
                                Toast.makeText(getApplicationContext(), "Please use cash Digital Time coming soon", Toast.LENGTH_SHORT).show();
                            }
                     else {
                                Intent int1 = new Intent(getApplicationContext(), databaseConnect.class);
                                int1.putExtra("DurationInt", durationInt);
                                int1.putExtra("Duration", duration);
                                int1.putExtra("PaymethodInt", paymentInt);
                                int1.putExtra("Paymethod", payment);
                                startActivity(int1);
                    }
//                    }
                }
                else {
                   Toast.makeText(getApplicationContext(), "please check the buttons", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Bmore= findViewById(R.id.more13);
        Bmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 =new Intent(getApplicationContext(),More.class);
                startActivity(int1);
            }
        });


        //  -------------toolbar---------
        Toolbar toolbar =findViewById(R.id.rentbar);
        setSupportActionBar(toolbar);



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

    public void onRadioButtonClickedDuration(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.halftime:
                if (checked){
                    duration="half";
                    durationInt=1;
                    checkD++;
                }
                break;
            case fulltime:
                if (checked){
                    duration="full";
                    durationInt=2;
                    checkD++;
                }
                break;
        }

    }

    public void onRadioButtonClickedPayment(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cash1:
                if (checked){
                    payment="cash";
                    paymentInt=1;
                    checkP++;
                }
                break;
            case digitaltime1:
                if (checked){
//                    payment="DT";
//                    paymentInt=2;
//                    checkP++;
                    Toast.makeText(getApplicationContext(), "Not yet available Coming soon", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
