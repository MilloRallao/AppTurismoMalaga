package com.example.turismomalagaapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;

public class OnClickVerFragment extends Fragment {
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_click_ver, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String aux_nombre = bundle.getString("nombre");
            TextView nombre = view.findViewById(R.id.textView_nombre_onclick);
            nombre.setText(aux_nombre);
            TextView descripcion = view.findViewById(R.id.textView_descripcion_onclick);
            String aux_descripcion = bundle.getString("descripcion");
            descripcion.setText(aux_descripcion);
            ImageView imagen = view.findViewById(R.id.imageView_onclick);
            String aux_imagen = bundle.getString("imagen");
            Glide.with(view).load(aux_imagen).into(imagen);
            ImageButton llamada = view.findViewById(R.id.imageButton_llamada_onclick);
            final String aux_telefono = bundle.getString("telefono");
            llamada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + aux_telefono));
                    startActivity(intent);
                }
            });
        }
        return view;
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
