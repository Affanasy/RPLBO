package com.example.sampri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TentangActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wisata);
    }

    public void clickinfo(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void clickwisata(View view){
        Intent i = new Intent(this,WisataActivity.class);
        startActivity(i);
    }
}
