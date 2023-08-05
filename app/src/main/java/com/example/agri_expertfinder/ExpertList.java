package com.example.agri_expertfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ExpertList extends AppCompatActivity {
    // edit text declaration
    TextView txtExpert1;
    TextView txtExpert2;
    TextView txtExpert3;
    TextView txtExpert4;
    TextView txtExpert5;
    TextView txtExpert6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_expert_list);

        // initialization
        txtExpert1 = findViewById(R.id.expert_1);
        txtExpert2 = findViewById(R.id.expert_2);
        txtExpert3 = findViewById(R.id.expert_3);
        txtExpert4 = findViewById(R.id.expert_4);
        txtExpert5 = findViewById(R.id.expert_5);
        txtExpert6 = findViewById(R.id.expert_6);

        //listener for txt expert 1

        txtExpert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                Intent intent = new Intent(ExpertList.this,FirstExpert.class);
                startActivity(intent);
                finish();
            }
        });

        // listener for expert 2

        txtExpert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                Intent intent = new Intent(ExpertList.this,SecondExpert.class);
                startActivity(intent);
                finish();

            }
        });

        // listener for expert 3

        txtExpert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ExpertList.this,ThirdExpert.class);
                startActivity(intent);
                finish();

            }
        });

        // listener for expert 4

        txtExpert4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ExpertList.this,FourthExpert.class);
                startActivity(intent);
                finish();

            }
        });

        // listener for expert 5

        txtExpert5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ExpertList.this,FifthExpert.class);
                startActivity(intent);
                finish();

            }
        });

        // listener for expert 6

        txtExpert6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ExpertList.this,SixthExpert.class);
                startActivity(intent);
                finish();

            }
        });
    }

}