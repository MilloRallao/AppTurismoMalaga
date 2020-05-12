package com.example.turismomalagaapp.ui.puntosInformacion;

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

public class AdapterPuntosInfo extends RecyclerView.Adapter<AdapterPuntosInfo.MyViewHolder> {

    List<JSONObject> respuesta;

    public AdapterPuntosInfo (List<JSONObject> response){
        respuesta = response;
    }

    @NonNull
    @Override
    public AdapterPuntosInfo.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.puntos_info_cardview, parent, false); // vista del card_view
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        try{
            holder.textview_puntos_de_info.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_puntos_info.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_puntos_info);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_puntos_info;
        private TextView textview_puntos_de_info, textView_descripcion_puntos_info;
        MyViewHolder(View v) {
            super(v);
            imageView_puntos_info = v.findViewById(R.id.imageView_puntos_info);
            textview_puntos_de_info = v.findViewById(R.id.textview_puntos_de_info);
            textView_descripcion_puntos_info = v.findViewById(R.id.textView_descripcion_puntos_info);
        }
    }
}
