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

    public void addUser(IProfile profile, String password) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("firstName", profile.getFirstName());
        userInformation.put("lastName", profile.getLastName());
        userInformation.put("mail", profile.getMail());
        userInformation.put("password", password);

        database.collection("Users").document(profile.getMail()).set(userInformation);
    }

    public void updateFirstNameTo(String firstName, IProfile profile) {
        Map<String, Object> userInformation = getUserInformation(profile);
        userInformation.put("firstName", firstName);

        database.collection("Users").document(profile.getMail()).set(userInformation);
    }

    public void updateLastNameTo(String lastName, IProfile profile) {
        Map<String, Object> userInformation = getUserInformation(profile);
        userInformation.put("lastName", lastName);

        database.collection("Users").document(profile.getMail()).set(userInformation);
    }

    public void updateMailTo(String mail, IProfile profile) {
        Map<String, Object> userInformation = getUserInformation(profile);
        userInformation.put("mail", mail);

        database.collection("Users").document(profile.getMail()).set(userInformation);
    }

    public void removeUser(IProfile profile) {
        database.collection("Users").document(profile.getMail()).delete();
    }

    private Map<String, Object> getUserInformation(IProfile profile) {
        Map<String, Object> userInformation = new HashMap<>();
        userInformation.put("firstName", profile.getFirstName());
        userInformation.put("lastName", profile.getLastName());
        userInformation.put("mail", profile.getMail());

        return userInformation;
    }
}
