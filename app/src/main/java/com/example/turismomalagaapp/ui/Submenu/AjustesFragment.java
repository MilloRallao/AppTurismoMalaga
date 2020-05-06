package com.example.turismomalagaapp.ui.Submenu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.turismomalagaapp.R;


public class AjustesFragment extends Fragment {

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ajustes, container, false);
        return view;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //getActivity().getActionBar().setTitle();
    }
}
