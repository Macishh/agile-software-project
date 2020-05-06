package com.danielkarlkvist.umberent.Model;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The Rental class contains all the information for when a user wants to rent an umbrella.
 */
public class Rental {
    private long startTime;
    private long endTime;
    private LocalDate date;
    private int cost;
    private Profile user;
    private Umbrella umbrella;

    public Rental(long startDate, long endDate, LocalDate date, Profile user, Umbrella umbrella) {
        this.startTime = startDate;
        this.endTime = endDate;
        this.user = user;
        this.umbrella = umbrella;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public  LocalDate getDate() {return  date;}

    public int getCost() {
        return cost;
    }

    public Profile getUser() {
        return user;
    }

    public Umbrella getUmbrella() {
        return umbrella;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public void setUmbrella(Umbrella umbrella) {
        this.umbrella = umbrella;
    }
}
