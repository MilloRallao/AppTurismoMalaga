package com.example.turismomalagaapp.ui.event;

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

import com.example.turismomalagaapp.AdapterEventos;
import com.example.turismomalagaapp.R;


public class EventFragment extends Fragment {

    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        rv = view.findViewById(R.id.recyclerview_eventos);
        layoutManager = new LinearLayoutManager(getActivity()); // para manegar los layouts de recyclerview (rv)
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL); // esto hace la funcion del scrol en el rv
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterEventos());
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
