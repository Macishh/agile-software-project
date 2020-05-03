package com.danielkarlkvist.umberent;

public class Umberent {

    private static Umberent instance;

    private boolean userIsLoggedIn = false;

    private IProfile profile;

    private Umberent() {
        profile = new Profile("Eric", "Jonsson", "hej@gmail.com", "hej", "123", "9999 8888 7777 6666", "23/45", "978");
    }

    public static Umberent getInstance() {
        if (instance == null) {
            instance = new Umberent();
        }

        return instance;
    }

    public IProfile getProfile() {
        return profile;
    }

    public boolean userIsLoggedIn() {
        return userIsLoggedIn;
    }

    public void setUserIsLoggedIn(boolean userIsLoggedIn) {
        this.userIsLoggedIn = userIsLoggedIn;
    }
}
