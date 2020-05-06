package com.example.turismomalagaapp.ui.principal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterPrincipal extends RecyclerView.Adapter<AdapterPrincipal.MyViewHolder> {

    public AdapterPrincipal(){
    }

    @NonNull
    @Override
    public AdapterPrincipal.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterPrincipal.MyViewHolder holder, final int position) {
        holder.lugar1.setText("Lugar principal 1");
        holder.boton.setText("Ir a lugar 1");
    }

    @Override
    public int getItemCount() {
        return 3;
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
