package com.example.helpapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminSearchAdapter  extends RecyclerView.Adapter<AdminSearchAdapter.SearchAdapterViewHolder>{

    public Context context;
    public ArrayList<model> arrayList;

    public AdminSearchAdapter(Context context, ArrayList<model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public AdminSearchAdapter.SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.updaterow,parent,false);
        return new AdminSearchAdapter.SearchAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSearchAdapter.SearchAdapterViewHolder holder, int position) {
        model model = arrayList.get(position);

        holder.companyName.setText(model.getCompanyName());
        holder.landmark.setText(model.getLandmark());
        holder.location.setText(model.getLocation());
        holder.personnel.setText(model.getPersonnel());
        holder.phoneNumber.setText(model.getPhoneNumber());
        holder.plateNumber.setText(model.getPlateNumber());
        holder.vehicleType.setText(model.getVehicleType());
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, UpdateAmbDetails.class);
                intent.putExtra("companyName", holder.companyName.getText());
                intent.putExtra("landmark", holder.landmark.getText());
                intent.putExtra("location", holder.location.getText());
                intent.putExtra("personnel", holder.personnel.getText());
                intent.putExtra("phoneNumber", holder.phoneNumber.getText());
                intent.putExtra("plateNumber", holder.plateNumber.getText());
                intent.putExtra("vehicleType", holder.vehicleType.getText());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SearchAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView companyName,landmark,location,personnel,phoneNumber,plateNumber,vehicleType;
        Button button1;

        public SearchAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, UpdateAmbDetails.class);
                    intent.putExtra("companyName", companyName.getText());
                    intent.putExtra("landmark", landmark.getText());
                    intent.putExtra("location", location.getText());
                    intent.putExtra("personnel", personnel.getText());
                    intent.putExtra("phoneNumber", phoneNumber.getText());
                    intent.putExtra("plateNumber", plateNumber.getText());
                    intent.putExtra("vehicleType", vehicleType.getText());

                    context.startActivity(intent);
                }
            });

//            img=(CircleImageView)itemView.findViewById(R.id.img1);
            companyName=(TextView)itemView.findViewById(R.id.companyName);
            landmark=(TextView)itemView.findViewById(R.id.landmark);
            location=(TextView)itemView.findViewById(R.id.location);
            personnel=(TextView)itemView.findViewById(R.id.personnel);
            phoneNumber=(TextView)itemView.findViewById(R.id.phoneNumber);
            plateNumber=(TextView)itemView.findViewById(R.id.plateNumber);
            vehicleType=(TextView)itemView.findViewById(R.id.vehicleType);
            button1=(Button)itemView.findViewById(R.id.btnInfo);

        }
    }
}
