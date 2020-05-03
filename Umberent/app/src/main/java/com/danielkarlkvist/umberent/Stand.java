package com.danielkarlkvist.umberent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/** A Stand-class that represents a stand on the map */

public class Stand {
    private Umbrella[] umbrellas;
    private Marker marker;
    private double longitude;
    private double latitude;
    private String title;
    private int icon;
    private Availability availability;

    public enum Availability {HIGH, LOW, NONE}


    public Stand(String title, double longitude, double latitude, Availability availability, GoogleMap googleMap) {
        this.title = title;
        this.longitude = longitude;
        this.latitude = latitude;
        this.icon = icon;
        this.availability = availability;

        addMarker(googleMap);
    }

    private int setIcon(){

        switch(availability){
            case HIGH:
                this.icon = R.drawable.umberella_icon_available;
                return this.icon;
            case LOW:
                this.icon = R.drawable.umberella_icon_few_available;
                return this.icon;
            case NONE:
                this.icon = R.drawable.umberella_icon_not_available;
                return this.icon;
        }
        return 0; // TODO create an exception if there isnt an availability
    }

    public String getTitle() {
        return title;
    }

    public LatLng getLatLng(){
        return new LatLng(longitude, latitude);
    }

    private void addMarker(GoogleMap googleMap) {
        googleMap.addMarker((new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(setIcon()))
                .position(getLatLng())
                .title(title)));
    }
}
