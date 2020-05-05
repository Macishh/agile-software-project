package com.danielkarlkvist.umberent.Model;

/**
 * The Profile class contains the information for the user of the app.
 */
class Profile implements IProfile {

    private String firstName;
    private String lastName;
    private String mail;
    private String password;

    private String cardNumber;
    private String expirationDate;
    private String cvc;

    public Profile(String firstName, String lastName, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    // Possibly remove?
    public Profile(String firstName, String lastName, String mail, String password, String cardNumber, String expirationDate, String cvc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCVC() {
        return cvc;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void setCVC(String cvc) {
        this.cvc = cvc;
    }
}
