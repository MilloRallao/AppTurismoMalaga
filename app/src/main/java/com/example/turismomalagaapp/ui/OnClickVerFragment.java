package com.example.turismomalagaapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.turismomalagaapp.R;
import com.example.turismomalagaapp.ui.costaSol.CostaSolCiudadesFragment;

import static android.app.Activity.RESULT_OK;

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
            ImageView imagen = view.findViewById(R.id.imageView_onclick);
            String aux_imagen = bundle.getString("imagen");
            Glide.with(view).load(aux_imagen).into(imagen);

            String aux_nombre = bundle.getString("nombre");
            TextView nombre = view.findViewById(R.id.textView_nombre_onclick);
            nombre.setText(aux_nombre);

            TextView descripcion = view.findViewById(R.id.textView_descripcion_onclick);
            String aux_descripcion = bundle.getString("descripcion");
            descripcion.setText(aux_descripcion);

            ImageButton llamada = view.findViewById(R.id.imageButton_llamada_onclick);
            try{
                final String aux_telefono = bundle.getString("telefono");
                if(aux_telefono != null){
                    llamada.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + aux_telefono)));
                        }
                    });
                }else {
                    llamada.setVisibility(View.INVISIBLE);
                }
            }catch (Error error){
                llamada.setVisibility(View.INVISIBLE);
            }

            ImageView estrella1 = view.findViewById(R.id.imageView_estrella1);
            ImageView estrella2 = view.findViewById(R.id.imageView_estrella2);
            ImageView estrella3 = view.findViewById(R.id.imageView_estrella3);
            ImageView estrella4 = view.findViewById(R.id.imageView_estrella4);
            ImageView estrella5 = view.findViewById(R.id.imageView_estrella5);
            String num_estrellas = bundle.getString("estrellas");
            if(!(num_estrellas == null)){
                switch (num_estrellas){
                    case "1" :
                        estrella3.setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        estrella2.setVisibility(View.VISIBLE);
                        estrella3.setVisibility(View.VISIBLE);
                        break;
                    case "3":
                        estrella2.setVisibility(View.VISIBLE);
                        estrella3.setVisibility(View.VISIBLE);
                        estrella4.setVisibility(View.VISIBLE);
                        break;
                    case "4":
                        estrella1.setVisibility(View.VISIBLE);
                        estrella2.setVisibility(View.VISIBLE);
                        estrella3.setVisibility(View.VISIBLE);
                        estrella4.setVisibility(View.VISIBLE);
                        break;
                    case "5":
                        estrella1.setVisibility(View.VISIBLE);
                        estrella2.setVisibility(View.VISIBLE);
                        estrella3.setVisibility(View.VISIBLE);
                        estrella4.setVisibility(View.VISIBLE);
                        estrella5.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }else{
                estrella1.setVisibility(View.GONE);
                estrella2.setVisibility(View.GONE);
                estrella3.setVisibility(View.GONE);
                estrella4.setVisibility(View.GONE);
                estrella5.setVisibility(View.GONE);
            }

            TextView horario = view.findViewById(R.id.textView_horario_onclick);
            try {
                String aux_horario = bundle.getString("horario");
                horario.setText(aux_horario);
            }catch (Error error){
                Log.d("ERROR", "onCreateView: "+error);
                horario.setVisibility(View.GONE);
            }

        }
        return view;
    }

//para ir para atras poner en el fragment
    @Override
    public void onResume() {
        super.onResume();
        final Bundle bundle0 = this.getArguments();
        final String id = bundle0.getString("id");
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    if(!(bundle0.getString("ciudad") == null)){
                        Bundle bundle1 = new Bundle();
                        CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                        bundle1.putString("nombreBD", bundle0.getString("ciudadBD"));
                        bundle1.putString("foto", bundle0.getString("foto"));
                        bundle1.putString("nombre", bundle0.getString("ciudad"));
                        bundle1.putString("getback", "getback");
                        ciudadCostaSolFragment.setArguments(bundle1);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        return true;
                    }else{
                        // Volver hacia la vista anterior
                        getFragmentManager().popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        return true;
                    }
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
