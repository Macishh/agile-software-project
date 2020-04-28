package com.danielkarlkvist.umberent;

public class Umberent {

    IProfile profile;

    public Umberent() {
        profile = new Profile("Eric", "Jonsson", "hej@gmail.com", "hej", "123", "9999 8888 7777 6666", "23/45", "978");
    }

    public IProfile getProfile() {
        return profile;
    }
}
