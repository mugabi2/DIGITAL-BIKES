package com.example.samuelhimself.bible1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
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

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button Brent,Bevents,Bpromo,Binst,Bmore,Bparking;

    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "my_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        createNotificationChannel();

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
//                Intent int1 =new Intent(getApplicationContext(),Events.class);
//                startActivity(int1);


// Starts the function below
                addNotification();
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

//    creat notification channel

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    // Creates and displays a notification
    private void addNotification() {
        int prio=2;

//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)


        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home11)
                .setContentTitle("Digital Bikes kind reminder")
                .setContentText("You are left with 5 minutes")
                .setPriority(prio)
//                .setSound(R.raw.bbell1)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());


//
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
