package com.sheraz.stsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sheraz.stsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    MaterialTextView user_signin;
    MaterialButton user_signup;
    TextInputEditText user_name, user_email, user_phone, user_passowrd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        user_signin = findViewById(R.id.user_signin_link);
        user_signup = findViewById(R.id.signup_submit_btn);

        user_name = findViewById(R.id.signup_name_input);
        user_email = findViewById(R.id.signup_email_input);
        user_phone = findViewById(R.id.signup_phone_input);
        user_passowrd = findViewById(R.id.signup_password_input);



        user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uName, uEmail, uPhone, uPass;

                uName = user_name.getText().toString().trim();
                uEmail = user_email.getText().toString().trim();
                uPhone = user_phone.getText().toString().trim();
                uPass = user_passowrd.getText().toString().trim();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //email pattern template


                //Email Data Validation
                if (uEmail.isEmpty()) {
                    user_email.setError("Email is Required");
                    user_email.requestFocus();
                    return;
                }
                //Email Pattern Validation
                else if (!uEmail.matches(emailPattern)) {
                    user_email.setError("Email is Invalid");
                    user_email.requestFocus();
                    return;
                }
                //Password Validation
                else if (uPass.length() < 6) {
                    user_passowrd.setError("Password Must Contains at Least 6 Characters");
                    user_passowrd.requestFocus();
                    return;
                }

                //Process User to Signup after Validation
                else {
                    Log.d("Test", "Signup Auth Start ");

                    User nuser = new User(uName, uEmail, uPhone, uPass);
                    Log.d("Test", "User Class Created");


                    db.collection("Users")
                            .add(nuser)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("Test", "User Class Created in DB Success "+ documentReference.getId());

                                    Toast.makeText(SignupActivity.this, "User Account Created Successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("Test", "User Created Failed " + e);

                                    Toast.makeText(SignupActivity.this, "Error Occurred User Account Not Created", Toast.LENGTH_LONG).show();
                                }


                    }); //End of Firebase Firestore Authentication Body


                } // Else Body of Auth


            }
        }); //End of Signup Button onClick Body



    }
}