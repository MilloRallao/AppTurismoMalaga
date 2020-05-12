package com.example.turismomalagaapp.ui.puntosInformacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.OnClickVerFragment;

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

    class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView_puntos_info;
        private TextView textview_puntos_de_info, textView_descripcion_puntos_info;
        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.cardview_puntos_info);
            imageView_puntos_info = v.findViewById(R.id.imageView_puntos_info);
            textview_puntos_de_info = v.findViewById(R.id.textview_puntos_de_info);
            textView_descripcion_puntos_info = v.findViewById(R.id.textView_descripcion_puntos_info);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    OnClickVerFragment onClickVerFragment = new OnClickVerFragment();
                    try {
                        bundle.putString("nombre", respuesta.get(getAdapterPosition()).getString("nombre"));
                        bundle.putString("descripcion", respuesta.get(getAdapterPosition()).getString("descripcion"));
                        bundle.putString("imagen", respuesta.get(getAdapterPosition()).getString("url_img"));
                        bundle.putString("telefono", respuesta.get(getAdapterPosition()).getString("telefono"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    onClickVerFragment.setArguments(bundle);
                    FragmentTransaction transaction = actividad.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, onClickVerFragment);
                    transaction.addToBackStack(String.valueOf(v.getRootView()));
                    transaction.commit();
                }
            });
        }
    }
}
