package com.danielkarlkvist.umberent;

public class Umberent {

    IProfile profile;

    public Umberent() {
        profile = new Profile("Eric", "Jonsson", "hej@gmail.com", "hej", "123");
    }

    public IProfile getProfile() {
        return profile;
    }
}
