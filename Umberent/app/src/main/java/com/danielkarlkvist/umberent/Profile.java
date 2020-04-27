package com.danielkarlkvist.umberent;

public class Profile implements IProfile {

    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String password;

    private String cardNumber;
    private String expirationDate;
    private String cvc;

    public Profile(String firstName, String lastName, String mail, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }


}
