package com.example.turismomalagaapp.ui.alojamientos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdapterAlojamiento extends RecyclerView.Adapter<AdapterAlojamiento.MyViewHolder>  {

    public AdapterAlojamiento(){
    }

    @NonNull
    @Override
    public AdapterAlojamiento.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alojamiento_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterAlojamiento.MyViewHolder holder, final int position) {
        holder.estrella1.setVisibility(View.VISIBLE);
        holder.estrella2.setVisibility(View.VISIBLE);
        holder.estrella3.setVisibility(View.VISIBLE);
        holder.estrella4.setVisibility(View.VISIBLE);
        holder.estrella5.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, estrella1, estrella2, estrella3, estrella4, estrella5;
        FloatingActionButton ver_alojamiento;
        MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView_alojamiento);
            estrella1 = v.findViewById(R.id.estrella1);
            estrella2 = v.findViewById(R.id.estrella2);
            estrella3 = v.findViewById(R.id.estrella3);
            estrella4= v.findViewById(R.id.estrella4);
            estrella5= v.findViewById(R.id.estrella5);
            ver_alojamiento = v.findViewById(R.id.floatingActionButton_ver_alojamiento);
            ver_alojamiento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
