package com.example.turismomalagaapp.ui.compras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterCompras extends RecyclerView.Adapter<AdapterCompras.MyViewHolder> {

   public AdapterCompras(){
   }

   @NonNull
    @Override
    public AdapterCompras.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_cardview, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCompras.MyViewHolder holder, final int position) {
        holder.compra.setText("Tienda");
        holder.boton.setText("Ir tienda");
        holder.imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView compra;
        Button boton;
        ImageView imageView;
        MyViewHolder(View v) {
            super(v);
            compra = v.findViewById(R.id.textview);
            boton = v.findViewById(R.id.button);
            imageView = v.findViewById(R.id.imageView);
        }
    }
}
