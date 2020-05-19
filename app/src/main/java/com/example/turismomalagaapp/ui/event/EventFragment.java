package com.example.turismomalagaapp.ui.event;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton mapa;
    private ArrayList<String> latitudes;
    private ArrayList<String> longitudes;
    private ArrayList<String> nombres;

    String BD_URL = "https://projectfctappmalaga.000webhostapp.com/MalagaApp/select_evento.php";
    List<JSONObject> respuesta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        rv = view.findViewById(R.id.recyclerview_eventos);
        layoutManager = new LinearLayoutManager(getActivity()); // para manegar los layouts de recyclerview (rv)
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL); // esto hace la funcion del scrol en el rv
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        cargarRespuesta();
        final FragmentActivity fragmentActivity = getActivity();
        mapa = view.findViewById(R.id.floatingActionButton_mapa_evento);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                MapFragment mapFragment = new MapFragment();
                bundle.putStringArrayList("latitudes", latitudes);
                bundle.putStringArrayList("longitudes", longitudes);
                bundle.putStringArrayList("nombres", nombres);
                mapFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, mapFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    public void cargarRespuesta(){
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
                        rv.setAdapter(new AdapterEventos(respuesta, getActivity()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
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
