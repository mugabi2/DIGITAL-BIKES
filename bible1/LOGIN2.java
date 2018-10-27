package com.example.samuelhimself.bible1;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LOGIN2 extends AppCompatActivity {


    EditText pas,usr;
    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds=21600000; //6 hrs
    private boolean timerRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        countdownText=findViewById(R.id.time1);
        countdownButton=findViewById(R.id.startstop);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimer();
    }

    public void startStop(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }

    }

    public void startTimer(){
        countDownTimer =new CountDownTimer(timeLeftInMilliseconds,60000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds=millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        countdownButton.setText("START");
        timerRunning=true;
    }
    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning=false;
        countdownButton.setText("STOP");

    }

public void updateTimer(){
        int hours=(int)timeLeftInMilliseconds/3600000;
        int minutes=(int)timeLeftInMilliseconds%3600000/60000;

        String timeLeftText;

        timeLeftText=""+hours;
        timeLeftText+=":";
        if (minutes<10)timeLeftText+="0";
        timeLeftText+=minutes;
        countdownText.setText(timeLeftText);
}
}
