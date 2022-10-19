package com.example.helpapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FavouriteAdapter extends FirebaseRecyclerAdapter<model,FavouriteAdapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FavouriteAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FavouriteAdapter.myviewholder holder, int position, @NonNull model model) {
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

                // Getting instance of Intent with action as ACTION_CALL
                Intent phone_intent = new Intent(Intent.ACTION_DIAL);

                // Set data of Intent through Uri by parsing phone number
                phone_intent.setData(Uri.parse("tel:" + model.getPhoneNumber().toString()));

                // start Intent
                context.startActivity(phone_intent);
            }
        });
        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Details.class);
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

    @NonNull
    @Override
    public FavouriteAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favouriterow,parent,false);
        return new FavouriteAdapter.myviewholder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getRef().removeValue();
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView companyName,landmark,location,personnel,phoneNumber,plateNumber,vehicleType;
        Button button1, button2;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Details.class);
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
            button1=(Button)itemView.findViewById(R.id.btnCall4);
            button2=(Button)itemView.findViewById(R.id.btnInfo);

        }
    }
}
