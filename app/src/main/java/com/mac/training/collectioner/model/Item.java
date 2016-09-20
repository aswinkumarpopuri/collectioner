package com.mac.training.collectioner.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/19/2016.
 */
public class Item {

    public String itemId;
    public String name;
    public String imageUrl;
    public String description;
    public double costedMe;
    public double price; //in the market
    public String location;
    public double location_latitude;
    public double location_longitude;
    public String condition;

    public Item() {
    }

    public Item(String itemId,
                String name,
                String imageUrl,
                String description,
                double costedMe,
                double price,
                String location,
                double location_latitude,
                double location_longitude,
                String condition) {
        this.itemId = itemId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.costedMe = costedMe;
        this.price = price;
        this.location = location;
        this.location_latitude = location_latitude;
        this.condition = condition;
        this.location_longitude = location_longitude;
    }

    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("itemId", itemId);
        result.put("name", name);
        result.put("imageUrl", imageUrl);
        result.put("description", description);
        result.put("costedMe", costedMe);
        result.put("price", price);
        result.put("location", location);
        result.put("location_latitude", location_latitude);
        result.put("location_longitude", location_longitude);
        result.put("condition", condition);

        return result;
    }

}
