package com.example.turismomalagaapp.ui.compras;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

public class AdapterCompras extends RecyclerView.Adapter<AdapterCompras.MyViewHolder> {
    private List<JSONObject> respuesta;
    private FragmentActivity actividad;
    boolean isLang = Locale.getDefault().getLanguage().equals("en");

    public AdapterCompras(List<JSONObject> response, FragmentActivity activity){
        respuesta = response;
        actividad = activity;
    }

   @NonNull
    @Override
    public AdapterCompras.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compras_cardview, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterCompras.MyViewHolder holder, final int position) {
        try {
            holder.textview_tienda.setText(respuesta.get(position).getString("nombre"));

            if(isLang){
                IamAuthenticator authenticator = new IamAuthenticator("d4E5Z0llGfeB-qL57GJmVopY0dmYHqNlUA--l5UM2RP1");
                final LanguageTranslator languageTranslator = new LanguageTranslator("2020-06-02", authenticator);
                languageTranslator.setServiceUrl("https://api.eu-gb.language-translator.watson.cloud.ibm.com/instances/0645a0c9-8847-483b-949c-f960da0dfb01");

                final TranslateOptions translateOptions = new TranslateOptions.Builder()
                        .addText(respuesta.get(position).getString("descripcion"))
                        .modelId("es-en")
                        .build();

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try  {
                            TranslationResult result = languageTranslator.translate(translateOptions).execute().getResult();
                            holder.textView_descripcion_tienda.setText(result.getTranslations().get(0).getTranslation());
                        } catch (Exception e) {
                            Log.d("ERROR0", "run: "+e);
                        }
                    }
                });
                thread.start();
            } else {
                holder.textView_descripcion_tienda.setText(respuesta.get(position).getString("descripcion"));
            }

            Glide.with(holder.itemView).load(respuesta.get(position).getString("url_img")).load(respuesta.get(position).getString("url_img")).apply(new RequestOptions().transform(new RoundedCorners(50)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)).into(holder.imageView_tienda);
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
        private ImageView imageView_tienda;
        private TextView textview_tienda, textView_descripcion_tienda;
        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.cardview_compras);
            imageView_tienda = v.findViewById(R.id.imageView_tienda);
            textview_tienda = v.findViewById(R.id.textview_tienda);
            textView_descripcion_tienda = v.findViewById(R.id.textView_descripcion_tienda);
            final String id = String.valueOf(v.getId());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    OnClickVerFragment onClickVerFragment = new OnClickVerFragment();
                    try {
                        bundle.putString("nombre", respuesta.get(getAdapterPosition()).getString("nombre"));
                        bundle.putString("descripcion", respuesta.get(getAdapterPosition()).getString("descripcion"));
                        bundle.putString("imagen", respuesta.get(getAdapterPosition()).getString("url_img"));
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
