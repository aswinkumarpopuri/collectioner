package com.mac.training.collectioner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/19/2016.
 */
public class Item implements Parcelable {

    private String itemId;
    private String name;
    private String imageUrl;
    private String description;
    private double costedMe;
    private double price; //in the market
    private String location;
    private double location_latitude;
    private double location_longitude;
    private String condition;

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

    protected Item(Parcel in) {
        this.itemId = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.description = in.readString();
        this.costedMe = in.readDouble();
        this.price = in.readDouble();
        this.location = in.readString();
        this.location_latitude = in.readDouble();
        this.condition = in.readString();
        this.location_longitude = in.readDouble();
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostedMe() {
        return costedMe;
    }

    public void setCostedMe(double costedMe) {
        this.costedMe = costedMe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(double location_latitude) {
        this.location_latitude = location_latitude;
    }

    public double getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(double location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeString(this.description);
        dest.writeDouble(this.costedMe);
        dest.writeDouble(this.price);
        dest.writeString(this.location);
        dest.writeDouble(this.location_latitude);
        dest.writeString(this.condition);
        dest.writeDouble(this.location_longitude);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", costedMe=" + costedMe +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", location_latitude=" + location_latitude +
                ", location_longitude=" + location_longitude +
                ", condition='" + condition + '\'' +
                '}';
    }
}
