package com.deliveryapp.notification_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryapp.notification_service.model.Notification;
import com.deliveryapp.notification_service.model.RestaurantNotification;
import com.deliveryapp.notification_service.service.RestaurantNotificationService;

@RestController
@RequestMapping("/api/v1/notifications/restaurent")
public class RestaurentNotificationController {

    private RestaurantNotificationService restaurantNotificationService;

    @Autowired
    public RestaurentNotificationController(RestaurantNotificationService restaurantNotificationService){
        this.restaurantNotificationService = restaurantNotificationService;
    }
    

    @PostMapping("/new-order")
    public ResponseEntity<Notification> RestaurentNewOrderNotification(@RequestBody RestaurantNotification notification) {
        Notification savedNotification = restaurantNotificationService.RestaurentNewOrderNotification(notification);
        return ResponseEntity.ok(savedNotification);
    }

    @PostMapping("/order-accepted")
    public ResponseEntity<Notification> RestaurantOrderAcceptedNotification(@RequestBody RestaurantNotification notification) {
        Notification savedNotification = restaurantNotificationService.RestaurantOrderAcceptedNotification(notification);
        return ResponseEntity.ok(savedNotification);
    }

    @PostMapping("/order-cancelled")
    public ResponseEntity<Notification> RestaurantOrderCancelledNotification(@RequestBody RestaurantNotification notification) {
        Notification savedNotification = restaurantNotificationService.RestaurantOrderCancelledNotification(notification);
        return ResponseEntity.ok(savedNotification);
    }

}