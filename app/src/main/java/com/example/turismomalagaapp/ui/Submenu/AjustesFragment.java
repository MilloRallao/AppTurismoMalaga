package com.example.turismomalagaapp.ui.Submenu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.turismomalagaapp.MenuLateralActivity;
import com.example.turismomalagaapp.R;

import java.util.Locale;


public class AjustesFragment extends Fragment {

    View view;
    Locale local;
    ImageButton botonEsp, botonIng;
    private Configuration config = new Configuration();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        botonEsp = view.findViewById(R.id.imageButton);
        botonIng = view.findViewById(R.id.imageButton2);

        botonEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Establecer lenguaje de los Strings
                local = new Locale("es");
                config.locale = local;
                getResources().updateConfiguration(config, null);
                getResources().updateConfiguration(config, null);
                // Cambiar tamaño de bandera
                botonEsp.setScaleX(1.5f);
                botonEsp.setScaleY(1.5f);
                // Actualizar la app
                Intent i = new Intent(v.getContext(), MenuLateralActivity.class);
                getContext().startActivity(i);
            }
        });

        botonIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Establecer lenguaje de los Strings
                local = new Locale("en");
                config.locale = local;
                getResources().updateConfiguration(config, null);
                // Cambiar tamaño de bandera
                botonIng.setScaleX(1.5f);
                botonIng.setScaleY(1.5f);
                // Actualizar la app
                Intent i = new Intent(v.getContext(), MenuLateralActivity.class);
                getContext().startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
