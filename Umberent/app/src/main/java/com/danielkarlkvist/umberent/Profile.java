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

    public Profile(String firstName, String lastName, String username, String mail, String password, String cardNumber, String expirationDate, String cvc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

}