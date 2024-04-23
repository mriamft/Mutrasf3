
package com.example.mutrasf;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.Holder> {

    public interface OnBtnClicked{
        void onBtnClickedListener(int pos);
    }



    private Context context;
    private ArrayList name,  date , time, id;

    private OnBtnClicked listener;


    public ReservationAdapter(Context context, ArrayList name, ArrayList date, ArrayList time, ArrayList id) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public void SetBtn(OnBtnClicked onBtnClicked){
        listener = onBtnClicked;
    }




    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaytrucks, parent, false);
        return new Holder(v , listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name.setText(String.valueOf(name.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
        holder.time.setText(String.valueOf(time.get(position)));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, date, time;


        public Holder(@NonNull View itemView,OnBtnClicked onBtnClicked) {
            super(itemView);
            name = itemView.findViewById(R.id.TextName);
            date = itemView.findViewById(R.id.TextDate);
            time= itemView.findViewById(R.id.TextTime);


        }
    }
}


