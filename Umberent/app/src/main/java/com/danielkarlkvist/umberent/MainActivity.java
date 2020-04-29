package com.danielkarlkvist.umberent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private MapFragment mapFragment = new MapFragment();

    Umberent umberent = new Umberent();

    private ProfileFragment profileFragment = new ProfileFragment(umberent);
    private PaymentFragment paymentFragment = new PaymentFragment(umberent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavigationBar();
        initializeViews();
        setSupportActionBar(toolbar);
        addToggleForToolbar();
        setNavigationListener();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment).commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                break;
            case R.id.nav_map:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
                break;
            case R.id.nav_payment:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, paymentFragment).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HistoryFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationListener() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    // adds a toggle for the toolbar so that when it is tapped, the side menu will pop out
    private void addToggleForToolbar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // finds the correct view in the xml file and connects it to the instance variables
    private void initializeViews() {
        drawerLayout = findViewById(R.id.test1);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar1);
    }

    private void hideNavigationBar() {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


}
