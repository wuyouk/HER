package com.example.davidyu.her.Authenticator;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Class to save user information inside phone
 */
public class UserLocalStore {

    public static final String SHARED_PREFERENCE_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    //constructor to get shared preferences from context
    public UserLocalStore(Context c){
        userLocalDatabase = c.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
    }

    //save user object to shared preferences
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();

        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putString("userID", user.id);

        spEditor.commit();
    }

    //get user object currently saved inside phone
    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        String id = userLocalDatabase.getString("userID", "");

        User storedUser = new User(name, username, password,id);

        return storedUser;
    }

    //set whether the user has logged in or not
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();

        spEditor.putBoolean("loggedIn", loggedIn);

        spEditor.commit();
    }

    //clear all data when user logs out
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();

        spEditor.clear();

        spEditor.commit();
    }

    public boolean isUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false))
            return true;

        return false;
    }
}
