package com.example.turismomalagaapp.ui.costaSol;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.turismomalagaapp.R;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class CostaSolCiudadesAdapter extends RecyclerView.Adapter<CostaSolCiudadesAdapter.MyViewHolder> {

    List<JSONObject> respuesta;
    boolean isLang = Locale.getDefault().getLanguage().equals("en");

    public CostaSolCiudadesAdapter(List<JSONObject> response){
        respuesta = response;
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
                            holder.textView_descripcion_lugar_interes_ciudad_costa_sol.setText(result.getTranslations().get(0).getTranslation());
                        } catch (Exception e) {
                            Log.d("ERROR0", "run: "+e);
                        }
                    }
                });
                thread.start();
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

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_lugar_interes_costa_sol;
        private TextView textview_lugar_interes_ciudad_costa_sol, textView_descripcion_lugar_interes_ciudad_costa_sol;
        MyViewHolder(View v) {
            super(v);
            imageView_lugar_interes_costa_sol = v.findViewById(R.id.imageView_lugar_interes_costa_sol);
            textview_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textview_lugar_interes_ciudad_costa_sol);
            textView_descripcion_lugar_interes_ciudad_costa_sol = v.findViewById(R.id.textView_descripcion_lugar_interes_ciudad_costa_sol);
        }
    }
}
