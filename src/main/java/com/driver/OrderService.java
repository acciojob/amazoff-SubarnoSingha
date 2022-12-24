package com.driver;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.saveOrder(order);
    }

    public void addPartner(String partner){
        orderRepository.savePartner(partner);
    }

    public void OrderPartnerPair(String order, String partner){
        orderRepository.OrderPartnerPair(order, partner);
    }

    public Order findOrder(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner findPartner(String partnerId) {
        return orderRepository.getPartnerById(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partner){
        return orderRepository.getOrdersByPartnerId(partner);
    }

    public List<String> findAllOrders(){
        return orderRepository.getAllOrders();
    }

    public void deletePartnerById(String partner){
        orderRepository.deletePartnerById(partner);
    }
    public void deleteOrderById(String order){
        orderRepository.deleteOrderById(order);
    }
public int getOrderCountByPartnerId(String partner)
{
    return orderRepository.getOrderCountByPartnerId(partner);
}
public int getCountOfUnassignedOrders()
{
    return orderRepository.getCountOfUnassignedOrders();
}
    public int getOrdersLeftAfterGivenTimeByPartnerId(LocalDateTime time,String partnerId){
        // Call the getOrdersLeftAfterGivenTimeByPartnerId method in the repository layer
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId( time,partnerId);
    }
    public String getlastdeliverordertime(String id)
    {
        return orderRepository.getlastdeliverordertime(id);
    }
}