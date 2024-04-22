package com.project.ordercartAPI.service.impl;

import com.project.ordercartAPI.enumeration.PaymentMethod;
import com.project.ordercartAPI.model.Iten;
import com.project.ordercartAPI.model.OrderCart;
import com.project.ordercartAPI.model.Restaurant;
import com.project.ordercartAPI.repository.ItenRepository;
import com.project.ordercartAPI.repository.OrderCartRepository;
import com.project.ordercartAPI.repository.ProductRepository;
import com.project.ordercartAPI.resource.dto.ItenDto;
import com.project.ordercartAPI.service.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCartServiceImpl implements OrderCartService {

    private final OrderCartRepository orderCartRepository;
    private final ProductRepository productRepository;
    private final ItenRepository itenRepository;

    @Override
    public Iten addItenInOrderCart(ItenDto itenDto) {
        OrderCart orderCart = viewOrderCart(itenDto.getOrderCartId());
        if (orderCart.isClosed()) {
            throw new RuntimeException("This order cart is closed");
        }
        Iten itenToBeAdd = Iten.builder()
                .quantity(itenDto.getQuantity())
                .orderCart(orderCart)
                .product(productRepository.findById(itenDto.getOrderCartId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("This product does not exist");
                        }
                ))
                .build();
        List<Iten> orderCartItens = orderCart.getItens();
        if (orderCartItens.isEmpty()) {
            orderCartItens.add(itenToBeAdd);
        } else {
            Restaurant actualRestaurant = orderCartItens.get(0).getProduct().getRestaurant();
            Restaurant itenToBeAddRestaurant = itenToBeAdd.getProduct().getRestaurant();
            if (actualRestaurant.equals(itenToBeAddRestaurant)) {
                orderCartItens.add(itenToBeAdd);
            } else {
                throw new RuntimeException("It is not possible to add products from different restaurants. Close the order cart or empty it.");
            }
        }
        orderCartRepository.save(orderCart);
        return itenToBeAdd;
    }

    @Override
    public OrderCart viewOrderCart(Long id) {
        return orderCartRepository.findById(id).orElseThrow(
                () -> new RuntimeException("This order cart does not exist")
        );
    }

    @Override
    public OrderCart closeOrderCart(Long id, int paymentMethod) {
        OrderCart orderCart = viewOrderCart(id);
        if (orderCart.getItens().isEmpty()) {
            throw new RuntimeException("Empty order cart! Add itens to your order cart");
        }
        PaymentMethod paymentMethod1 =
                paymentMethod == 0 ? PaymentMethod.MONEY : PaymentMethod.CARD;
        orderCart.setPaymentMethod(paymentMethod1);
        orderCart.setClosed(true);
        return orderCartRepository.save(orderCart);
    }
}
