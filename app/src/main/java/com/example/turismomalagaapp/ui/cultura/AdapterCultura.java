package com.example.turismomalagaapp.ui.cultura;

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

public class AdapterCultura extends RecyclerView.Adapter<AdapterCultura.MyViewHolder> {

    List<JSONObject> respuesta;

    public AdapterCultura(List<JSONObject> response){
        respuesta = response;
    }

    @NonNull
    @Override
    public AdapterCultura.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cultura_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterCultura.MyViewHolder holder, final int position) {
        try {
            holder.textview_lugar_cultural.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_lugar_cultural.setText(respuesta.get(position).getString("nombre"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_lugar_cultural);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_lugar_cultural;
        private TextView textview_lugar_cultural;
        private TextView textView_descripcion_lugar_cultural;
        MyViewHolder(View v) {
            super(v);
            imageView_lugar_cultural = v.findViewById(R.id.imageView_lugar_cultural);
            textview_lugar_cultural = v.findViewById(R.id.textview_lugar_cultural);
            textView_descripcion_lugar_cultural = v.findViewById(R.id.textView_descripcion_lugar_cultural);
        }
    }
}
