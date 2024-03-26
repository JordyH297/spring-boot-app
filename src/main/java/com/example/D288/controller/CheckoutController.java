package com.example.D288.controller;

import com.example.D288.services.Checkout;
import com.example.D288.services.Purchase;
import com.example.D288.services.PurchaseResponse;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private Checkout checkout;
    public CheckoutController(Checkout checkout) {
        this.checkout = checkout;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkout.placeOrder(purchase);
        return purchaseResponse;
    }
}