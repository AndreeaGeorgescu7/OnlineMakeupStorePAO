package persistence;

import classes.Delivery;

import java.util.Arrays;

public class DeliveryRepo implements GenericRepo<Delivery, String> {

    private Delivery[] storage = new Delivery[11];

    @Override
    public void add(Delivery entity) {
        // check if there's room in the storage
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = entity;
                return;
            }
        }
        // no place found. Increasing capacity
        Delivery[] newStorage = Arrays.<Delivery, Delivery>copyOf(storage, 2 * storage.length, Delivery[].class);
        // add the new entry
        newStorage[storage.length] = entity;
        storage = newStorage;
    }

    @Override
    public Delivery get(int index) {
        return storage[index];
    }

    @Override
    public void update(Delivery entity, String phone) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].hashCode() == entity.hashCode())
                storage[i].setPhoneNumber(phone);

    }

    @Override
    public void delete(Delivery entity) {

        Delivery[] newStorage = new Delivery[storage.length - 1];
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

    public Delivery getMinim() {
        int mini = 100, pozMini = 0;
        for (int i = 0; i < storage.length && storage[i] != null; i++)
            if (storage[i].getNumberOfDelivery() < mini) {
                mini = storage[i].getNumberOfDelivery();
                pozMini = i;
            }
        return get(pozMini);
    }
}

