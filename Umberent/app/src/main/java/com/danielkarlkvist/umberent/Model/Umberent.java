package com.danielkarlkvist.umberent.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Umberent class is the entry to the model and contains the users profile as well as the stands.
 */
public class Umberent {
    private static Umberent instance;

    private Profile profile;
    private List<IStand> stands = new ArrayList<>();
    private Rental rental;
    private Umbrella umbrella;

    private boolean userIsLoggedIn = false;

    private Umberent() {
        profile = new Profile("Eric", "Jonsson", "hej@gmail.com", "123");
        rental = new Rental(System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), LocalDate.now(), 0, profile, umbrella);
        umbrella = new Umbrella(1, true);
        createStands();
    }

    // temporary solution before database is implemented
    private void createStands() {
        Integer idNumber = 0;
        Random random = new Random();

        int capacity = random.nextInt(50) + 3;
        Stand stand1 = new Stand(0, "Emilsborg", capacity, 57.680960, 11.984787);
        addUmbrellas(stand1, idNumber, capacity);

        capacity = random.nextInt(50) + 3;
        Stand stand2 = new Stand(1, "Chalmers", capacity, 57.686330588, 11.972662776);
        addUmbrellas(stand2, idNumber, capacity);

        stands.add(stand1);
        stands.add(stand2);
    }
    private void addUmbrellas(Stand stand, Integer id, int capacity) {
        Random random = new Random();
        int amountOfUmbrellas = random.nextInt(capacity + 1);

        for (int i = 0; i < amountOfUmbrellas; i++) {
            boolean isAvailable = random.nextBoolean();

            stand.addUmbrella(new Umbrella(id, isAvailable));
            id++;
        }
    }

    public static Umberent getInstance() {
        if (instance == null) {
            instance = new Umberent();
        }

        return instance;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<IStand> getStands() {
        return stands;
    }

    public boolean userIsLoggedIn() {
        return userIsLoggedIn;
    }

    public void setUserIsLoggedIn(boolean userIsLoggedIn) {
        this.userIsLoggedIn = userIsLoggedIn;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
