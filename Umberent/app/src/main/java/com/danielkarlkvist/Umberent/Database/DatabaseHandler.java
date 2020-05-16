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
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", profile.getFirstName());
        user.put("lastName", profile.getLastName());
        user.put("mail", profile.getMail());
        user.put("password", password);

        database.collection("Users").document(profile.getMail()).set(user);
    }
}
