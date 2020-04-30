package com.example.turismomalagaapp.ui.ocio;

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
import com.example.turismomalagaapp.AdapterOcio;
import com.example.turismomalagaapp.R;

public class OcioFragment extends Fragment {

    Context context;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocio, container, false);
        rv = view.findViewById(R.id.recyclerview_ocio);
        layoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdapterOcio());
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
