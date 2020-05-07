package com.example.turismomalagaapp.ui.costaSol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.principal.AdapterPrincipal;

public class CostaSolCiudadesAdapter extends RecyclerView.Adapter<CostaSolCiudadesAdapter.MyViewHolder> {

    public CostaSolCiudadesAdapter(){
    }

    @NonNull
    @Override
    public CostaSolCiudadesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costa_sol_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CostaSolCiudadesAdapter.MyViewHolder holder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View v) {
            super(v);
        }
    }
}
