package com.example.D288.services;

import com.example.D288.dao.*;
import com.example.D288.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutImpl implements Checkout {
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final VacationRepository vacationRepository;
    private final ExcursionRepository excursionRepository;
    private final CartItemRepository cartItemRepository;

    public CheckoutImpl(CustomerRepository customerRepository, CartRepository cartRepository,
                        VacationRepository vacationRepository, ExcursionRepository excursionRepository,
                        CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.vacationRepository = vacationRepository;
        this.excursionRepository = excursionRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        Set<CartItem> cartItems = purchase.getCartItems();

        Vacation vacation = cartItems.stream().findFirst().map(CartItem::getVacation).orElse(null);

        if (vacation == null) {
            throw new IllegalArgumentException("Vacation cannot be null.");
        }

        for (CartItem cartItem : cartItems) {
            cart.add(cartItem);

            Set<Excursion> excursions = vacation.getExcursions();

            if (excursions != null) {
                for (Excursion excursion : excursions) {
                    if (excursion.getVacation() == null || excursion.getVacation().getId() == null) {
                        excursion.setVacation(vacation);
                        if (vacation.getId() == null) {
                            vacation = vacationRepository.save(vacation);
                        }
                    }
                    excursionRepository.save(excursion);
                }

            }
            vacationRepository.save(vacation);
        }


        cart.setStatus(StatusType.ordered);
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        cartRepository.save(cart);
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }




    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
