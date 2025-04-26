package com.example.deliverKavishka.controller;

import com.example.deliverKavishka.dto.DeliveryDTO;
import com.example.deliverKavishka.model.Delivery;
import com.example.deliverKavishka.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        Delivery delivery = deliveryService.createDelivery(deliveryDTO);
        return ResponseEntity.ok(delivery);
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<Delivery> assignDriver(@PathVariable String id) {
        Delivery delivery = deliveryService.assignDriver(id);
        return ResponseEntity.ok(delivery);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable String id) {
        return deliveryService.getDelivery(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }
}