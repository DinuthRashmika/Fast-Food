package com.deliveryapp.notification_service.model;

import java.util.List;

public class CustomerNotification extends Notification{

    private double total;
    private String restaurantName;
    private List<String> orderedItems;

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public List<String> getOrderedItems() {
        return orderedItems;
    }
    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }

    
    
}
