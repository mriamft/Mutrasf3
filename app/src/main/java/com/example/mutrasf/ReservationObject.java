package com.example.mutrasf;


// Represent a reservation of a food truck for a user
public class ReservationObject {
    private int reservationId; // id of the reservation
    private String time; // time of the reservation
    private String date; // date of the reservation
    private int foodTruckId; // id of food truck which is reserved
    private int userID; // id of a user who is reserved the food truck

    //Constructor that will help in adding to reservation page
    public ReservationObject(int reservationId, String time, String date, int foodTruckId, int userID) {
        this.reservationId = reservationId;
        this.time = time;
        this.date = date;
        this.foodTruckId = foodTruckId;
        this.userID = userID;
    }

    //Setters and getters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFoodTruckId() {
        return foodTruckId;
    }

    public void setFoodTruckId(int foodTruckId) {
        this.foodTruckId = foodTruckId;
    }

    public int getUserID() {
        return userID;
    }
}