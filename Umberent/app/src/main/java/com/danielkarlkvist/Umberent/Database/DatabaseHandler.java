package com.danielkarlkvist.Umberent.Database;

import com.danielkarlkvist.Umberent.Model.IProfile;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler {
    private static DatabaseHandler instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private DatabaseHandler() {

    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }

    /**
     * Adds all user information to the database.
     * @param profile
     * @param password
     */
    public void addUser(IProfile profile, String password) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("firstName", profile.getFirstName());
        userInformation.put("lastName", profile.getLastName());
        userInformation.put("mail", profile.getMail());
        userInformation.put("password", password);

        database.collection("Users").document(profile.getMail()).set(userInformation);
    }

    /**
     * Updates the current user's first name to the database.
     * @param firstName
     * @param profile
     */
    public void updateFirstNameTo(String firstName, IProfile profile) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("firstName", firstName);

        database.collection("Users").document(profile.getMail()).update(userInformation);
    }

    /**
     * Updates the current user's last name to the database.
     * @param lastName
     * @param profile
     */
    public void updateLastNameTo(String lastName, IProfile profile) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("lastName", lastName);

        database.collection("Users").document(profile.getMail()).update(userInformation);
    }

    /**
     * Updates the current user's mail to the database.
     * @param mail
     * @param profile
     */
    public void updateMailTo(String mail, IProfile profile) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("mail", mail);

        database.collection("Users").document(profile.getMail()).update(userInformation);
    }

    /**
     * Removes the current user from the database.
     * @param profile
     */
    public void removeUser(IProfile profile) {
        database.collection("Users").document(profile.getMail()).delete();
    }

}
