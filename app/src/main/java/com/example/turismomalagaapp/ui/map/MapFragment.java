package com.example.turismomalagaapp.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turismomalagaapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        MapsInitializer.initialize(getContext());
        map = googleMap;
        LatLng sydney1 = new LatLng(-33.852, 151.211);
        LatLng sydney2 = new LatLng(-33.852, 149.211);
        LatLng sydney3 = new LatLng(-33.852, 147.211);
        MarkerOptions loc1 = new MarkerOptions().position(sydney1).title("RAMÓN").snippet("Vidal");
        MarkerOptions loc2 = new MarkerOptions().position(sydney2).title("DAVID").snippet("Curbelo");
        MarkerOptions loc3 = new MarkerOptions().position(sydney3).title("CARLOS").snippet("Garrido");
        googleMap.addMarker(loc1);
        googleMap.addMarker(loc2);
        googleMap.addMarker(loc3);
        googleMap.setMyLocationEnabled(true);
        googleMap.setMinZoomPreference(10);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));
    }
}
