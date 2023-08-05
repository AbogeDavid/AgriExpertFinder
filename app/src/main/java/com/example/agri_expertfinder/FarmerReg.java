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
import com.google.firebase.database.FirebaseDatabase;

public class FarmerReg extends AppCompatActivity {

    // buttons declarations
    android.widget.Button back_login;
    android.widget.Button reg_button;

    //Edit text declaration
    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userConfirmPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_farmer_reg);

        // buttons initialization
        back_login = findViewById(R.id.backtologin_button);
        reg_button = findViewById(R.id.register_button);

        //text views initialization
        userName = findViewById(R.id.name_text);
        userEmail = findViewById(R.id.email_adress);
        userPassword = findViewById(R.id.register_passwordfield);
        userConfirmPassword = findViewById(R.id.confirm_passwordfield);


        // listener for back to login button

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FarmerReg.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // listener for reg button
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textUName = userName.getText().toString();
                String textUEmail = userEmail.getText().toString();
                String textUPassword = userPassword.getText().toString();
                String textUConfirmPassword = userConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(textUName)) {
                    Toast.makeText(FarmerReg.this, "Please enter name", Toast.LENGTH_LONG).show();
                    userName.setError("Name is required");
                    userName.requestFocus();
                }

                // confirm if email is valid, define pattern to manipulate string
                else if (!Patterns.EMAIL_ADDRESS.matcher(textUEmail).matches()) {
                    Toast.makeText(FarmerReg.this, "Valid email is required", Toast.LENGTH_LONG).show();
                    userEmail.setError("Enter valid email");
                    userEmail.requestFocus();
                } else if (TextUtils.isEmpty(textUEmail)) {
                    Toast.makeText(FarmerReg.this, "Please enter email", Toast.LENGTH_LONG).show();
                    userEmail.setError("Email is required");
                    userEmail.requestFocus();

                } else if (TextUtils.isEmpty(textUPassword)) {

                    Toast.makeText(FarmerReg.this, "Please enter your password", Toast.LENGTH_LONG).show();
                    userPassword.setError("Password is required");
                    userPassword.requestFocus();
                } else if (TextUtils.isEmpty(textUConfirmPassword)) {
                    Toast.makeText(FarmerReg.this, "Please confirm password", Toast.LENGTH_LONG).show();
                    userConfirmPassword.setError("Confirm password is required");
                    userConfirmPassword.requestFocus();
                }
                // confirm if password is matching
                else if (!textUPassword.equals(textUConfirmPassword)) {

                    Toast.makeText(FarmerReg.this, "Same password is required", Toast.LENGTH_LONG).show();
                    userConfirmPassword.setError("Password does not match");
                    userConfirmPassword.requestFocus();

                    // clear password fields
                    userPassword.clearComposingText();
                    userConfirmPassword.clearComposingText();
                }
                // if user details are valid now register him or her on the app
                else {
                    //call registerUser method and pass all the variables

                    registerUser(textUName, textUEmail, textUPassword);

                }

            }

            private void registerUser(String textUName, String textUEmail, String textUPassword) {
                // start the firebase
                //obtain the instance of firebase authentication

                FirebaseAuth auth = FirebaseAuth.getInstance();

                // using auth variable create one user using email and password
                auth.createUserWithEmailAndPassword(textUEmail, textUPassword).addOnCompleteListener(FarmerReg.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            // when account is created this onComplete is executed
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(FarmerReg.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FarmerReg.this, SpecificArea.class);
                                    startActivity(intent);
                                    finish();
                                    // access firebase database
                                    FirebaseDatabase.getInstance().getReference("Farmers/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new Farmer(userName.getText().toString(),
                                        userEmail.getText().toString()));

                                    // send email verification link so that he is really the owner

                                    //  FirebaseUser firebaseUser = auth.getCurrentUser();
                                    // now send link to email

                                    // firebaseUser.sendEmailVerification();

                                }


                            }


                });
            }
        });


    }
}