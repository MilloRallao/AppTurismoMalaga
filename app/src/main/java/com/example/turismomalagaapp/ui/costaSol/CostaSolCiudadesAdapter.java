package com.example.turismomalagaapp.ui.costaSol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CostaSolCiudadesAdapter extends RecyclerView.Adapter<CostaSolCiudadesAdapter.MyViewHolder> {

    List<JSONObject> respuesta;

    public CostaSolCiudadesAdapter(List<JSONObject> response){
        respuesta = response;
    }

    @NonNull
    @Override
    public CostaSolCiudadesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costa_sol_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CostaSolCiudadesAdapter.MyViewHolder holder, final int position) {
        try{
            holder.textview_lugar_interes_ciudad_costa_sol.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_lugar_interes_ciudad_costa_sol.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_lugar_interes_costa_sol);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_lugar_interes_costa_sol;
        private TextView textview_lugar_interes_ciudad_costa_sol, textView_descripcion_lugar_interes_ciudad_costa_sol;
        MyViewHolder(View v) {
            super(v);
            imageView_lugar_interes_costa_sol = v.findViewById(R.id.imageView_lugar_interes_costa_sol);
            textview_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textview_lugar_interes_ciudad_costa_sol);
            textView_descripcion_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textView_descripcion_lugar_interes_ciudad_costa_sol);
        }
    }
}
