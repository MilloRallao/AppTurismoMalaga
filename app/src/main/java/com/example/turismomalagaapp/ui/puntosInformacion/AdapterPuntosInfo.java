package com.example.turismomalagaapp.ui.puntosInformacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterPuntosInfo extends RecyclerView.Adapter<AdapterPuntosInfo.MyViewHolder> {
    private List<JSONObject> respuesta;
    private FragmentActivity actividad;

    public AdapterPuntosInfo (List<JSONObject> response, FragmentActivity activity){
        respuesta = response;
        actividad = activity;
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
            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).into(holder.imageView_puntos_info);
            holder.textview_titulo_puntos_info.setText(respuesta.get(position).getString("nombre"));
            holder.horario_puntos_info.setText(respuesta.get(position).getString("horario"));
            holder.telefono_puntos_info.setText(respuesta.get(position).getString("telefono"));
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return respuesta.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView_puntos_info;
        private TextView textview_titulo_puntos_info, horario_puntos_info, telefono_puntos_info;
        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.cardview_puntos_info);
            imageView_puntos_info = v.findViewById(R.id.imageView_puntos_info);
            textview_titulo_puntos_info = v.findViewById(R.id.textview_titulo_puntos_info);
            horario_puntos_info = v.findViewById(R.id.textView_horario_puntos_info);
            telefono_puntos_info = v.findViewById(R.id.textView_telefono_puntos_info);
        }
    }
}
