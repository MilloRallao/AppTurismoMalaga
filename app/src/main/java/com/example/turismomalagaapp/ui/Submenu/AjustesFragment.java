package com.example.turismomalagaapp.ui.Submenu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.turismomalagaapp.MenuLateralActivity;
import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.map.MapFragment;

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
                local = new Locale("es");
                config.locale = local;
                getResources().updateConfiguration(config, null);
            }
        });

        botonIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                local = new Locale("en");
                config.locale = local;
                getResources().updateConfiguration(config, null);
            }
        });




        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getActivity().getActionBar().setTitle();
    }

}
