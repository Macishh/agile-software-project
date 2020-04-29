package com.danielkarlkvist.umberent;

public interface IProfile {

    String getFullName();

    String getMail();

    String getUsername();

    String getCardNumber();

    String getExpirationDate();

    String getCvc();


    String getFirstName();

    String getLastName();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setUsername(String username);

    void setMail(String username);
}
