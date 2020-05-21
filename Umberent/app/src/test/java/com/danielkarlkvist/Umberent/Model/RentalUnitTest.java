package com.danielkarlkvist.Umberent.Model;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for the Stand class in Model.
 */
public class RentalUnitTest {

    private Umberent umberent = Umberent.getInstance();
    private IRental rental = umberent.getRental();

    @Test
    public void calculateRentalTime_isCorrect() {
        long start = 0;
        long end = 60;

        rental.setStartTime(start);
        assertEquals(start, rental.getStartTime());

        rental.setEndTime(end);
        assertEquals(end, rental.getEndTime());

        assertEquals(end - start, rental.calculateRentalTime(rental.getStartTime(), rental.getEndTime()));
    }

    @Test
    public void calculatePrice_isCorrect() {
        long start = 0;
        long end = 60;

        rental.setStartTime(start);
        assertEquals(start, rental.getStartTime());

        rental.setEndTime(end);
        assertEquals(end, rental.getEndTime());

        rental.setCost((int) rental.calculatePrice(rental.getStartTime(), rental.getEndTime()));

        assertEquals(rental.getCost(), rental.calculatePrice(rental.getStartTime(), rental.getEndTime()), 0);
    }
}