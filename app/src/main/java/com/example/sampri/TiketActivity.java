package com.example.sampri;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class TiketActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiket);
    }

    public void increment(View view){
        if (quantity==100){
            Toast.makeText(this,"Pesanan Maksimal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1;
        display(quantity);
    }



    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }

    public void Submitorder(View view){
        CheckBox kentang= (CheckBox) findViewById(R.id.mobil);
        boolean hasmobil=kentang.isChecked();
        Log.v("TiketActivity","hasmobil: "+hasmobil);

        CheckBox eskrim= (CheckBox) findViewById(R.id.motor);
        boolean hasmotor=eskrim.isChecked();
        Log.v("TiketActivity","hasmotor: "+hasmotor);

        int price=calculateprice(hasmobil,hasmotor);

        String pricemessage=createOrderSummary(price,hasmobil,hasmotor);

        displayMessage(pricemessage);

    }

    private int calculateprice(boolean addmobil, boolean addmotor) {
        int harga=10000;

        if(addmobil){
            harga= (quantity*harga)+5000;
        }

        if (addmotor){
            harga=(quantity*harga)+2000;
        }

        return harga;
    }

    private String createOrderSummary(int price, boolean addmobil, boolean addmotor) {//hasil pemesanan
        String pricemessage ="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.tvharga);
        priceTextView.setText(message);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.tvquantity);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.tvharga);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
