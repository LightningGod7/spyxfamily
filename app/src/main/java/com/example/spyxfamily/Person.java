package com.example.spyxfamily;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private String description;
    private int imagePath;

    public Person() {

    }

    public Person(String name, String description, int imagePath) {
        this.id = 0;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

}