package com.example.turismomalagaapp.ui.ocio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;

public class AdapterOcio extends RecyclerView.Adapter<AdapterOcio.MyViewHolder>{

    public AdapterOcio(){
    }

    @NonNull
    @Override
    public AdapterOcio.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ocio_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterOcio.MyViewHolder holder, final int position) {
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
