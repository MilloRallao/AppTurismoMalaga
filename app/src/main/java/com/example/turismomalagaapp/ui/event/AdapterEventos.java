package com.example.turismomalagaapp.ui.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.MyViewHolder> {

    public AdapterEventos() {

    }

    @NonNull
    @Override
    public AdapterEventos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview, parent, false); // vista del card_view
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) { // como un oncreatte normal de un activity
        holder.boton.setText("Ir a evento");
        holder.textView.setText("Evento");
        holder.imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        Button boton;
        TextView textView;
        ImageView imageView;
        MyViewHolder(View v) {
            super(v);
            boton = v.findViewById(R.id.button);
            textView = v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
        }
    }
}
