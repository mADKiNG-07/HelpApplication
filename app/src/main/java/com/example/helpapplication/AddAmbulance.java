package com.example.helpapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAmbulance extends AppCompatActivity {

    TextView companyName,landmark,location,personnel,phoneNumber,plateNumber,vehicleType;
    private Button addAmbulance, viewAmbulance, backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ambulance);

        getSupportActionBar().hide();


        companyName = findViewById(R.id.ngcompanyName);
        landmark = findViewById(R.id.nglandmark);
        location = findViewById(R.id.nglocation);
        personnel = findViewById(R.id.ngpersonnel);
        phoneNumber = findViewById(R.id.ngphoneNumber);
        plateNumber = findViewById(R.id.ngplateNumber);
        vehicleType = findViewById(R.id.ngvehicleType);



        addAmbulance = findViewById(R.id.addAmb);
        viewAmbulance = findViewById(R.id.viewAmb);
        backToHome = findViewById(R.id.backHome);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddAmbulance.this, MainActivity.class);
                startActivity(i);
            }
        });

        viewAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddAmbulance.this, AdminViewAmbulance.class);
                startActivity(i);
            }
        });

        addAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tCompanyName = companyName.getText().toString();
                String tLandmark = landmark.getText().toString();
                String tLocation = location.getText().toString();
                String tPersonnel = personnel.getText().toString();
                String tPhoneNumber = phoneNumber.getText().toString();
                String tPlateNumber = plateNumber.getText().toString();
                String tVehicle = vehicleType.getText().toString();

                Toast.makeText(AddAmbulance.this, "Ambulance Added Successfully!",
                        Toast.LENGTH_SHORT).show();

                model obj = new model(tCompanyName,tLandmark,tLocation,tPersonnel,tPhoneNumber,tPlateNumber,tVehicle);
//               Write Ambulance to database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("Users")
//                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .child("Orders");

                DatabaseReference myRef = database.getReference("Ambulances");
                myRef.child(String.valueOf(obj.getCompanyName())).setValue(obj);
            }
        });

    }
}