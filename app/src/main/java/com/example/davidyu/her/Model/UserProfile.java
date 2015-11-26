package com.example.davidyu.her.Model;

import java.util.ArrayList;

/**
 * Created by Johnson on 11/25/15.
 */
public class UserProfile{

    String userID;

    String myAge;
    String myOccupation;
    String myEarnings;

    String herAge;
    String herOccupation;
    String herEarnings;

    String personality;
    ArrayList<String> hobby = new ArrayList<String>();
    String relationship;

    public void setMyOccupation(String occupation) {
        this.myOccupation = occupation;
    }

    public void setMyAge(String age) {
        this.myAge = age;
    }

    public void setMyEarnings(String earnings) {
        this.myEarnings = earnings;
    }

    public void setHerOccupation(String occupation) {
        this.herOccupation = occupation;
    }

    public void setHerAge(String age) {
        this.herAge = age;
    }

    public void setHerEarnings(String earnings) {
        this.herEarnings = earnings;
    }

    public void setHerPersonality(String personality) {
        this.personality = personality;
    }

    public void setHerRelation(String relation) {
        this.relationship = relation;
    }

    public String getPersonality() {
        return personality;
    }

    public String getMyAge() {
        return myAge;
    }

    public void addHobby(String hobby) {
        if (!this.hobby.contains(hobby))
            this.hobby.add(hobby);
    }

    public void cancelHobby(String hobby) {
        int i = this.hobby.indexOf(hobby);
        this.hobby.remove(i);
    }

    public ArrayList getHobby() {
        return this.hobby;
    }
}
