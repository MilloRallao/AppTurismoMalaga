package com.example.turismomalagaapp.ui.alojamientos;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.compras.AdapterCompras;

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
        holder.compra.setText("Alojamiento");
        holder.boton.setText("Ir a alojamiento");
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
        TextView compra;
        ImageView imageView, estrella1, estrella2, estrella3, estrella4, estrella5;
        Button boton;
        MyViewHolder(View v) {
            super(v);
            compra = v.findViewById(R.id.textview_lugar);
            imageView = v.findViewById(R.id.imageView);
            estrella1 = v.findViewById(R.id.estrella1);
            estrella2 = v.findViewById(R.id.estrella2);
            estrella3 = v.findViewById(R.id.estrella3);
            estrella4= v.findViewById(R.id.estrella4);
            estrella5= v.findViewById(R.id.estrella5);
            boton = v.findViewById(R.id.button);
        }
    }

}
