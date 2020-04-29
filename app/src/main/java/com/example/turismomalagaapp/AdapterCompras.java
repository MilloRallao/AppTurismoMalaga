package com.example.turismomalagaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCompras extends RecyclerView.Adapter<AdapterEventos.MyViewHolder> {

    public AdapterCompras() {
    }

    @NonNull
    @Override
    public AdapterEventos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_cardview, parent, false);
        return new AdapterEventos.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterEventos.MyViewHolder holder, final int position) {
        holder.pepito.setText("Item 1");
        holder.boton.setText("Boton de compra");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView compra;
        Button comprabotton;

        MyViewHolder(View v) {
            super(v);
            compra = v.findViewById(R.id.textview_compras);
            comprabotton = v.findViewById(R.id.button_compra);
        }
    }
}
