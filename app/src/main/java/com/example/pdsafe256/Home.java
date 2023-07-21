package com.example.pdsafe256;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pdsafe256.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class Home extends AppCompatActivity {
    ImageView settings,phonelock,Gmaps,Dwipe,Antisnatch,RemoveApp;

    private AppBarConfiguration mAppBarConfiguration;

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

     ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        settings = findViewById(R.id.settingsID);
        phonelock = findViewById(R.id.phonelockID);
        Gmaps = findViewById(R.id.mapsID);
        Dwipe = findViewById(R.id.WipeID);
        Antisnatch = findViewById(R.id.AntisnatchID);
        RemoveApp = findViewById(R.id.uninstallID);

        settings.setOnClickListener(view -> {
            Intent inte = new Intent(Home.this,Settings.class);
            startActivity(inte);
        });

        phonelock.setOnClickListener(view -> {
            Intent inte = new Intent(Home.this,PhoneLock.class);
            startActivity(inte);
        });

        Gmaps.setOnClickListener(view -> {
            Intent inte = new Intent(Home.this,GoogleMaps.class);
            startActivity(inte);
        });

        Dwipe.setOnClickListener(view -> {
            Intent inte = new Intent(Home.this,activity_device_wipe.class);
            startActivity(inte);
        });

        RemoveApp.setOnClickListener(view -> {
           Intent inte =  new Intent(Intent.ACTION_DELETE);
           inte.setData(Uri.parse("package:com.example.pdsafe256"));
           startActivity(inte);
        });

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
