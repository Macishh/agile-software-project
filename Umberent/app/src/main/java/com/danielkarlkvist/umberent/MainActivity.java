package com.danielkarlkvist.umberent;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        setSupportActionBar(toolbar);

        addToggleForToolbar();

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
        toolbar = findViewById(R.id.toolbar1);
    }
}
