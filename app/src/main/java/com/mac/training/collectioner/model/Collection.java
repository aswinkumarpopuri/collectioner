package com.mac.training.collectioner.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/19/2016.
 */
public class Collection {

    public String collection;

    public Collection(){}

    public Collection(String name) {
        this.collection = name;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("collection", collection);
        return result;
    }
}