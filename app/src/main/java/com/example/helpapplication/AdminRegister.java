package com.example.helpapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.rxjava3.annotations.NonNull;

public class AdminRegister extends AppCompatActivity {

    private EditText email, name, phone;
    private EditText password, cpassword;
    private Button register;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        getSupportActionBar().hide();


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        register = findViewById(R.id.register);

        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_cPassword = cpassword.getText().toString();
                String txt_name = name.getText().toString();
                String txt_phone = phone.getText().toString();

                if (txt_name.isEmpty()) {
                    name.setError("Full name required");
                    name.requestFocus();
                    return;
                }

                if (txt_phone.isEmpty()) {
                    phone.setError("Phone number is required");
                    phone.requestFocus();
                    return;
                }

                if (txt_email.isEmpty()) {
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
                    email.setError("Please provide valid email");
                    email.requestFocus();
                    return;
                }

                if (txt_password.isEmpty()) {
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                if (txt_password.length() < 6) {
                    password.setError("Minimum password should be 6 characters");
                    password.requestFocus();
                    return;
                }

                if (!txt_password.equals(txt_cPassword)) {
                    password.setError("Password doesn't Match! Try Again!");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(AdminRegister.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(txt_name, txt_phone, txt_email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(AdminRegister.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                startActivity(new Intent(AdminRegister.this, AdminLogin.class));
                                            } else {
                                                Toast.makeText(AdminRegister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                            FirebaseDatabase.getInstance().getReference("Users").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("AdminAmbulances").setValue("");
                        } else {
                            Toast.makeText(AdminRegister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
