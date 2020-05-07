package com.example.turismomalagaapp.ui.cultura;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterCultura extends RecyclerView.Adapter<AdapterCultura.MyViewHolder> {

    public AdapterCultura(){
    }

    @NonNull
    @Override
    public AdapterCultura.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cultura_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCultura.MyViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View v) {
            super(v);
        }
    }
}
