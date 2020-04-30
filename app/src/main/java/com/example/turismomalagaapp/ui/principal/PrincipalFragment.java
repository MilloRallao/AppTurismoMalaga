package com.example.turismomalagaapp.ui.principal;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turismomalagaapp.AdapterCompras;
import com.example.turismomalagaapp.AdapterPrincipal;
import com.example.turismomalagaapp.R;

public class PrincipalFragment extends Fragment {

    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        rv = view.findViewById(R.id.recyclerview_principal);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterPrincipal());
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
        Log.d("HOLA", "HOLA");
    }
}
