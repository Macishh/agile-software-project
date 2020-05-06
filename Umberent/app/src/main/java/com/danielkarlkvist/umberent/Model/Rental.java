package com.danielkarlkvist.umberent.Model;

import java.util.Date;

/**
 * The Rental class contains all the information for when a user wants to rent an umbrella.
 */
public class Rental {
    private Date startDate;
    private Date endDate;
    private int cost;
    private Profile user;
    private Umbrella umbrella;

    public Rental(Date startDate, Date endDate, Profile user, Umbrella umbrella) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.umbrella = umbrella;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getCost() {
        return cost;
    }

    public Profile getUser() {
        return user;
    }

    public Umbrella getUmbrella() {
        return umbrella;
    }
}
