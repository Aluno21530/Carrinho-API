package com.project.ordercartAPI.service;

import com.project.ordercartAPI.model.Iten;
import com.project.ordercartAPI.model.OrderCart;
import com.project.ordercartAPI.resource.dto.ItenDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderCartService {

    Iten addItenInOrderCart(ItenDto itenDto);
    OrderCart viewOrderCart(Long id);
    OrderCart closeOrderCart(Long id, int paymentMethod);

}
