package com.example.turismomalagaapp.ui.costaSol;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turismomalagaapp.R;


public class CostaSolFragment extends Fragment {
    private CardView nerja;
    private CardView velezMalaga;
    private CardView rinconVictoria;
    private CardView torremolinos;
    private CardView benalmadena;
    private CardView fuengirola;
    private CardView mijas;
    private CardView marbella;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Obtener el path de un Drawable para pasarlo como String
    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_costa_sol, container, false);
        final Bundle bundle = new Bundle();
        nerja = view.findViewById(R.id.Nerja);
        nerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("nombreBD", "nerja");
                bundle.putString("nombre", "Nerja");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.nerja));
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        velezMalaga = view.findViewById(R.id.VelezMalaga);
        velezMalaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "velezmalaga");
                bundle.putString("nombre", "Vélez Málaga");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.velezmalaga));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        rinconVictoria = view.findViewById(R.id.RinconDeLaVictoria);
        rinconVictoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "rinconvictoria");
                bundle.putString("nombre", "Rincón de la Victoria");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.rincon));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        torremolinos = view.findViewById(R.id.TorreMolinos);
        torremolinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "torremolinos");
                bundle.putString("nombre", "Torremolinos");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.torremolinos));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        benalmadena = view.findViewById(R.id.Benalmadena);
        benalmadena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "benalmadena");
                bundle.putString("nombre", "Benalmádena");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.benalmeda));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        fuengirola = view.findViewById(R.id.Fuengirola);
        fuengirola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "fuengirola");
                bundle.putString("nombre", "Fuengirola");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.fuengirola));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        mijas = view.findViewById(R.id.Mijas);
        mijas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "mijas");
                bundle.putString("nombre", "Mijas");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.mijas));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        marbella = view.findViewById(R.id.Marbella);
        marbella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CostaSolCiudadesFragment ciudadCostaSolFragment = new CostaSolCiudadesFragment();
                bundle.putString("nombreBD", "marbella");
                bundle.putString("nombre", "Marbella");
                bundle.putString("id", String.valueOf(v.getId()));
                bundle.putString("foto", getURLForResource(R.drawable.marbella));
                ciudadCostaSolFragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, ciudadCostaSolFragment);
                transaction.addToBackStack(String.valueOf(v.getId()));
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
