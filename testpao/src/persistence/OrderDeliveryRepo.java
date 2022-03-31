package persistence;

import classes.Order;

import classes.Delivery;

import java.util.Arrays;

import java.util.Map;

import java.util.HashMap;

public class OrderDeliveryRepo {


    Map<Order, Delivery> inChargeOfOrder = new HashMap<>();

    public void add(Order ord, Delivery dev) {
        inChargeOfOrder.put(ord, dev);

    }


    public Delivery get(Order ord) {
        return inChargeOfOrder.get(ord);
    }


    public void update(Order ord, Delivery dev) {

        inChargeOfOrder.replace(ord, dev);

    }


    public void delete(Order ord, Delivery dev) {

        inChargeOfOrder.remove(ord, dev);
    }

    public int getSize() {
        return inChargeOfOrder.size();
    }

    public void listALLOrdDEv() {
        for (Order key : inChargeOfOrder.keySet())
             System.out.println(inChargeOfOrder.get(key).getDeliveryMan() + " : " + key.getLip() + key.getEyes() + key.getFound());
    }


}
