package com.danielkarlkvist.Umberent.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * The IStand interface defines the method that should be able to run of a Stand outside of the model.
 */
public interface IStand {
    int getID();

    String getTitle();

    List<Umbrella> getUmbrellas();

    int getAmountOfUmbrellas();

    int getCapacity();

    LatLng getLatLng();
}
