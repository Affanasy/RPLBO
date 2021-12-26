package com.example.sampri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda);
    }

    public void clickwisata(View view){
        Intent i = new Intent(this,WisataActivity.class);
        startActivity(i);
    }

    public void clicktentang(View view){
        Intent i = new Intent(this,TentangActivity.class);
        startActivity(i);
    }
}