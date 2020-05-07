package com.example.turismomalagaapp.ui.gastronomia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterGastronomia extends RecyclerView.Adapter<AdapterGastronomia.MyViewHolder> {

    public AdapterGastronomia(){
    }

    @NonNull
    @Override
    public AdapterGastronomia.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gastronomia_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterGastronomia.MyViewHolder holder, final int position) {
        holder.lugar1.setText("Resturante");
        holder.boton.setText("Ir a resturante");
        holder.imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView lugar1;
        Button boton;
        ImageView imageView;
        MyViewHolder(View v) {
            super(v);
            lugar1 = v.findViewById(R.id.textview);
            boton = v.findViewById(R.id.button);
            imageView = v.findViewById(R.id.imageView);
        }
    }
}
