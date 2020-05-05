package com.danielkarlkvist.umberent.Model;

/**
 * The IProfile interface defines the method that should be able to run of a Profile outside of the model.
 */
public interface IProfile {

    String getFullName();

    String getFirstName();

    String getLastName();

    String getMail();

    String getCardNumber();

    String getExpirationDate();

    String getCVC();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setCardNumber(String cardNumber);

    void setExpirationDate(String expirationDate);

    void setCVC(String cvc);
}
