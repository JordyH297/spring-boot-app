package com.example.D288.services;

import com.example.D288.entity.Cart;
import com.example.D288.entity.CartItem;
import com.example.D288.entity.Customer;

import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Cart cart;
    private Set<CartItem> cartItems;

}