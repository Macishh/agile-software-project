package com.danielkarlkvist.Umberent.Model;

import java.time.LocalDate;

/**
 * The IRental interface defines the method that should be able to run of a Rental outside of the model.
 */
public interface IRental {

     long getStartTime();
     long getEndTime();
     long getTotalTime();
     long calculateRentalTime(long startTime, long endTime);
     long calculatePrice(long startTime, long endTime);
     LocalDate getDate();
     int getCost();
     Profile getUser();
     Umbrella getUmbrella();

     void setStartTime(long startTime);
     void setEndTime(long endTime);
     void setTotalTime(long totalTime);
     void setDate(LocalDate date);
     void setCost(int cost);
     void setUser(Profile user);
     void setUmbrella(Umbrella umbrella);

}
