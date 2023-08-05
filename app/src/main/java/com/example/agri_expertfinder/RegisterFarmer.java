package com.example.agri_expertfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class RegisterFarmer extends AppCompatActivity {

    // text view declaration
    TextView farmerReg;
    TextView expertReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_register_farmer);

        // text view initialization
        farmerReg = findViewById(R.id.farmer_text);
        expertReg = findViewById(R.id.exprt_text);


        // listener for text farmer
        farmerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RegisterFarmer.this,FarmerReg.class);
                startActivity(intent);
                finish();
            }
        });

        //listener for expert text

        expertReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RegisterFarmer.this,ExpertReg.class);
                startActivity(intent);
                finish();


            }
        });
    }
}