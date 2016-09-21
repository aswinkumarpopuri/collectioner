package com.mac.training.collectioner.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/19/2016.
 */
public class Collection implements Parcelable {

    private String colId;
    private String collectionName;
    private String description;

    public Collection() {
    }

    public Collection(String name, String description) {
        this.collectionName = name;
        this.description = description;
    }

    public Collection(String colId, String name, String description) {
        this.colId = colId;
        this.collectionName = name;
        this.description = description;
    }

    protected Collection(Parcel in) {
        this.colId = in.readString();
        this.collectionName = in.readString();
        this.description = in.readString();
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collection) {
        this.collectionName = collection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("colId", colId);
        result.put("collectionName", collectionName);
        result.put("description", description);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.colId);
        dest.writeString(this.collectionName);
        dest.writeString(this.description);
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };

}