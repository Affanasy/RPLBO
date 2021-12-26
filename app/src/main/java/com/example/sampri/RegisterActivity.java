package com.example.sampri;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    EditText etusername;
    EditText etpassword;
    EditText etnohp;


    ImageButton btndaftar;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String Username = etusername.getText().toString();
                    String Password = etpassword.getText().toString();
                    String Nohp = etnohp.getText().toString();

                    if (!sqliteHelper.isNomorExist(Nohp)) {

                        sqliteHelper.addUser(new User(null, Username, Password, Nohp));

                        Snackbar.make(btndaftar, "Akun Berhasil Dibuat! Silahkan Login", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    } else {

                        Snackbar.make(btndaftar, "Akun Sudah Ada", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    private void initTextViewLogin() {
        TextView tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }

    private void initViews() {
        etusername = (EditText) findViewById(R.id.etusername);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etnohp = (EditText) findViewById(R.id.etnohp);

        btndaftar = (ImageButton) findViewById(R.id.btndaftar);
    }

    public boolean validate() {
        boolean valid = false;

        String Username = etusername.getText().toString();
        String Password = etpassword.getText().toString();
        String Nohp = etnohp.getText().toString();

        if (Username.isEmpty()) {
            valid = false;
            etusername.setError("Masukan Username Yang Valid!");
        } else {
            if (Username.length() > 5) {
                valid = true;
                etusername.setError(null);
            } else {
                valid = false;
                etusername.setError("Username Terlalu Pendek!");
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Nohp).matches()) {
            valid = false;
            etnohp.setError("Please enter valid email!");
        } else {
            valid = true;
            etnohp.setError(null);
        }

        if (Password.isEmpty()) {
            valid = false;
            etpassword.setError("Masukan Password Yang Valid!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                etpassword.setError(null);
            } else {
                valid = false;
                etpassword.setError("Password Terlalu Pendek!");
            }
        }

        return valid;
    }
}
