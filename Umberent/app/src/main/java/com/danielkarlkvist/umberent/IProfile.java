package com.danielkarlkvist.umberent;

public interface IProfile {

    String getFullName();

    String getMail();

    String getUsername();

    String getFirstName();

    String getLastName();

    String getCardNumber();

    String getExpirationDate();

    String getCvc();


    String getFirstName();

    String getLastName();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setUsername(String username);

    void setMail(String username);

    void setCardNumber(String cardNumber);

    void setExpirationDate(String expirationDate);

    void setCvc(String cvc);




}
