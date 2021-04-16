package com.example.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("COVID-19 TRACKER");
        btnHome = findViewById(R.id.btnHome);
    }
    public void btnHomeClick(View v){
        Intent intent = new Intent(HomePage.this,MainActivity.class);
        startActivity(intent);
    }
}