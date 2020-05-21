package com.danielkarlkvist.Umberent.Model;

import com.danielkarlkvist.Umberent.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * The Stand class represents a stand on the map.
 * */
class Stand implements IStand {
    private int id;
    private String title;

    private List<Umbrella> umbrellas = new ArrayList<>();
    private int capacity;

    private double longitude;
    private double latitude;

    public Stand(int id, String title, int capacity, double longitude, double latitude) {
        this.id = id;
        this.title = title;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Umbrella> getUmbrellas() {
        return umbrellas;
    }

    public Umbrella getLastUmbrella() {return umbrellas.get(umbrellas.size() - 1); }

    /**
     * Returns the amount of umbrellas in the list.
     * @return Returns the amount of umbrellas in the list.
     */
    public int getAmountOfUmbrellas() {
        return umbrellas.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public LatLng getLatLng(){
        return new LatLng(longitude, latitude);
    }

    /**
     * Adds an umbrella to the stand if we have not reached our maximum capacity.
     * @param umbrella
     */
    public void addUmbrella(Umbrella umbrella) {
        if (umbrellas.size() < capacity) {
            umbrellas.add(umbrella);
        } else {
            System.out.println("Can not add any more umbrellas, stand reached max capacity");
        }
    }
}
