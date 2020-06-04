package com.example.turismomalagaapp.ui.costaSol;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.OnClickVerFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class CostaSolCiudadesAdapter extends RecyclerView.Adapter<CostaSolCiudadesAdapter.MyViewHolder> {

    private List<JSONObject> respuesta;
    private FragmentActivity actividad;
    boolean isLang = Locale.getDefault().getLanguage().equals("en");
    private String nombre;
    private String nombre_ciudad;
    private String foto;

    public CostaSolCiudadesAdapter(List<JSONObject> response, FragmentActivity activity, String nombre_ciudad, String nombre_ciudad_BD, String foto_ciudad){
        respuesta = response;
        actividad = activity;
        nombre = nombre_ciudad;
        this.nombre_ciudad = nombre_ciudad_BD;
        foto = foto_ciudad;
    }

    @NonNull
    @Override
    public CostaSolCiudadesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.costa_sol_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CostaSolCiudadesAdapter.MyViewHolder holder, final int position) {
        try{
            holder.textview_lugar_interes_ciudad_costa_sol.setText(respuesta.get(position).getString("nombre"));

            if(isLang){
                holder.textView_descripcion_lugar_interes_ciudad_costa_sol.setText(respuesta.get(position).getString("descripcion_ing"));
            } else {
                holder.textView_descripcion_lugar_interes_ciudad_costa_sol.setText(respuesta.get(position).getString("descripcion"));
            }

            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).apply(new RequestOptions().transform(new RoundedCorners(50)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(holder.imageView_lugar_interes_costa_sol);
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
        private ImageView imageView_lugar_interes_costa_sol;
        private TextView textview_lugar_interes_ciudad_costa_sol, textView_descripcion_lugar_interes_ciudad_costa_sol;
        MyViewHolder(View v) {
            super(v);
            imageView_lugar_interes_costa_sol = v.findViewById(R.id.imageView_lugar_interes_costa_sol);
            textview_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textview_lugar_interes_ciudad_costa_sol);
            textView_descripcion_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textView_descripcion_lugar_interes_ciudad_costa_sol);
            cardView = v.findViewById(R.id.cardview_lugar_interes_ciudad_costa_sol);
            final String id = String.valueOf(v.getId());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    OnClickVerFragment onClickVerFragment = new OnClickVerFragment();
                    try {
                        bundle.putString("nombre", respuesta.get(getAdapterPosition()).getString("nombre"));
                        bundle.putString("descripcion", textView_descripcion_lugar_interes_ciudad_costa_sol.getText().toString());
                        bundle.putString("imagen", respuesta.get(getAdapterPosition()).getString("url_img"));
                        bundle.putString("ciudad", nombre);
                        bundle.putString("ciudadBD", nombre_ciudad);
                        bundle.putString("foto", foto);
                        bundle.putString("id", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    onClickVerFragment.setArguments(bundle);
                    FragmentTransaction transaction = actividad.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, onClickVerFragment);
                    transaction.addToBackStack(id);
                    transaction.commit();
                }
            });
        }
    }
}
