package com.example.turismomalagaapp.ui.puntosInformacion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PuntosInfoFragment extends Fragment {

    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    String BD_URL = "https://projectfctappmalaga.000webhostapp.com/MalagaApp/select_p_info.php";
    List<JSONObject> respuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_puntos_info, container, false);
        rv = view.findViewById(R.id.recyclerview_puntos_info);
        layoutManager = new LinearLayoutManager(getActivity()); // para manegar los layouts de recyclerview (rv)
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL); // esto hace la funcion del scrol en el rv
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        cargarRespuesta();
        return view;
    }

    public void cargarRespuesta(){
        respuesta = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BD_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        respuesta.add(response.getJSONObject(i));
                        rv.setAdapter(new AdapterPuntosInfo(respuesta, getActivity()));
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
