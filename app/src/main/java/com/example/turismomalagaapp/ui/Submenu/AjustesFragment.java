package com.example.turismomalagaapp.ui.Submenu;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.turismomalagaapp.MenuLateralActivity;
import com.example.turismomalagaapp.R;

import java.util.Locale;


public class AjustesFragment extends Fragment {
    View view;
    public Locale local;
    public ImageButton botonEsp, botonIng;
    public Configuration config = new Configuration();

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
                Resources resources = getContext().getResources();
                local = new Locale("en");
                Locale.setDefault(local);
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(local);
                getResources().updateConfiguration(configuration, null);
                DisplayMetrics metrics = resources.getDisplayMetrics();
                resources.updateConfiguration(configuration, metrics);
                // Cambiar tamaño de bandera
                botonIng.setScaleX(1.5f);
                botonIng.setScaleY(1.5f);
                // Actualizar la app
                Intent i = new Intent(v.getContext(), MenuLateralActivity.class);
                i.putExtra("idioma", local.getLanguage());
                getContext().startActivity(i);
            }
        });

        return view;
    }

    //para ir para atras poner en el fragment
    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        final String id = bundle.getString("id");
        if(getView() == null){
            return;
        }
        Log.d("AjustesFragment", "onResume: "+id);
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
}
