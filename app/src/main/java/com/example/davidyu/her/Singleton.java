package com.example.davidyu.her;

import android.graphics.Bitmap;

import com.example.davidyu.her.Model.Gift;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.models.GroupEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/16/2015.
 */
public class Singleton {

    private static UserProfile user = new UserProfile();

    public static UserProfile getUserInstance(){
        return user;
    }

    private static List<Tip> tipList = new ArrayList<>();
    private static List<Gift> giftList = new ArrayList<>();
    private static List<GroupEntity> timeline = new ArrayList<>();
    private static List<String> hobbyList = new ArrayList<>();
    private static String currentPersonality;

    public static String getCurrentRelationship() {
        return currentRelationship;
    }

    public static void setCurrentRelationship(String currentRelationship) {
        Singleton.currentRelationship = currentRelationship;
    }

    private static String currentRelationship;

    public static String getCurrentPersonality() {
        return currentPersonality;
    }

    public static void setCurrentPersonality(String currentPersonality) {
        Singleton.currentPersonality = currentPersonality;
    }

    public static List<String> getHobbyList() {
        return hobbyList;
    }

    public static void setHobbyList(List<String> hobbyList) {
        Singleton.hobbyList = hobbyList;
    }

    private static String userID = "1";

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        Singleton.userID = userID;
    }

    public static List<GroupEntity> getTimeline() {
        return timeline;
    }

    public static void setTimeline(List<GroupEntity> timeline) {
        Singleton.timeline = timeline;
    }

    private static int[] screenLocation = new int[2];
    private static int[] size = new int[2];
    private static String tipsText;

    public static String getTipsText() {
        return tipsText;
    }

    public static void setTipsText(String tipsText) {
        Singleton.tipsText = tipsText;
    }

    public static int[] getSize() {
        return size;
    }

    public static void setSize(int[] size) {
        Singleton.size = size;
    }

    public static int[] getScreenLocation() {
        return screenLocation;
    }

    public static void setScreenLocation(int[] screenLocation) {
        Singleton.screenLocation = screenLocation;
    }

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        Singleton.bitmap = bitmap;
    }

    private static Bitmap bitmap;

    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    public Singleton() {
    }

    public static List<Tip> getTipList(){
        return tipList;
    }

    public static void setTipList(List<Tip> inputList){
        tipList = inputList;
    }

    public static List<Gift> getGiftList() {
        return giftList;
    }

    public static void setGiftList(List<Gift> giftList) {
        Singleton.giftList = giftList;
    }
}
