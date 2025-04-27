package com.deliveryapp.notification_service.model;

public class DeliveryNotification extends Notification{

    private String deliveryAddress;
    private String restaurantName;

    
    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    
    
}
