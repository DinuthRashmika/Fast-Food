package com.deliveryapp.notification_service.model;

import java.util.List;

public class RestaurantNotification extends Notification{

    private double total;
    private List<String> orderedItems;


    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public List<String> getOrderedItems() {
        return orderedItems;
    }
    public void setOrderedItems(List<String> orderedItems) {
        this.orderedItems = orderedItems;
    }
    
    
    
}
