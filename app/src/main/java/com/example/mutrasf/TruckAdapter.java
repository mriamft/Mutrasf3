
package com.example.mutrasf;

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

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.Holder> {

    private Context context;
    private ArrayList<String> name, price, phone, id;

    public TruckAdapter(Context context, ArrayList<String> name, ArrayList<String> price, ArrayList<String> phone, ArrayList<String> id) {
        this.context = context;
        this.name = name;
        this.price = price;
        this.phone = phone;
        this.id = id;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaytrucks, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final String truckId = id.get(holder.getAdapterPosition());

        holder.name.setText(name.get(holder.getAdapterPosition()));
        holder.price.setText(price.get(holder.getAdapterPosition()));
        holder.phone.setText(phone.get(holder.getAdapterPosition()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TruckProfileActivity.class);
                intent.putExtra("TRUCK_ID", truckId);
                intent.putExtra("TRUCK_NAME", name.get(holder.getAdapterPosition()));
                intent.putExtra("TRUCK_PRICE", price.get(holder.getAdapterPosition()));
                intent.putExtra("TRUCK_PHONE", phone.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return name.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, price, phone;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.TextName);
            price = itemView.findViewById(R.id.TextPrice);
            phone = itemView.findViewById(R.id.TextContact);
        }
    }
}