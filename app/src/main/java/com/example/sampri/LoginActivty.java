package com.example.sampri;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivty extends AppCompatActivity {

    //Declaration EditTexts
    EditText etusername;
    EditText etpassword;

    //Declaration Button
    ImageButton btnlogin ;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteHelper = new SqliteHelper(this);
        initRegister();
        initViews();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {

                    String Username = etusername.getText().toString();
                    String Password = etpassword.getText().toString();

                    User currentUser = sqliteHelper.Authenticate(new User(null, Username, Password,null));

                    if (currentUser != null) {
                        Snackbar.make(btnlogin, "Berhasil Login", Snackbar.LENGTH_LONG).show();

                        Intent intent=new Intent(LoginActivty.this,MainActivity.class);

                        startActivity(intent);
                        finish();
                    } else {

                        Snackbar.make(btnlogin, "Gagal Login, Silahkan Coba Lagi", Snackbar.LENGTH_LONG).show();
                    }
                }
            }


        });
    }

    private void initRegister() {
        TextView tvregister = (TextView) findViewById(R.id.tvdaftar);
        tvregister.setText(fromHtml("<font color='#FF000000'>Sudah Punya Akun?. </font><font color='#FF03DAC5'>Buat Akun</font>"));
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivty.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        etusername = (EditText) findViewById(R.id.etusername);
        etpassword = (EditText) findViewById(R.id.etpassword);
        btnlogin = (ImageButton) findViewById(R.id.btnlogin);
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private boolean validate() {
        boolean valid = false;

        String Username = etusername.getText().toString();
        String Password = etpassword.getText().toString();

        if (Username.isEmpty()){
            valid = false;
            etusername.setError("Masukan Username Yang Benar");
        } else {
            if (Username.length() > 5) {
                valid = true;
                etusername.setError(null);
            } else {
                valid = false;
                etusername.setError("Username Terlalu Pendek");
            }
        }

        if (Password.isEmpty()){
            valid = false;
            etpassword.setError("Masukan Password Yang Benar");
        } else {
            if (Password.length() > 5) {
                valid = true;
                etpassword.setError(null);
            } else {
                valid = false;
                etpassword.setError("Password Terlalu Pendek");
            }
        }

        return valid;
    }


}
