package com.example.turismomalagaapp.ui.compras;

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

public class AdapterCompras extends RecyclerView.Adapter<AdapterCompras.MyViewHolder> {

    List<JSONObject> respuesta;

    public AdapterCompras(List<JSONObject> response){
        respuesta = response;
    }

   @NonNull
    @Override
    public AdapterCompras.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_cardview, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCompras.MyViewHolder holder, final int position) {
        try {
            holder.textview_tienda.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_tienda.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_tienda);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_tienda;
        private TextView textview_tienda, textView_descripcion_tienda;
        MyViewHolder(View v) {
            super(v);
            imageView_tienda = v.findViewById(R.id.imageView_tienda);
            textview_tienda = v.findViewById(R.id.textview_tienda);
            textView_descripcion_tienda = v.findViewById(R.id.textView_descripcion_tienda);
        }
    }
}
