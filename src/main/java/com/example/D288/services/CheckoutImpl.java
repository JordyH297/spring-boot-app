package com.example.D288.services;

import com.example.D288.dao.CartRepository;
import com.example.D288.dao.CustomerRepository;
import com.example.D288.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutImpl implements Checkout {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository; // Add CartRepository for saving the cart

    public CheckoutImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));
        cart.setStatus(StatusType.ordered); // Set the cart's status to 'ordered'
        cartRepository.save(cart); // Save the cart with the updated status
        Customer customer = purchase.getCustomer();
        customer.add(cart);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
