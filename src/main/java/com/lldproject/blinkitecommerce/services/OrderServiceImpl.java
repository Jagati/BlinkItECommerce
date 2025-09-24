package com.lldproject.blinkitecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lldproject.blinkitecommerce.exceptions.*;
import org.springframework.data.util.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.blinkitecommerce.models.Address;
import com.lldproject.blinkitecommerce.models.HighDemandProduct;
import com.lldproject.blinkitecommerce.models.Inventory;
import com.lldproject.blinkitecommerce.models.Order;
import com.lldproject.blinkitecommerce.models.OrderStatus;
import com.lldproject.blinkitecommerce.models.Product;
import com.lldproject.blinkitecommerce.models.OrderDetail;
import com.lldproject.blinkitecommerce.models.User;
import com.lldproject.blinkitecommerce.repositories.AddressRepository;
import com.lldproject.blinkitecommerce.repositories.HighDemandProductRepository;
import com.lldproject.blinkitecommerce.repositories.InventoryRepository;
import com.lldproject.blinkitecommerce.repositories.OrderDetailRepository;
import com.lldproject.blinkitecommerce.repositories.OrderRepository;
import com.lldproject.blinkitecommerce.repositories.ProductRepository;
import com.lldproject.blinkitecommerce.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final AddressRepository addressRepository;
    private final HighDemandProductRepository highDemandProductRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(AddressRepository addressRepository, HighDemandProductRepository highDemandProductRepository, InventoryRepository inventoryRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.highDemandProductRepository = highDemandProductRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws UserNotFoundException, InvalidAddressException, OutOfStockException, ProductNotFoundException, HighDemandProductException{
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        User user = userOp.get();
        Optional<Address> addressOp = addressRepository.findById(addressId);
        if(addressOp.isEmpty()){
            throw new InvalidAddressException("address not found");
        }
        Address address = addressOp.get();
        if(!user.getAddresses().contains(address)){
            throw new InvalidAddressException("address does not belong to user");
        }
        Order order = new Order();
        order.setUser(user);
        order.setDeliveryAddress(address);
        List<OrderDetail> finalOrderDetails = new ArrayList<>();
        for(Pair<Integer, Integer> orderDetail: orderDetails){
            int requiredProductId = orderDetail.getFirst();
            int requiredQuantity = orderDetail.getSecond();
            Optional<Product> productOp = productRepository.findById(requiredProductId);
            if(productOp.isEmpty()){
                throw new ProductNotFoundException("Product doesn't exist");
            }
            Product product = productOp.get();
            Optional<Inventory> inventoryOp = inventoryRepository.findByProduct_Id(requiredProductId);
            if(inventoryOp.isEmpty()){
                throw new OutOfStockException("product out of stock");
            }
            Inventory inventory = inventoryOp.get();
            int quantityInStock = inventory.getQuantity();
            if(quantityInStock<requiredQuantity){
                throw new OutOfStockException("the required quantity cannot be fetched as few items are out of stock.");
            }

            Optional<HighDemandProduct> highDemandProductOp = highDemandProductRepository.findByProduct_Id(requiredProductId);
            if(highDemandProductOp.isPresent()){
                HighDemandProduct highDemandProduct = highDemandProductOp.get();
                int maxQuantityAllowed = highDemandProduct.getMaxQuantity();
                if(requiredQuantity>maxQuantityAllowed){
                    throw new HighDemandProductException("This product has high demand and required quantity cannot be added to cart.");
                }
            }

            inventory.setQuantity(quantityInStock-requiredQuantity);
            inventoryRepository.save(inventory);
            OrderDetail detail = new OrderDetail();
            detail.setProduct(inventory.getProduct());
            detail.setQuantity(requiredQuantity);
            orderDetailRepository.save(detail);
            finalOrderDetails.add(detail);
        }
        order.setOrderDetails(finalOrderDetails);
        order.setOrderStatus(OrderStatus.PLACED);
        order = orderRepository.save(order);
        for (OrderDetail orderDetail : finalOrderDetails) {
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }
        return order;
    }

    @Override
    @Transactional
    public Order cancelOrder(int orderId, int userId)  throws UserNotFoundException, OrderNotFoundException, OrderDoesNotBelongToUserException, OrderCannotBeCancelledException{
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("user not found");
        }
        User user = userOp.get();
        Optional<Order> orderOp = orderRepository.findById(orderId);
        if(orderOp.isEmpty()){
            throw new OrderNotFoundException("order not found");
        }
        Order order = orderOp.get();
        if(!user.getOrders().contains(order)){
            throw new OrderDoesNotBelongToUserException("order does not belong to user");
        }
        if(order.getOrderStatus()==OrderStatus.CANCELLED || order.getOrderStatus()==OrderStatus.SHIPPED || order.getOrderStatus()==OrderStatus.DELIVERED){
            throw new OrderCannotBeCancelledException("order cannot be cancelled");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for(OrderDetail orderDetail: orderDetails){
            Product product = orderDetail.getProduct();
            int quantity = orderDetail.getQuantity();
            Optional<Inventory> inventoryOp = inventoryRepository.findByProduct_Id(product.getId());
            if(inventoryOp.isEmpty()){
                Inventory newInventory = new Inventory();
                newInventory.setProduct(product);
                newInventory.setQuantity(quantity);
                inventoryRepository.save(newInventory);
            }
            Inventory inventory = inventoryOp.get();
            inventory.setQuantity(inventory.getQuantity()+quantity);
            inventoryRepository.save(inventory);
        }
        return order;
    }

}
