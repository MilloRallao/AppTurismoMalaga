package com.example.turismomalagaapp.ui.principal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PrincipalFragment extends Fragment {

    private Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    private TextView temperatura;
    private ImageView icono_tiempo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        temperatura = view.findViewById(R.id.textView_temperatura_ciudad);
        icono_tiempo = view.findViewById(R.id.imageView_icono_tiempo);
        rv = view.findViewById(R.id.recyclerview_principal);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterPrincipal());
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        String weatherApiKey = "85060b865d926f1b69746640c87cb07e";
        String weatherCityID = "2514256";
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?id="+weatherCityID+"&appid="+weatherApiKey;
        new JsonTask().execute(weatherURL);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class JsonTask extends AsyncTask<String, String, JSONObject> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected JSONObject doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }
                JSONObject data = new JSONObject(buffer.toString());
                return data;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            try {
                int id_weather = result.getJSONArray("weather").getJSONObject(0).getInt("id");
                Log.d("PRUEBA1", "onPostExecute: "+id_weather);
                String icono_weather = result.getJSONArray("weather").getJSONObject(0).getString("icon");
                Log.d("PRUEBA2", "onPostExecute: "+icono_weather);
                if (id_weather == 800) {
                    icono_tiempo.setImageResource(R.drawable.icono_soleado);
                }else{
                    Glide.with(context).load("http://openweathermap.org/img/wn/"+icono_weather+"@2x.png").into(icono_tiempo);
                }
                temperatura.setText(String.format("%.1f", result.getJSONObject("main").getDouble("temp") - 273,15)+ " ℃");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
