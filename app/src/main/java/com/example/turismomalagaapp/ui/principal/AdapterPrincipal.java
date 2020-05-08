package com.example.turismomalagaapp.ui.principal;

import android.net.Uri;
import android.util.Log;
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

public class AdapterPrincipal extends RecyclerView.Adapter<AdapterPrincipal.MyViewHolder> {
    List<JSONObject> respuesta;

    AdapterPrincipal(List<JSONObject> response){
        respuesta = response;
    }

    @NonNull
    @Override
    public AdapterPrincipal.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterPrincipal.MyViewHolder holder, final int position) {
        try {
            holder.evento.setText(respuesta.get(position).getString("nombre"));
            holder.descripcion.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imagen);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView evento;
        private TextView descripcion;
        private ImageView imagen;
        MyViewHolder(View v) {
            super(v);
            evento = v.findViewById(R.id.textview_evento_principal);
            descripcion = v.findViewById(R.id.textView_descripcion_evento_principal);
            imagen = v.findViewById(R.id.imageView_evento_principal);
        }
    }
}
