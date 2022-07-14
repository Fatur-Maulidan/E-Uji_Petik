package com.example.e_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Preferences.getKEY_Token(getBaseContext()) != ""){
            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                    finish();
                }
            }, 3000L);
        }
        else if(Preferences.getKEY_Token(getBaseContext()) == ""){

            final Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, Login_Activity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000L);
        }
    }
}