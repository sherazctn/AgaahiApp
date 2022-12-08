package com.sheraz.stsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    MaterialTextView signUp;
    MaterialButton signIn;
    TextInputEditText user_email, user_passowrd;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        user_email = findViewById(R.id.signin_email_input);
        user_passowrd = findViewById(R.id.signin_password_input);

        signIn = findViewById(R.id.signin_submit);




        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uEmail, uPass;
                uEmail = user_email.getText().toString().trim();
                uPass = user_passowrd.getText().toString().trim();




                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"; //email pattern template

                //Email Data Validation
                if (uEmail.isEmpty()) {
                    user_email.setError("Email is Required");
                    user_email.requestFocus();
                    return;
                }
                //Email Pattern Validation
                else if(!uEmail.matches(emailPattern)){
                    user_email.setError("Email is Invalid");
                    user_email.requestFocus();
                    return;
                }
                //Password Validation
                else if(uPass.length()<6){
                    user_passowrd.setError("Password Must Contains at Least 6 Characters");
                    user_passowrd.requestFocus();
                    return;
                }

                else {
                    db.collection("Users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("Test", "Sign In Successfully "+document.getId() + " => " + document.getData());

                                            Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                                        }
                                    } else {
                                        Log.d("Test", "Sign In Failed "+task.getException());

                                    }
                                }
                    });
                }

                Log.d("Test", "End Of Firebase");


            }
        });


    }

}