package com.lldproject.blinkitecommerce.services;

import com.lldproject.blinkitecommerce.exceptions.*;
import com.lldproject.blinkitecommerce.models.Order;
import org.springframework.data.util.Pair;

import java.util.List;

public interface OrderService {

    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws UserNotFoundException, InvalidAddressException, OutOfStockException, ProductNotFoundException, HighDemandProductException;
}
