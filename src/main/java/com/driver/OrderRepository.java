package com.driver;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository

public class OrderRepository {


    private Map<String, Order> ordersMap;
    private  Map<String, DeliveryPartner> partnersMap;
    private HashMap<String, List<String>> orderPartnerMap;
    Set<String>assigned=new HashSet<>();

    public OrderRepository(){
        this.ordersMap = new HashMap<String, Order>();
        this.partnersMap = new HashMap<String, DeliveryPartner>();
        this.orderPartnerMap = new HashMap<String, List<String>>();
    }
    public void saveOrder(Order order){
        ordersMap.put(order.getId(), order);
    }

    public void savePartner(String partnerId){
        // Create a new Partner object using the partnerId parameter
        DeliveryPartner partner = new DeliveryPartner(partnerId);

        // Save the Partner object in the partnersMap map using the partnerId as the key
        partnersMap.put(partnerId, partner);
    }

    public void OrderPartnerPair(String order, String partner){
        if(ordersMap.containsKey(order) && partnersMap.containsKey(partner)){
            ordersMap.put(order, ordersMap.get(order));
            partnersMap.put(partner, partnersMap.get(partner));
            List<String> curr = new ArrayList<String>();
            if(orderPartnerMap.containsKey(partner))
                curr = orderPartnerMap.get(partner);
            curr.add(order);
            orderPartnerMap.put(partner, curr);
        }
    }

    public Order getOrderById(String order){
        return ordersMap.get(order);
    }

    public DeliveryPartner  getPartnerById(String partner){
        return partnersMap.get(partner);
    }
    public int getOrderCountByPartnerId(String partner){
        List<String> orderList = new ArrayList<String>();
        if(orderPartnerMap.containsKey(partner))
            orderList = orderPartnerMap.get(partner);

        return orderList.size();
    }

    public List<String> getOrdersByPartnerId(String partner){
        List<String> orderList = new ArrayList<String>();
        if(orderPartnerMap.containsKey(partner))
            orderList = orderPartnerMap.get(partner);

        return orderList;
    }

    public List<String> getAllOrders(){
        return new ArrayList<>(ordersMap.keySet());
    }
    public void deletePartnerById(String id) {
        partnersMap.remove(id);
        orderPartnerMap.remove(id);
    }
    public void deleteOrderById(String orderId) {
        ordersMap.remove(orderId);
        if (assigned.contains(orderId)) {
            for (String a : orderPartnerMap.keySet()) {
                List<String> ll = orderPartnerMap.get(a);
                for (int i = 0; i < ll.size(); i++) {
                    if (ll.get(i).equals(orderId)) {
                        ll.remove(i);
                        orderPartnerMap.put(a, ll);
                        return;
                    }
                }
            }
        }
    }

    public int getCountOfUnassignedOrders(){
        int unassignedOrderCount = 0;
        List<Order> orders = null;

        // Iterate through the orders in the system
        for(Order order : orders){
            // If the order does not have a partner assigned, increment the unassigned order count
            if(order.getId() == null){
                unassignedOrderCount++;
            }
        }

        return unassignedOrderCount;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId( LocalDateTime time,String partnerId){
        int ordersLeft = 0;

        // Retrieve the list of orders from the orderMap map
        List<Order> orders = retrieveOrdersFromMap();

        // Iterate through the orders in the system
        for(Order order : orders){
            // Check if the order is assigned to the given partner and has a delivery time after the given time
            if(order.getPartner().getId().equals(partnerId) ){
                ordersLeft++;
            }
        }

        return ordersLeft;
    }

    private List<Order> retrieveOrdersFromMap(){
        // Get a collection of the values in the orderMap map
        Collection<Order> orderValues = ordersMap.values();

        // Create a new list to hold the orders
        List<Order> orders = new ArrayList<>();

        // Add the values from the collection to the list
        orders.addAll(orderValues);

        return orders;
    }
    public String getlastdeliverordertime(String id)
    {
        List<String>order=orderPartnerMap.get(id);
        int last=0;
        for(String s:order) {
            Order o=ordersMap.get(s);
            if(((Order) o).getDeliveryTime()>last) {
                last=o.getDeliveryTime();
            }
        }
        int hour=last/60;
        last%=60;
        String time=hour+" : "+last;
        return time;
    }










}
