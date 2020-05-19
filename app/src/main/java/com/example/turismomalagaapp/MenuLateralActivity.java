package com.example.turismomalagaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import com.example.turismomalagaapp.ui.map.MapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.turismomalagaapp.ui.Submenu.AjustesFragment;
import com.example.turismomalagaapp.ui.Submenu.ContactoFragment;


public class MenuLateralActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lateral);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton map = findViewById(R.id.map);
//        map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MapFragment mapFragment = new MapFragment();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.nav_host_fragment, mapFragment);
//                Toolbar toolbar = findViewById(R.id.toolbar);
//                toolbar.setTitle(R.string.ml_map);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });

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
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    // Creación del submenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }
    // Opciones dependiendo del item selecionado del submenu
    @Override
    public boolean onOptionsItemSelected( MenuItem item) { // opciones del submenu --> cambios de fragment

        switch (item.getItemId()) {
            case R.id.action_ajustes:
                AjustesFragment fragment = new AjustesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                toolbar.setTitle(R.string.action_ajustes);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            case R.id.action_contacto:
                ContactoFragment fragment2 = new ContactoFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.nav_host_fragment, fragment2);
                toolbar.setTitle(R.string.action_contacto);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                return true;
            case R.id.action_valorar:
//                ValorarFragment fragment3 = new ValorarFragment();
//                FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction3.replace(R.id.nav_host_fragment, fragment3);
//                toolbar.setTitle(R.string.action_valorar);
//                fragmentTransaction3.addToBackStack(null);
//                fragmentTransaction3.commit();
                valorar();
                return true;
            case R.id.action_recomendaciones: //recomendaciones covid-19
                recomendaciones();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Popup de recomendaciones
    public void recomendaciones(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuLateralActivity.this);
        alerta.setMessage(R.string.info).setCancelable(true).setNegativeButton(R.string.salir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle(R.string.titulo);
        titulo.show();
    }

    // Popup de valorar
    public void valorar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuLateralActivity.this);
        alerta.setMessage("¿Te gusta la aplicación? \n Entra en la Play Store y valóranos").setCancelable(true).setPositiveButton("Valorar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        }).setNegativeButton("Ahora no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Valora "+R.string.app_name);
        titulo.show();
    }
}
