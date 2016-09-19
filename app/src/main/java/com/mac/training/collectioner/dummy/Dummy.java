package com.mac.training.collectioner.dummy;

import java.util.Random;


public class Dummy {

    static String[] user   = {"Josimar", "Ashwini", "Jaime", "Rohan", "Jonathan", "Nicholas"};
    static String[] collection = {"gadgets", "cars", "coins", "posts", "cards", "albums"};
    static Random rand = new Random();

    public static String genUser(){
        return user[rand.nextInt(6)];
    }

    public static String genCollection(){
        return collection[rand.nextInt(6)];
    }

}
