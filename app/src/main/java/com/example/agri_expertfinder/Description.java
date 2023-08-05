package com.example.agri_expertfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Description extends AppCompatActivity {

    private Button chooseExpert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_description);

        // button initialization
        chooseExpert = findViewById(R.id.butExpert);

        // listener for button

        chooseExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(Description.this,ExpertList.class);
                startActivity(intent);
                finish();


            }
        });
    }
}