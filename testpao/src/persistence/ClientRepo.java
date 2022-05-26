package persistence;

import classes.Client;

import java.util.Arrays;

public class ClientRepo implements GenericRepo<Client, String> {

    private Client[] storage = new Client[11];

    @Override
    public void add(Client entity) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = entity;
                return;
            }
        }

        Client[] newStorage = Arrays.<Client, Client>copyOf(storage, 2 * storage.length, Client[].class);

        newStorage[storage.length] = entity;
        storage = newStorage;
    }

    @Override
    public Client get(int index) {
        return storage[index];
    }

    @Override
    public void update(Client entity, String phone) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].hashCode() == entity.hashCode())
                storage[i].setPhone(phone);

    }

    @Override
    public void delete(Client entity) {

        Client[] newStorage = new Client[storage.length - 1];

        int j = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].hashCode() != entity.hashCode()) {
                newStorage[j++] = storage[i];
                //System.out.println("Din repo"+storage[i]);
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

