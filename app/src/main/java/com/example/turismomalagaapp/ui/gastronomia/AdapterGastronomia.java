package com.example.turismomalagaapp.ui.gastronomia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterGastronomia extends RecyclerView.Adapter<AdapterGastronomia.MyViewHolder> {

    List<JSONObject> respuesta;

    public AdapterGastronomia(List<JSONObject> response){
        respuesta = response;
    }

    @NonNull
    @Override
    public AdapterGastronomia.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gastronomia_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterGastronomia.MyViewHolder holder, final int position) {
        try{
            holder.textview_restaurante.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_restaurante.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_restaurante);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_restaurante;
        private TextView textview_restaurante, textView_descripcion_restaurante;
        MyViewHolder(View v) {
            super(v);
            imageView_restaurante = v.findViewById(R.id.imageView_restaurante);
            textview_restaurante = v.findViewById(R.id.textview_restaurante);
            textView_descripcion_restaurante = v.findViewById(R.id.textView_descripcion_restaurante);
        }
    }
}
