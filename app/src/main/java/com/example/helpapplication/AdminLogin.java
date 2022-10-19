package com.example.helpapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AdminLogin extends AppCompatActivity {

    private EditText userNameEdt;
    private EditText passwordEdt;
    private Button loginBtn, registerBtn;

    private ProgressBar progressBar2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        getSupportActionBar().hide();


        auth = FirebaseAuth.getInstance();

        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);
        loginBtn = findViewById(R.id.idBtnLogin);
        registerBtn = findViewById(R.id.idBtnRegister);
        progressBar2 = findViewById(R.id.progressBar2);


        // adding on click listener for our button.
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = userNameEdt.getText().toString().trim();
                String txt_password = passwordEdt.getText().toString().trim();

                if(txt_email.isEmpty()){
                    userNameEdt.setError("Email is required");
                    userNameEdt.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
                    userNameEdt.setError("Please provide valid email");
                    userNameEdt.requestFocus();
                    return;
                }

                if(txt_password.isEmpty()){
                    passwordEdt.setError("Password is required");
                    passwordEdt.requestFocus();
                }

                if (txt_password.length() < 6) {
                    passwordEdt.setError("Minimum password should be 6 characters");
                    passwordEdt.requestFocus();
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AdminLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                            startActivity(new Intent(AdminLogin.this, AddAmbulance.class));
                        }else {
                            Toast.makeText(AdminLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminLogin.this, AdminRegister.class);
                startActivity(i);
            }
        });

    }

}

