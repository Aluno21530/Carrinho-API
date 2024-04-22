package com.project.ordercartAPI.resource;

import com.project.ordercartAPI.model.Iten;
import com.project.ordercartAPI.model.OrderCart;
import com.project.ordercartAPI.resource.dto.ItenDto;
import com.project.ordercartAPI.service.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordercarts")
@RequiredArgsConstructor
public class OrderCartResource {

    private final OrderCartService orderCartService;



    @PostMapping
    public Iten addItenInOrderCart(@RequestBody ItenDto itenDto){
        return orderCartService.addItenInOrderCart(itenDto);
    }
    @GetMapping("/{id}")
    public OrderCart viewOrderCart(@PathVariable("id") Long id){
        return orderCartService.viewOrderCart(id);
    }
    @PutMapping("/closeordercart/{orderCartId}")
    public OrderCart closeOrderCart(@PathVariable("orderCartId") Long orderCartId,@RequestParam("paymentMethod") int paymentMethod){
        return orderCartService.closeOrderCart(orderCartId, paymentMethod);
    }
}
