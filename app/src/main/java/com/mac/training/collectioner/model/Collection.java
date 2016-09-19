package com.mac.training.collectioner.model;

/**
 * Created by User on 9/19/2016.
 */
public class Collection {

    String name;
    String description;

    public Collection() {
    }

    public Collection(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
