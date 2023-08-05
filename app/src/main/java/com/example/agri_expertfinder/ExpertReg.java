package com.example.agri_expertfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ExpertReg extends AppCompatActivity {


    // button declaration
    android.widget.Button exRegButton;


    // text field declaration
    EditText exName;
    EditText exLocation;
    EditText exEmail;
    EditText exPassword;
    EditText exConfirmPassword;

    // button decleration

    android.widget.Button login_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_expert_reg);

        //initialize button back to login
        login_back = findViewById(R.id.backtologin_expert_button);
        exRegButton = findViewById(R.id.register_button);

        // Edit text initialization
        exName = findViewById(R.id.name_text);
        exLocation = findViewById(R.id.loc_name);
        exEmail = findViewById(R.id.email_adress);
        exPassword = findViewById(R.id.register_passwordfield);
        exConfirmPassword = findViewById(R.id.confirm_passwordfield);



            //listener for back to login button
        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent intent = new Intent(ExpertReg.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        // listener for register button

        exRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textExName = exName.getText().toString();
                String textExLoc = exLocation.getText().toString();
                String textExEmail = exEmail.getText().toString();
                String textExPassword = exPassword.getText().toString();
                String textExConfirmPass = exConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(textExName)) {
                    Toast.makeText(ExpertReg.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
                    exName.setError("Name required");
                    exName.requestFocus();
                } else if (TextUtils.isEmpty(textExLoc)) {
                    Toast.makeText(ExpertReg.this, "Please enter location", Toast.LENGTH_SHORT).show();
                    exLocation.setError("Location required");
                    exLocation.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(textExEmail).matches()) {

                    Toast.makeText(ExpertReg.this, "Valid email is required", Toast.LENGTH_LONG).show();
                    exEmail.setError("Enter valid email");
                    exEmail.requestFocus();
                } else if (TextUtils.isEmpty(textExEmail)) {
                    Toast.makeText(ExpertReg.this, "Please enter email", Toast.LENGTH_LONG).show();
                    exEmail.setError("Email is required");
                    exEmail.requestFocus();

                } else if (TextUtils.isEmpty(textExPassword)) {
                    Toast.makeText(ExpertReg.this, "Please enter password", Toast.LENGTH_LONG).show();
                    exPassword.setError("Password is required");
                    exPassword.requestFocus();


                }
                /**
                 else if(!PASSWORD_PARTTERN.matcher(textExPassword).matches())
                 {

                 Toast.makeText(RegisterExpert.this,"Password is weak", Toast.LENGTH_LONG).show();
                 exPassword.setError("Password must include mixed eight characters");
                 exPassword.requestFocus();

                 } */
                else if (TextUtils.isEmpty(textExConfirmPass)) {
                    Toast.makeText(ExpertReg.this, "Please confirm password", Toast.LENGTH_LONG).show();
                    exConfirmPassword.setError("Confirm password is required");
                    exConfirmPassword.requestFocus();
                } else if (!textExPassword.equals(textExConfirmPass)) {
                    Toast.makeText(ExpertReg.this, "Same password is required", Toast.LENGTH_LONG).show();
                    exConfirmPassword.setError("Password does not match");
                    exConfirmPassword.requestFocus();

                    // clear password fields
                    exConfirmPassword.clearComposingText();
                    exConfirmPassword.clearComposingText();
                } else
                {
                    registerUser(textExEmail, textExPassword);
                }

            }


            private void registerUser(String textEEmail, String textEPassword)
            {
                // start the firebase
                //obtain the instance of firebase authentication

                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.createUserWithEmailAndPassword(textEEmail, textEPassword).addOnCompleteListener(ExpertReg.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override

                            // when account is created this onComplete is executed
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {

                                if(task.isSuccessful())
                                {

                                    Toast.makeText(ExpertReg.this,"Successfully registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ExpertReg.this,ExpertReg.class);
                                    startActivity(intent);
                                    finish();

                                    // access firebase database
                                   FirebaseDatabase.getInstance().getReference("Experts/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new Eperts(exName.getText().toString(),
                                           exLocation.getText().toString(), exEmail.getText().toString(),exPassword.getText().toString()));


                                    // send email verification link so that he is really the owner

                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    // now send link to email

                                    firebaseUser.sendEmailVerification();
                                }

                            }
                        });

            }
        });

    }


}