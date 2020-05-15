package com.example.turismomalagaapp.ui.costaSol;

import android.content.Context;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_costa_sol, container, false);
        final Bundle bundle = new Bundle();
        nerja = view.findViewById(R.id.Nerja);
        nerja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("nombre", "nerja");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "velezmalaga");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "rinconvictoria");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "torremolinos");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "benalmadena");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "nerja");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "mijas");
                bundle.putString("id", String.valueOf(v.getId()));
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
                bundle.putString("nombre", "marbella");
                bundle.putString("id", String.valueOf(v.getId()));
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
