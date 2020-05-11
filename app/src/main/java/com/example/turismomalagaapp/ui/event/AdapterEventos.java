package com.example.turismomalagaapp.ui.event;

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

public class AdapterEventos extends RecyclerView.Adapter<AdapterEventos.MyViewHolder> {

    List<JSONObject> respuesta;

    public AdapterEventos(List<JSONObject> response) {
        respuesta = response;
    }

    @NonNull
    @Override
    public AdapterEventos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cardview, parent, false); // vista del card_view
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) { // como un oncreatte normal de un activity
        try {
            holder.textview_lugar_evento.setText(respuesta.get(position).getString("nombre"));
            holder.textView_descripcion_evento.setText(respuesta.get(position).getString("descripcion"));
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_evento);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_evento;
        private TextView textview_lugar_evento, textView_descripcion_evento;
        MyViewHolder(View v) {
            super(v);
            imageView_evento = v.findViewById(R.id.imageView_evento);
            textview_lugar_evento = v.findViewById(R.id.textview_lugar_evento);
            textView_descripcion_evento = v.findViewById(R.id.textView_descripcion_evento);
        }
    }
}
