package com.example.turismomalagaapp.ui.costaSol;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CostaSolCiudadesFragment extends Fragment {
    Context context;
    private TextView nombreCiudad;
    private ImageView foto;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    String BD_URL = "https://projectfctappmalaga.000webhostapp.com/MalagaApp/select_costaDelSol.php?nombre_ciudad=";
    List<JSONObject> respuesta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_costa_sol_ciudades, container, false);
        nombreCiudad = view.findViewById(R.id.textView_nombre_ciudad_costa_sol);
        foto = view.findViewById(R.id.imageView_ciudad_costa_sol);
        rv = view.findViewById(R.id.recyclerview_costa_sol_ciudad);
        layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String nombre_ciudad = bundle.getString("nombre");
            String nombre_ciudad_BD = bundle.getString("nombreBD");
            nombreCiudad.setText(nombre_ciudad);
            String foto_ciudad = bundle.getString("foto");
            Glide.with(context).load(foto_ciudad).into(foto);
            BD_URL += nombre_ciudad_BD;
            cargarRespuesta();
        }
        return view;
    }

    private void cargarRespuesta(){
        final ProgressDialog login = ProgressDialog.show(context,"Por favor espere ...","Actualizando",false,false);
        respuesta = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BD_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        respuesta.add(response.getJSONObject(i));
                        rv.setAdapter(new CostaSolCiudadesAdapter(respuesta));
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
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        final String id = bundle.getString("id");
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // Volver hacia la vista anterior
                    getFragmentManager().popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
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
