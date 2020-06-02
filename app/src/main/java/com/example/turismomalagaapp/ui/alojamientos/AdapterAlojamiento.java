package com.example.turismomalagaapp.ui.alojamientos;

import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.List;

public class AdapterAlojamiento extends RecyclerView.Adapter<AdapterAlojamiento.MyViewHolder>  {
    private List<JSONObject> respuesta;
    private FragmentActivity actividad;

     public AdapterAlojamiento(List<JSONObject> response, FragmentActivity activity){
         respuesta = response;
         actividad = activity;
    }

    @NonNull
    @Override
    public AdapterAlojamiento.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alojamiento_cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterAlojamiento.MyViewHolder holder, final int position) {
        holder.estrella1.setVisibility(View.INVISIBLE);
        holder.estrella2.setVisibility(View.INVISIBLE);
        holder.estrella3.setVisibility(View.INVISIBLE);
        holder.estrella4.setVisibility(View.INVISIBLE);
        holder.estrella5.setVisibility(View.INVISIBLE);
         try{
             holder.nombre_alojamiento.setText(respuesta.get(position).getString("nombre"));

             // Create an Spanish-English translator:
             FirebaseApp.initializeApp(actividad);
             FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                     .setSourceLanguage(FirebaseTranslateLanguage.ES)
                     .setTargetLanguage(FirebaseTranslateLanguage.EN)
                     .build();
             final FirebaseTranslator spanishEnglisTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
             FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().requireWifi().build();
             spanishEnglisTranslator.downloadModelIfNeeded(conditions).addOnSuccessListener( new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void v) {
                     for (int i = 0; i < respuesta.get(position).length(); i++) {
                         try {
                             spanishEnglisTranslator.translate(respuesta.get(position).getString("descripcion")).addOnSuccessListener(new OnSuccessListener<String>() {
                                 @Override
                                 public void onSuccess(@NonNull String translatedText) {
                                     holder.descripcion_alojamiento.setText(translatedText);
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Log.d("ERROR0", "onFailure: "+e);
                                 }
                             });
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }
             }).addOnFailureListener(
                     new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Log.d("ERROR1", "onFailure: "+e);
                         }
                     });

             switch (respuesta.get(position).getString("estrellas")){
                 case "1" :
                     holder.estrella1.setVisibility(View.VISIBLE);
                     break;
                 case "2":
                     holder.estrella1.setVisibility(View.VISIBLE);
                     holder.estrella2.setVisibility(View.VISIBLE);
                     break;
                 case "3":
                     holder.estrella1.setVisibility(View.VISIBLE);
                     holder.estrella2.setVisibility(View.VISIBLE);
                     holder.estrella3.setVisibility(View.VISIBLE);
                     break;
                 case "4":
                     holder.estrella1.setVisibility(View.VISIBLE);
                     holder.estrella2.setVisibility(View.VISIBLE);
                     holder.estrella3.setVisibility(View.VISIBLE);
                     holder.estrella4.setVisibility(View.VISIBLE);
                     break;
                 case "5":
                     holder.estrella1.setVisibility(View.VISIBLE);
                     holder.estrella2.setVisibility(View.VISIBLE);
                     holder.estrella3.setVisibility(View.VISIBLE);
                     holder.estrella4.setVisibility(View.VISIBLE);
                     holder.estrella5.setVisibility(View.VISIBLE);
                     break;
                 default:
                     break;
             }
             Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).apply(new RequestOptions().transform(new RoundedCorners(50)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(holder.imagen);
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
        private ImageView imagen, estrella1, estrella2, estrella3, estrella4, estrella5;
        private TextView nombre_alojamiento, descripcion_alojamiento;
        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.cardview_alojamiento);
            nombre_alojamiento = v.findViewById(R.id.textview_nombre_alojamiento);
            descripcion_alojamiento = v.findViewById(R.id.textView_descripcion_alojamiento);
            imagen = v.findViewById(R.id.imageView_alojamiento);
            estrella1 = v.findViewById(R.id.estrella1);
            estrella2 = v.findViewById(R.id.estrella2);
            estrella3 = v.findViewById(R.id.estrella3);
            estrella4= v.findViewById(R.id.estrella4);
            estrella5= v.findViewById(R.id.estrella5);
            final String id = String.valueOf(v.getId());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    OnClickVerFragment onClickVerFragment = new OnClickVerFragment();
                    try {
                        bundle.putString("nombre", respuesta.get(getAdapterPosition()).getString("nombre"));
                        bundle.putString("descripcion", descripcion_alojamiento.getText().toString());
                        bundle.putString("imagen", respuesta.get(getAdapterPosition()).getString("url_img"));
                        bundle.putString("telefono", respuesta.get(getAdapterPosition()).getString("telefono"));
                        bundle.putString("estrellas", respuesta.get(getAdapterPosition()).getString("estrellas"));
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
