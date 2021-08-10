package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

    private ImageView shapes, animals, plants, cars;
    private Button logoutBtn, checkOrderBtn, maintainProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        logoutBtn = findViewById(R.id.admin_logout_btn);
        checkOrderBtn = findViewById(R.id.check_orders_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,LoginIntro.class);
                intent.putExtra("Admin","Admin");
                startActivity(intent);

            }
        });



        checkOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminNewOrdersActivity.class);
                startActivity(intent);

            }
        });

        shapes = findViewById(R.id.shapes);
        animals = findViewById(R.id.animals_figures);
        plants = findViewById(R.id.plants_figures);
        cars = findViewById(R.id.cars_figures);



        shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","shapes");
                startActivity(intent);
            }
        });


        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","animals");
                startActivity(intent);
            }
        });

        plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","plants");
                startActivity(intent);
            }
        });

        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddNewProductActivity.class);
                intent.putExtra("category","cars");
                startActivity(intent);
            }
        });
    }
}