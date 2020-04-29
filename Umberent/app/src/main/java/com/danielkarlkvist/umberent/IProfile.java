package com.danielkarlkvist.umberent;

public interface IProfile {

    String getFullName();

    String getMail();

    String getUsername();

    String getFirstName();

    String getLastName();

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setUsername(String username);

    void setMail(String username);
    String getCardNumber();

    String getExpirationDate();

    String getCvc();


}
