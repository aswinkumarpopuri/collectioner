package com.mac.training.collectioner.pojo;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/19/2016.
 */
public class Item {

    public String name;
    public String image;
    public String description;
    public double costedMe;
    public double price; //in the market
    public String location;
    public String condition;

    public Item() {
    }

    public Item(String name, String description, double costedMe, double price, String location, String condition, String image) {
        this.name = name;
        this.description = description;
        this.costedMe = costedMe;
        this.price = price;
        this.location = location;
        this.condition = condition;
        this.image = image;
    }

    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("description", description);
        result.put("costedMe", costedMe);
        result.put("price", price);
        result.put("location", location);
        result.put("condition", condition);
        result.put("image", image);

        return result;
    }

}
