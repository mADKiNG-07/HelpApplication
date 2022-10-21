package com.example.helpapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateAmbDetails extends AppCompatActivity {

    Button button;
    DatabaseReference reference;
    TextView ngcompanyName,nglandmark,nglocation,ngpersonnel,ngphoneNumber,ngplateNumber,ngvehicleType;

    ImageButton buttontt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_amb_details);

        getSupportActionBar().hide();

        buttontt = findViewById(R.id.back);
        buttontt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

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

        button = findViewById(R.id.update);

//        companyName = findViewById(R.id.ngcompanyName);
        nglandmark = findViewById(R.id.nglandmark);
        nglocation = findViewById(R.id.nglocation);
        ngpersonnel = findViewById(R.id.ngpersonnel);
        ngphoneNumber = findViewById(R.id.ngphoneNumber);
        ngplateNumber = findViewById(R.id.ngplateNumber);
        ngvehicleType = findViewById(R.id.ngvehicleType);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tdLandmark = nglandmark.getText().toString();
                String tdLocation = nglocation.getText().toString();
                String tdPersonnel = ngpersonnel.getText().toString();
                String tdPhoneNumber = ngphoneNumber.getText().toString();
                String tdPlateNumber = ngplateNumber.getText().toString();
                String tdVehicle = ngvehicleType.getText().toString();

              updateData(tCompanyName,tdLandmark,tdLocation,tdPersonnel,tdPhoneNumber,tdPlateNumber,tdVehicle);

            }
        });


    }

    private void updateData(String tc,String tdLandmark, String tdLocation, String tdPersonnel, String tdPhoneNumber, String tdPlateNumber, String tdVehicle) {
        HashMap Details = new HashMap();
        Details.put("companyName", tc);
        Details.put("landmark",tdLandmark);
        Details.put("location",tdLocation);
        Details.put("personnel",tdPersonnel);
        Details.put("phoneNumber",tdPhoneNumber);
        Details.put("plateNumber",tdPlateNumber);
        Details.put("vehicleType",tdVehicle);

        reference = FirebaseDatabase.getInstance().getReference("Ambulances");
        reference.child(tc).updateChildren(Details).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(UpdateAmbDetails.this, "Update Successful!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(UpdateAmbDetails.this, "Update Failed! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}