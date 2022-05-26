package service;

import classes.*;
import exceptions.InvalidDataExc;
import persistence.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class ProductService {

    private ProductRepo product1Repo = new ProductRepo();
    private OrderRepo order1Repo = new OrderRepo();
    private DeliveryRepo delivery1Repo = new DeliveryRepo();
    DBConnection dbConnection;

    public ProductService(DBConnection connection) {
        dbConnection = connection;
    }
    public void loadFromDB() {
        try (Statement statement = dbConnection.getConnection().createStatement()) {

            String query = "select * from lips";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                Lips product = new Lips(rs.getString("product_name"), rs.getString("brand"),  rs.getString("valability"), rs.getFloat("price"), rs.getString("shade"), rs.getString("type"));

                product1Repo.add(product);
            }
            query = "select * from eyeshadow";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                List<String> colors=new ArrayList<>();
                colors.add(rs.getString("color"));
              Eyeshadow product = new Eyeshadow(rs.getString("product_name"), rs.getString("brand"),  rs.getString("valability"), rs.getFloat("price"), colors);

                product1Repo.add(product);
            }
            query = "select * from foundation";
            rs = statement.executeQuery(query);

            while(rs.next()) {

                Foundation product = new Foundation(rs.getString("product_name"), rs.getString("brand"),  rs.getString("valability"), rs.getFloat("price"),rs.getString("shade"), rs.getString("type"));

                product1Repo.add(product);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerNewFoundation(String product_name, String brand, String valability, Float price, List<String> ingredients, String foundationShade, String forTypeOfSkin)
            throws InvalidDataExc, IOException {


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
        try {
            String query = "insert into foundation values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, product_name);
            preparedStatement.setString(2,brand);
            preparedStatement.setString(3, valability);
            preparedStatement.setFloat(4, price);
            preparedStatement.setString(5, foundationShade);
            preparedStatement.setString(6, forTypeOfSkin);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Audit.writeAuditCSV("addFoundation");
    }

    public void registerNewLips(String product_name, String brand, String valability, Float price, List<String> ingredients, String shade, String type)
            throws InvalidDataExc, IOException {


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
        try {
            String query = "insert into lips values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, product_name);
            preparedStatement.setString(2,brand);
            preparedStatement.setString(3, valability);
            preparedStatement.setFloat(4, price);
            preparedStatement.setString(5, shade);
            preparedStatement.setString(6, type);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Audit.writeAuditCSV("addLips");


    }

    public void registerNewEyeshadow(String product_name, String brand, String valability, Float price, List<String> ingredients, List<String> colors)
            throws InvalidDataExc, IOException {


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
        try {
            String query = "insert into eyeshadow values(?,?,?,?,?)";
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, product_name);
            preparedStatement.setString(2,brand);
            preparedStatement.setString(3, valability);
            preparedStatement.setFloat(4, price);
            preparedStatement.setString(5, colors.get(0));


            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        Audit.writeAuditCSV("addEyeshadow");
    }

    public Product[] getAllProducts() {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < product1Repo.getSize(); i++) {
            if (product1Repo.get(i) != null) {
                result.add(product1Repo.get(i));
            }
        }
                              //empty array
        return result.toArray(new Product[0]);
    }

    public void removeAProduct(String product_name, String brand) throws InvalidDataExc {
        boolean ok = true;
        int i;

        for (i = 0; i < product1Repo.getNumberOf() && ok; i++) {
            if (Objects.equals(product1Repo.get(i).getProduct_name(), product_name) && Objects.equals(product1Repo.get(i).getBrand(), brand))
                ok = false;

        }
        Class<? extends Product> clasa=product1Repo.get(i - 1).getClass();
        if (ok)
            throw new InvalidDataExc("Invalid Product");

        else
        {
            product1Repo.delete(product1Repo.get(i - 1));
          }


        if(clasa.getName().equals("classes.Lips"))
        try (Statement statement = dbConnection.getConnection().createStatement()) {
            String query = "delete from lips where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        else
            if(clasa.getName().equals("classes.Foundation"))
            try (Statement statement = dbConnection.getConnection().createStatement()) {
                String query = "delete from foundation where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            else
                try (Statement statement = dbConnection.getConnection().createStatement()) {
                    String query = "delete from eyeshadow where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    }
    public void updatePrice(String product_name,String brand,Float price) throws InvalidDataExc {

        boolean ok = true;
        int i;

        for (i = 0; i < product1Repo.getNumberOf() && ok; i++) {
            if (Objects.equals(product1Repo.get(i).getProduct_name(), product_name) && Objects.equals(product1Repo.get(i).getBrand(), brand))
                ok = false;

        }
        if (ok)
            throw new InvalidDataExc("Invalid Product");

        else
            product1Repo.get(i - 1).setPrice(price);
        Class<? extends Product> clasa=product1Repo.get(i - 1).getClass();
        System.out.println("clasa obiect: "+clasa.getName());
        if(clasa.getName().equals("classes.Lips"))
            try (Statement statement = dbConnection.getConnection().createStatement()) {
                String query = "update lips set price= \""+price+"\"where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        else
        if(clasa.getName().equals("classes.Foundation"))
            try (Statement statement = dbConnection.getConnection().createStatement()) {
                String query = "update foundation set price= \""+price+"\" where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        else
            try (Statement statement = dbConnection.getConnection().createStatement()) {
                String query = "update eyeshadow set price=\""+price+"\"where product_name=\""+product_name+"\" and brand=\""+brand+"\"";
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }


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
        ///current date for the dateOfOrder attribute
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

