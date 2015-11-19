package com.example.davidyu.her;

import android.graphics.Bitmap;

import com.example.davidyu.her.Model.Gift;
import com.example.davidyu.her.Model.Tip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/16/2015.
 */
public class Singleton {

    private static List<Tip> tipList = new ArrayList<>();
    private static List<Gift> giftList = new ArrayList<>();
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

    private Singleton() {
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
