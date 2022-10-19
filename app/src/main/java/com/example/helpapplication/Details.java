package com.example.helpapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Details extends AppCompatActivity {

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().hide();

        String tCompanyName = getIntent().getStringExtra("companyName");
        TextView companyName = findViewById(R.id.companyName);
        companyName.setText(tCompanyName);

        String tLandmark = getIntent().getStringExtra("landmark");
        TextView landmark = findViewById(R.id.landmark);
        landmark.setText(tLandmark);

        String tLocation = getIntent().getStringExtra("location");
        TextView location = findViewById(R.id.location);
        location.setText(tLocation);

        String tPersonnel = getIntent().getStringExtra("personnel");
        TextView personnel = findViewById(R.id.personnel);
        personnel.setText(tPersonnel);

        String tPhoneNumber = getIntent().getStringExtra("phoneNumber");
        TextView phoneNumber = findViewById(R.id.phoneNumber);
        phoneNumber.setText(tPhoneNumber);

        String tPlateNumber = getIntent().getStringExtra("plateNumber");
        TextView plateNumber = findViewById(R.id.plateNumber);
        plateNumber.setText(tPlateNumber);

        String tVehicleType = getIntent().getStringExtra("vehicleType");
        TextView vehicleType = findViewById(R.id.vehicleType);
        vehicleType.setText(tVehicleType);

        // Getting instance of edittext and button
        button1 = findViewById(R.id.btnCall);
        button2 = findViewById(R.id.btnFav);

        // Attach set on click listener to the button for initiating intent
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting instance of Intent with action as ACTION_CALL
                Intent phone_intent = new Intent(Intent.ACTION_DIAL);

                // Set data of Intent through Uri by parsing phone number
                phone_intent.setData(Uri.parse("tel:" + tPhoneNumber.toString()));

                // start Intent
                startActivity(phone_intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Details.this, "Added to Favourites!",
                        Toast.LENGTH_SHORT).show();

                model obj = new model(tCompanyName, tLandmark, tLocation, tPersonnel, tPhoneNumber, tPlateNumber, tVehicleType);

//                Write orders to database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Favourites");

                myRef.child(String.valueOf(obj.companyName)).setValue(obj);
            }
        });

        }
}