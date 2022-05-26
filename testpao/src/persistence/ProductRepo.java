package persistence;

import classes.Product;

import java.util.Arrays;

public class ProductRepo implements GenericRepo<Product, Float> {

    private Product[] storage = new Product[11];

    @Override
    public void add(Product entity) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = entity;
                return;
            }
        }

        Product[] newStorage = Arrays.<Product, Product>copyOf(storage, 2 * storage.length, Product[].class);

        newStorage[storage.length] = entity;
        storage = newStorage;
    }

    @Override
    public Product get(int index) {
        return storage[index];
    }

    @Override
    public void update(Product entity, Float price) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] != null && storage[i].hashCode() == entity.hashCode())
                storage[i].setPrice(price);

    }

    @Override
    public void delete(Product entity) {

        Product[] newStorage = new Product[storage.length - 1];
        ;
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
    ///the actual number of elements !=null
    @Override
    public int getNumberOf() {
        int nr = 0;
        for (int i = 0; i < storage.length && storage[i] != null; i++)
            nr++;

        return nr;

    }
}

