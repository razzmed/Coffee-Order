package com.example.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.coffeeorder.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int quantity = 0;
    private double price;
    private ArrayList<String> spinnerArrayList;
    private ArrayAdapter<String> spinnerAdapter;
    private HashMap<String, Double> goodsMap;
    private String goodsName;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createSpinner();
        createMap();
    }

    private void createSpinner() {
        binding.spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Эспрессо");
        spinnerArrayList.add("Американо");
        spinnerArrayList.add("Капучино");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(spinnerAdapter);
    }

    private void createMap() {
        goodsMap = new HashMap<>();
        goodsMap.put("Эспрессо", 1.0);
        goodsMap.put("Американо", 1.5);
        goodsMap.put("Капучино", 2.0);
    }

    public void decrease(View view) {
        if (quantity > 0) {
            quantity -= 1;
        }
        updateViews();
    }

    public void increase(View view) {
        quantity += 1;
        updateViews();
    }

    private void updateViews() {
        binding.quantityTextView.setText(String.valueOf(quantity));
        binding.priceTextView.setText(String.format("$%.2f", quantity * price));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinnerArrayList.get(position);
        price = goodsMap.get(goodsName);
        updateViews();
        updateImage();
    }

    private void updateImage() {
        int imageRes;
        switch (goodsName) {
            case "espresso":
                imageRes = R.drawable.espresso;
                break;
            case "americano":
                imageRes = R.drawable.americano;
                break;
            default:
                imageRes = R.drawable.cappuccino;
                break;
        }
        binding.goodsImageView.setImageResource(imageRes);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // No action needed
    }

    public void addToCard(View view) {
        Order order = new Order();
        order.userName = binding.nameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("Имя", order.userName);
        orderIntent.putExtra("Название товара", order.goodsName);
        orderIntent.putExtra("Количество", order.quantity);
        orderIntent.putExtra("Цена", order.price);
        orderIntent.putExtra("Стоимость заказа", order.orderPrice);

        startActivity(orderIntent);
    }
}