package persistence;

import classes.Order;

import java.util.Arrays;

public class OrderRepo implements GenericRepo<Order, Integer> {

    private Order[] storage = new Order[11];

    @Override
    public void add(Order entity) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = entity;
                return;
            }
        }

        Order[] newStorage = Arrays.<Order, Order>copyOf(storage, 2 * storage.length, Order[].class);

        newStorage[storage.length] = entity;
        storage = newStorage;
    }

    @Override
    public Order get(int index) {
        return storage[index];
    }

    @Override
    public void update(Order entity, Integer days) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].hashCode() == entity.hashCode())
                storage[i].setEstimatedDaysDelivery(days);

    }


    @Override
    public void delete(Order entity) {

        Order[] newStorage = new Order[storage.length - 1];
        ;
        int j = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].hashCode() != entity.hashCode()) {
                newStorage[j++] = storage[i];

            }
        }

        storage = newStorage;
    }

    @Override
    public int getSize() {
        return storage.length;
    }

    @Override
    public int getNumberOf() {
        int nr = 0;
        for (int i = 0; i < storage.length && storage[i] != null; i++)
            nr++;

        return nr;

    }
}

