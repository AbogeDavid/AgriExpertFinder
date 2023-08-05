package com.example.agri_expertfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    // text views declaration
    TextView reg_text;

    // edit text declaration
    EditText log_emailtext;
    EditText log_passwordtext;

    //reg button declaration
    android.widget.Button login_button;
    android.widget.Button delete_button;


    FirebaseAuth loguth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        loguth = FirebaseAuth.getInstance();

        // text view initialization
        reg_text = findViewById(R.id.register_text);

        //edit text initialization
        log_emailtext = findViewById(R.id.get_email);
        log_passwordtext = findViewById(R.id.get_password);



        // buttons initialization
        login_button = findViewById(R.id.button_login);
        delete_button = findViewById(R.id.button_cleartext);

        // delete button listener
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                log_emailtext.getText().clear();
                log_passwordtext.getText().clear();

            }
        });


        // login button listener
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                login();
            }
        });

        // listener delete button




        // listener for reg text
        reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(MainActivity.this,RegisterFarmer.class);
                startActivity(intent);
               finish();

            }
        });

    }
    // called by log in onclick listener
    private void login()
    {

        String userE = log_emailtext.getText().toString().trim();
        String pass = log_passwordtext.getText().toString().trim();

        if(userE.isEmpty())
        {


            log_emailtext.setError("Email can't be empty ");
            log_emailtext.requestFocus();

        }
        else if(pass.isEmpty())
        {
            log_passwordtext.setError("Password is required");
            log_passwordtext.requestFocus();
        }
        else
        {
            loguth.signInWithEmailAndPassword(userE, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {

                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this,SpecificArea.class);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Login failed"+ task.getException(), Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }
    }

}