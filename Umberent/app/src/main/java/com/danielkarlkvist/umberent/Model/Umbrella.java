package com.danielkarlkvist.umberent.Model;

/**
 * The Umbrella class contains all the information of an umbrella.
 */
public class Umbrella {
    private int id;
    private boolean isAvailable;

    public Umbrella(int id, boolean isAvailable) {
        this.id = id;
        this.isAvailable = isAvailable;
    }

    public int getID() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        isAvailable = available;
    }
}
