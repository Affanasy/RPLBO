package com.example.sampri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WisataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wisata);
    }

    public void clickinfo(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void clicktentang(View view){
        Intent i = new Intent(this,TentangActivity.class);
        startActivity(i);
    }

    public void clicksiak(View view){
        Intent i = new Intent(this,TiketActivity.class);
        startActivity(i);
    }
}
