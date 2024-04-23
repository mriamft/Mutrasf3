package com.example.mutrasf;


import java.util.Arrays;

public class FoodTruck {
    private int id;
    private String name;
    private String category;
    private String description;
    private int phoneNumber;
    private int Status;
    private float Price;
    byte[] photo;

    public FoodTruck(int id, String name, String category, String description, int phoneNumber, int status, float price, byte[] photo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.phoneNumber = phoneNumber;
        Status = status;
        Price = price;
        this.photo = photo;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
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
                ", Status=" + Status +
                ", Price=" + Price +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}