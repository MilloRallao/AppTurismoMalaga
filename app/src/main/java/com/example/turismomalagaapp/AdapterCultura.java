package com.example.turismomalagaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCultura extends RecyclerView.Adapter<AdapterCultura.MyViewHolder> {

    public AdapterCultura(){
    }

    @NonNull
    @Override
    public AdapterCultura.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCultura.MyViewHolder holder, final int position) {
        holder.lugar1.setText("Lugar de cultura 1");
        holder.boton.setText("Ir a lugar 1");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lugar1;
        Button boton;
        MyViewHolder(View v) {
            super(v);
            lugar1 = v.findViewById(R.id.textview_lugar);
            boton = v.findViewById(R.id.button);
        }
    }
}
