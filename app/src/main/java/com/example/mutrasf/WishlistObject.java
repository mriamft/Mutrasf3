package com.example.mutrasf;

// Represents a Food truck in wishList
public class WishlistObject {
    private int id; // id of the food truck in the wishlist
    private int FTid; // id of the food truck
    private String name; // name of the food truck
    private byte[] foodTruckPhoto; // photo of the food truck

    //Constructor that will help in adding to wishlist page
    public WishlistObject(int id, int FTid, String name, byte[] foodTruckPhoto) {
        this.id = id;
        this.FTid = FTid;
        this.name = name;
        this.foodTruckPhoto = foodTruckPhoto;
    }

    // Setters and Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFTid() {
        return FTid;
    }

    public void setFTid(int FTid) {
        this.FTid = FTid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFoodTruckPhoto() {
        return foodTruckPhoto;
    }

    public void setFoodTruckPhoto(byte[] foodTruckPhoto) {
        this.foodTruckPhoto = foodTruckPhoto;
    }
}