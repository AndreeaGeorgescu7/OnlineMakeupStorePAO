package service;

import classes.*;
import exceptions.InvalidDataExc;
import persistence.*;

import java.io.IOException;
import java.time.LocalDate;


import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ProductService {

    private ProductRepo product1Repo = new ProductRepo();
    private OrderRepo order1Repo = new OrderRepo();
    private DeliveryRepo delivery1Repo = new DeliveryRepo();


    public void registerNewFoundation(String product_name, String brand, String valability, Float price, List<String> ingredients, String foundationShade, String forTypeOfSkin)
            throws InvalidDataExc {


        if (product_name == null || product_name.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid product name");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid brand name");
        }

        if (price < 0) {
            throw new InvalidDataExc("Invalid price");
        }
        if (valability == null || valability.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid valability");
        }
        Foundation entity = new Foundation(product_name, brand, valability, price, ingredients, foundationShade, forTypeOfSkin);
        product1Repo.add(entity);
    }

    public void registerNewLips(String product_name, String brand, String valability, Float price, List<String> ingredients, String shade, String type)
            throws InvalidDataExc {


        if (product_name == null || product_name.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid product name");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid brand name");
        }

        if (price < 0) {
            throw new InvalidDataExc("Invalid price");
        }
        if (valability == null || valability.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid valability");
        }
        Lips entity = new Lips(product_name, brand, valability, price, ingredients, shade, type);
        product1Repo.add(entity);
    }

    public void registerNewEyeshadow(String product_name, String brand, String valability, Float price, List<String> ingredients, List<String> colors)
            throws InvalidDataExc {


        if (product_name == null || product_name.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid product name");
        }
        if (brand == null || brand.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid brand name");
        }

        if (price < 0) {
            throw new InvalidDataExc("Invalid price");
        }
        if (valability == null || valability.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid valability");
        }
        Eyeshadow entity = new Eyeshadow(product_name, brand, valability, price, ingredients, colors);
        product1Repo.add(entity);
    }

    public Product[] getAllProducts() {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < product1Repo.getSize(); i++) {
            if (product1Repo.get(i) != null) {
                result.add(product1Repo.get(i));
            }
        }
        return result.toArray(new Product[0]);
    }

    public void removeAProduct(String product_name, String brand) throws InvalidDataExc {
        boolean ok = true;
        int i;

        for (i = 0; i < product1Repo.getNumberOf() && ok; i++) {
            if (Objects.equals(product1Repo.get(i).getProduct_name(), product_name) && Objects.equals(product1Repo.get(i).getBrand(), brand))
                ok = false;

        }
        if (ok)
            throw new InvalidDataExc("Invalid Product");

        else
            product1Repo.delete(product1Repo.get(i - 1));
    }

    public Product[] getAllProductsContainingThisWord(String word) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < product1Repo.getSize(); i++) {
            if (product1Repo.get(i) != null && product1Repo.get(i).includeWord(word)) {
                result.add(product1Repo.get(i));
            }
        }
        return result.toArray(new Product[0]);
    }

    public Product[] getAllProductsBio() {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < product1Repo.getSize(); i++) {
            if (product1Repo.get(i) != null && product1Repo.get(i).isBIO()) {
                result.add(product1Repo.get(i));
            }
        }
        return result.toArray(new Product[0]);
    }

    public void registerNewOrder(String address, String cardDetails, String postalCode, List<Eyeshadow> eyes, List<Lips> lip, List<Foundation> found)
            throws InvalidDataExc {


        if (address == null || address.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid address");
        }
        if (cardDetails == null || cardDetails.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid card details");
        }

        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid postal code");
        }
        LocalDate myObj = LocalDate.now();
        Order entity = new Order(address, cardDetails, 50, String.format("%s", myObj), postalCode, 7, eyes, lip, found);
        order1Repo.add(entity);
    }

    public Order[] getAllOrders() {
        List<Order> result = new ArrayList<>();
        for (int i = 0; i < order1Repo.getSize(); i++) {
            if (order1Repo.get(i) != null) {
                result.add(order1Repo.get(i));
            }
        }
        return result.toArray(new Order[0]);
    }

    public void registerNewDeliveryMan(String companyName, String delivaryMan, String phoneNumber, int numberOfDelivery, String typeOfDelivery)
            throws InvalidDataExc {


        if (companyName == null || companyName.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid company");
        }
        if (delivaryMan == null || delivaryMan.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid name");
        }
        if (numberOfDelivery < 0) {
            throw new InvalidDataExc("Invalid number");
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid phone number");
        }
        Delivery entity = new Delivery(companyName, delivaryMan, phoneNumber, numberOfDelivery, typeOfDelivery);
        delivery1Repo.add(entity);
    }

    public Delivery[] getAllDeliveryMen() {
        List<Delivery> result = new ArrayList<>();
        for (int i = 0; i < delivery1Repo.getSize(); i++) {
            if (delivery1Repo.get(i) != null) {
                result.add(delivery1Repo.get(i));
            }
        }
        return result.toArray(new Delivery[0]);
    }

    public void removeADeliveryMan(String phoneNumber, String deliveryMan) throws InvalidDataExc {
        boolean ok = true;
        int i;

        for (i = 0; i < delivery1Repo.getNumberOf() && ok; i++) {
            if (Objects.equals(delivery1Repo.get(i).getPhoneNumber(), phoneNumber) && Objects.equals(delivery1Repo.get(i).getDeliveryMan(), deliveryMan))
                ok = false;

        }
        if (ok)
            throw new InvalidDataExc("Invalid Delivary Man");

        else
            delivery1Repo.delete(delivery1Repo.get(i - 1));
    }

    public Delivery returnMinimum() {
        return delivery1Repo.getMinim();
    }

    public void readLips(){
        try {
            List<Lips> l=new ArrayList<>();

             l = ReaderWriterIO.readLips("LipsCSV.csv");
            for (Lips c : l) {
               product1Repo.add(c);
            }
            Audit.writeAuditCSV("Read liptick from CSV read file");
        } catch ( IOException | InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void addLips(Lips c) throws IOException {
        //product1Repo.add(c);

        try {

             //ReaderWriterIO.writeLips("LipsCSV.csv", c);
            ReaderWriterIO.writeProduct(c,c.getClass(),"C:\\Users\\Andreea\\IdeaProjects\\testpao\\LipsCSV.csv");

            Audit.writeAuditCSV("Write lipstick to CSV");
        } catch (InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public void readFoundation(){
        try {
            List<Foundation> l=new ArrayList<>();

            l = ReaderWriterIO.readFoundation("FoundationCSV.csv");
            for (Foundation c : l) {
                product1Repo.add(c);
            }
            Audit.writeAuditCSV("Read foundation from CSV read file");
        } catch ( IOException | InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void addFoundation(Foundation c) throws IOException {
        //product1Repo.add(c);

        try {

            //ReaderWriterIO.writeFoundation("FoundationCSV.csv", c);
            ReaderWriterIO.writeProduct(c,c.getClass(),"C:\\Users\\Andreea\\IdeaProjects\\testpao\\FoundationCSV.csv");
            Audit.writeAuditCSV("Write foundation to CSV");
        } catch (InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public void readEyeshadow(){
        try {
            List<Eyeshadow> l=new ArrayList<>();

            l = ReaderWriterIO.readEyeshadow("EyeshadowCSV.csv");
            for (Eyeshadow c : l) {
                product1Repo.add(c);
            }
            Audit.writeAuditCSV("Read eyeshadow from CSV read file");
        } catch ( IOException | InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void addEyeshadow(Eyeshadow c) throws IOException {
        //product1Repo.add(c);

        try {

            //ReaderWriterIO.writeEyeshadow("EyeshadowCSV.csv", c);
            ReaderWriterIO.writeProduct(c,c.getClass(),"C:\\Users\\Andreea\\IdeaProjects\\testpao\\EyeshadowCSV.csv");
            Audit.writeAuditCSV("Write Eyeshadow to CSV");
        } catch (InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }



}

