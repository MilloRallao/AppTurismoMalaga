package com.example.turismomalagaapp;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import com.example.turismomalagaapp.ui.map.MapFragment;
import com.example.turismomalagaapp.ui.principal.PrincipalFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.turismomalagaapp.ui.Submenu.AjustesFragment;
import com.example.turismomalagaapp.ui.Submenu.ContactoFragment;
import com.example.turismomalagaapp.ui.Submenu.ValorarFragment;

import java.util.Locale;


public class MenuLateralActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    Locale local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton map = findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapFragment mapFragment = new MapFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, mapFragment);
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.setTitle("Mapa");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.principal,
                R.id.nav_event, R.id.nav_ocio, R.id.nav_gastronomia,R.id.nav_cultura,R.id.nav_pInfo, R.id.nav_compras,R.id.nav_cSol,
                R.id.action_ajustes,R.id.action_contacto,R.id.action_valorar,R.id.nav_map, R.id.nav_alojamiento)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    // para el funcionamiento del submenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }
    // opciones dependiendo del item selecionado del submenu
    @Override
    public boolean onOptionsItemSelected( MenuItem item) { // opciones del submenu --> cambios de fragment

        switch (item.getItemId()) {
            case R.id.action_ajustes:
                AjustesFragment fragment = new AjustesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                toolbar.setTitle("Ajustes");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.action_contacto:
                ContactoFragment fragment2 = new ContactoFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.nav_host_fragment, fragment2);
                if (local == new Locale("es")){
                    toolbar.setTitle("Contacto");
                }else if (local== new Locale("en")){
                    toolbar.setTitle("Contact");
                }
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;
            case R.id.action_valorar:
                ValorarFragment fragment3 = new ValorarFragment();
                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.nav_host_fragment, fragment3);
                toolbar.setTitle("Valorar");
                fragmentTransaction3.addToBackStack(null);
                fragmentTransaction3.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void home(View v){// para volver al principal con la casita
        PrincipalFragment fragment = new PrincipalFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("TurismoMalaga");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
