package com.example.auth.view.Repository;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationRepository {

    private final FirebaseAuth firebaseAuth;
    private static final String TAG = "AuthRepository";

    public AuthenticationRepository() {

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void register(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

//                    String userId = task.getResult().getUser().getUid();
//                    User user = new User(email, pass);
//                    saveUserToFirestore(user);
                Log.d(TAG, "createUserWithEmailAndPassword:success");
            } else {
                Log.w(TAG, "createUserWithCredential:failure", task.getException());
            }
        });
    }

    public void login(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "signInWithEmailAndPassword:success");
            } else {
                Log.w(TAG, "signInWithEmailAndPassword:failure", task.getException());
            }
        });
    }
//    Saving extra info taken at registration to firestore

//    private void saveUserToFirestore(User user) {
//        User newUser = new User();
//        newUser.setUserId("USER_PHONE_NUMBER"); // Set the user's phone number as the User ID
//        newUser.setPhoneNumber("USER_PHONE_NUMBER"); // Set the user's phone number as per your requirement
////        newUser.setCountryCode("SELECTED_COUNTRY_CODE"); // Set the selected country code from the Country Code Picker
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("users")
//                .document(user.getUserId())
//                .set(user)
//                .addOnSuccessListener(aVoid -> {
//                    // User data saved successfully
//                    Log.d(TAG, "User data saved successfully.");
//                })
//                .addOnFailureListener(e -> {
//                    // Error occurred while saving user data
//                    Log.e(TAG, "Error saving user data: " + e.getMessage());
//                });
//
//    }
}
