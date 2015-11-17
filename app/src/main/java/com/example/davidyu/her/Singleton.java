package com.example.davidyu.her;

import com.example.davidyu.her.Model.Tip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/16/2015.
 */
public class Singleton {

    private static List<Tip> tipList = new ArrayList<>();

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
}
