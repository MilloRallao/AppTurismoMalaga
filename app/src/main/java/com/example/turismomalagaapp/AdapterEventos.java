package com.example.turismomalagaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.MyViewHolder> {

    public AdapterEventos() {

    }

    @NonNull
    @Override
    public AdapterEventos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.pepito.setText("SOY PEPITO");
        holder.juanito.setText("SOY JUANITO");
        holder.boton.setText("SOY UN BOTON");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pepito;
        TextView juanito;
        Button boton;
        MyViewHolder(View v) {
            super(v);
            pepito = v.findViewById(R.id.textview_pepito);
            juanito = v.findViewById(R.id.textview_juanito);
            boton = v.findViewById(R.id.button_boton);
        }
    }
}
