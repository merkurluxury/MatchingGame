package com.example.matchinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button5x4;
    Button button6x5;

    TextView sure4x4,puan4x4,sure6x6,puan6x6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button5x4 = (Button) findViewById(R.id.button1);
        button5x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openGame5x4Activity();
            }
        });

        sure4x4 = findViewById(R.id.sure4x4);
        puan4x4 = findViewById(R.id.puan4x4);
        sure6x6 = findViewById(R.id.sure6x6);
        puan6x6 = findViewById(R.id.puan6x6);

        SharedPreferences settings = getSharedPreferences("appName",0);
        String asure4x4 = settings.getString("sured","Kayıt Yok");
        String apuan4x4 = settings.getString("puand","Kayıt Yok");
        String asure6x6 = settings.getString("surea","Kayıt Yok");
        String apuan6x6 = settings.getString("puana","Kayıt Yok");
        sure4x4.setText(asure4x4);
        puan4x4.setText(apuan4x4);
        sure6x6.setText(asure6x6);
        puan6x6.setText(apuan6x6);

        button6x5 = (Button) findViewById(R.id.button2);
        button6x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openGame6x5Activity();
            }
        });


    }

    public void openGame5x4Activity(){
        Intent intent5x4 = new Intent(this, Game4x4Activity.class);
        startActivity(intent5x4);
        finish();
    }

    public void openGame6x5Activity(){
        Intent intent6x5 = new Intent(this, Game6x6Activity.class);
        startActivity(intent6x5);
        finish();
    }
}
