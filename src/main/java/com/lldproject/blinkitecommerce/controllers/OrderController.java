package com.lldproject.blinkitecommerce.controllers;

import com.lldproject.blinkitecommerce.dtos.*;
import com.lldproject.blinkitecommerce.models.Order;
import com.lldproject.blinkitecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) {
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try{
            Order order = orderService.placeOrder(placeOrderRequestDto.getUserId(), placeOrderRequestDto.getAddressId(), placeOrderRequestDto.getOrderDetails());
            responseDto.setOrder(order);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public CancelOrderResponseDto cancelOrder(CancelOrderRequestDto cancelOrderRequestDto) {
        CancelOrderResponseDto responseDto = new CancelOrderResponseDto();
        try{
            Order order = orderService.cancelOrder(cancelOrderRequestDto.getOrderId(), cancelOrderRequestDto.getUserId());
            responseDto.setOrder(order);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }

}
