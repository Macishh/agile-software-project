package com.danielkarlkvist.umberent;

import com.google.android.gms.maps.model.Marker;

public class Stand {
    private Umbrella[] umbrellas;
    private Marker marker;
    private int longitude;
    private int latitude;

    public Stand(int longitude, int latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }



}
