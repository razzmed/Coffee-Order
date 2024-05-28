package com.example.coffeeorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Order");

        Intent receivedIntent = getIntent();
        String userName = receivedIntent.getStringExtra("userName");
        String goodsName = receivedIntent.getStringExtra("goodsName");
        int quantity = receivedIntent.getIntExtra("quantity", 0);
        double price = receivedIntent.getDoubleExtra("price", 0);
        double orderPrice = receivedIntent.getDoubleExtra("orderPrice", 0);

        String text = "Имя: " + userName + "\n" + "Название товара: " + goodsName + "\n" + "Количество: " + quantity + "\n" + "Цена: $" + price + "\n" + "Стоимость заказа: $" + orderPrice;

        TextView textView = findViewById(R.id.orderTextView);
        textView.setText(text);
    }

    public void orderSubmit(View view) {
        Toast.makeText(this, "Заказ оформлен!", Toast.LENGTH_SHORT).show();
    }
}