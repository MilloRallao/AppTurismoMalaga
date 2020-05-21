package com.example.turismomalagaapp.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turismomalagaapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // PERMISOS
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.mapView);
        if (mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle bundle = this.getArguments();
        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> longitudes = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();
        if (bundle != null) {
            latitudes = bundle.getStringArrayList("latitudes");
            longitudes = bundle.getStringArrayList("longitudes");
            nombres = bundle.getStringArrayList("nombres");
        }

        MapsInitializer.initialize(getContext());
        map = googleMap;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }else {
            googleMap.setMyLocationEnabled(false);
        }

        for (int i = 0; i < latitudes.size(); i++) {
            if(i==0){
                LatLng marcador = new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i)));
                map.addMarker(new MarkerOptions().position(marcador)).setTitle(nombres.get(i));
                map.moveCamera(CameraUpdateFactory.newLatLng(marcador));

                LatLngBounds.Builder constructor = new LatLngBounds.Builder();
                constructor.include(marcador);
                LatLngBounds limites = constructor.build();
                int ancho = getResources().getDisplayMetrics().widthPixels;
                int alto = getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (alto * 0.25); // 25% de espacio (padding) superior e inferior

                CameraUpdate centrarmarcadores = CameraUpdateFactory.newLatLngBounds(limites, ancho, alto, padding);
                map.animateCamera(centrarmarcadores);
            }else{
                LatLng marcador = new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i)));
                map.addMarker(new MarkerOptions().position(marcador)).setTitle(nombres.get(i));

                LatLngBounds.Builder constructor = new LatLngBounds.Builder();
                constructor.include(marcador);
            }
            CameraUpdate zoomMapa = CameraUpdateFactory.zoomTo(15.0f);
            map.animateCamera(zoomMapa);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        final String id = bundle.getString("id");
        if(getView() == null){
            return;
        }
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
