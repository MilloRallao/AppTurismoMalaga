package com.example.turismomalagaapp.ui.alojamientos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.map.MapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoFragment extends Fragment {
    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton mapa;
    private ArrayList<String> latitudes;
    private ArrayList<String> longitudes;
    private ArrayList<String> nombres;
    private ArrayList<String> descripciones;

    String BD_URL = "https://projectfctappmalaga.000webhostapp.com/MalagaApp/select_alojamiento.php";
    List<JSONObject> respuesta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alojamiento, container, false);
        rv = view.findViewById(R.id.recyclerview_alojamiento);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        cargarRespuesta();
        final FragmentActivity fragmentActivity = getActivity();
        final String id = String.valueOf(view.getId());
        mapa = view.findViewById(R.id.floatingActionButton_mapa_alojamiento);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                MapFragment mapFragment = new MapFragment();
                bundle.putStringArrayList("latitudes", latitudes);
                bundle.putStringArrayList("longitudes", longitudes);
                bundle.putStringArrayList("nombres", nombres);
                bundle.putString("id", id);
                mapFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, mapFragment);
                transaction.addToBackStack(id);
                transaction.commit();
            }
        });
        return view;
    }

    public void cargarRespuesta(){
        final ProgressDialog login = ProgressDialog.show(context,"por favorespere ...","aztualizando",false,false);
        respuesta = new ArrayList<>();
        latitudes = new ArrayList<>();
        longitudes = new ArrayList<>();
        nombres = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BD_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        respuesta.add(response.getJSONObject(i));
                        latitudes.add(i, respuesta.get(i).getString("latitud"));
                        longitudes.add(i, respuesta.get(i).getString("longitud"));
                        nombres.add(i, respuesta.get(i).getString("nombre"));
                        rv.setAdapter(new AdapterAlojamiento(respuesta, getActivity()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                login.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                login.dismiss();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
