package com.driver;

public class Order {

    private String id;
    private int deliveryTime;


    public Order(String id, String deliveryTime) {
        this.id = id;

        // Split the delivery time into hours and minutes
        String[] parts = deliveryTime.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        // Convert the delivery time to minutes
        this.deliveryTime = hours * 60 + minutes;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    private DeliveryPartner partner;

    // Getter method for the partner field
    public DeliveryPartner getPartner(){
        return this.partner;
    }
}
