package com.danielkarlkvist.Umberent.Model;

import java.time.LocalDate;

/**
 * The Rental class contains all the information for when a user wants to rent an umbrella.
 */
class Rental implements IRental {
    private long startTime;
    private long endTime;
    private long totalTime;
    private LocalDate date;
    private int cost;
    private Profile user;
    private Umbrella umbrella;

    Rental(long startTime, long endTime, long totalTime, LocalDate date, int cost, Profile user, Umbrella umbrella) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
        this.date = date;
        this.cost = cost;
        this.user = user;
        this.umbrella = umbrella;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public LocalDate getDate() {
        return  date;
    }

    public int getCost() {
        return cost;
    }

    public IProfile getUser() {
        return user;
    }

    public IUmbrella getUmbrella() {
        return umbrella;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
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

    public long calculateRentalTime(long startTime, long endTime) {
        return endTime - startTime;
    }

    public long calculatePrice(long startTime, long endTime){
        long difference = endTime - startTime;
        return (2 * ((difference) / 1000)) / 60;
    }

    @Override
    public String toString() {
        return "Rental is: " + this.startTime + " " +  this.endTime + " " + this.totalTime + " " + this.date.getDayOfMonth() + " " + this.cost + " " + this.user.toString() + " " + this.umbrella;
    }
}
