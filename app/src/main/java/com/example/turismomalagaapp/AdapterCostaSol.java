package com.example.turismomalagaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCostaSol extends RecyclerView.Adapter<AdapterCostaSol.MyViewHolder> {

    public AdapterCostaSol(){
    }

    @NonNull
    @Override
    public AdapterCostaSol.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costasol_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCostaSol.MyViewHolder holder, final int position) {
        holder.lugar.setText("Lugar 1");
        holder.boton.setText("Ir al lugar 1");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lugar;
        Button boton;
        MyViewHolder(View v) {
            super(v);
            lugar = v.findViewById(R.id.textview_lugar);
            boton = v.findViewById(R.id.button);
        }
    }
}
