package com.danielkarlkvist.umberent.Model;

/**
 * The IUmbrella interface defines the method that should be able to run of an Umbrella outside of the model.
 */
public interface IUmbrella {

    int getId();
    boolean isAvailable();

    void setAvailability(boolean available);

}
