package com.example.mutrasf;

import android.graphics.Bitmap;

// Food truck it self
public class FoodTruckObject {
    private int id; // id of the ood truck
    private String name; // name of the ood truck
    private String category; // category of the ood truck
    private String description; // description of the ood truck
    private int phoneNumber; // Phone number to contact
    private float Price; // Price of the ood truck
    private Bitmap photo; // photo of the ood truck

    //Constructor
    public FoodTruckObject(int id, String name, String category, String description, int phoneNumber, float price, Bitmap photo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.phoneNumber = phoneNumber;
        Price = price;
        this.photo = photo;
    }

    //Setters and getters
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "FoodTruck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", Price=" + Price +
                ", photo=" + photo +
                '}';
    }
}