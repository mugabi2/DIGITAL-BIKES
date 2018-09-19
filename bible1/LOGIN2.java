package com.example.samuelhimself.bible1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LOGIN2 extends AppCompatActivity {


    EditText pas,usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        usr=(EditText)findViewById(R.id.username);
        pas=(EditText)findViewById(R.id.psd);


    }
    public void loginBtn(View view){

        String user =usr.getText().toString();
        String pass =pas.getText().toString();

        background bg = new background(this);
        bg.execute(user,pass);
    }
}
