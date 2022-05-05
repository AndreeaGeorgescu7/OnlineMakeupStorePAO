import classes.*;
import persistence.*;
import service.*;
import exceptions.InvalidDataExc;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    static private ClientService service = new ClientService();
    static private ProductService serviceP = new ProductService();
    static private OrderDeliveryRepo ordDev1Repo = new OrderDeliveryRepo();
    static String emailRegister;
    static Integer status;
    static boolean ok;


    private static Scanner s = new Scanner(System.in);

    public static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(100));

    }

    public static void main(String[] args) throws IOException, InvalidDataExc {//psvm



        Main app = new Main();


        service.readClient();
        serviceP.readLips();
        serviceP.readEyeshadow();
        serviceP.readFoundation();

        /*adding some customers*/
        List<String> in_cart = new ArrayList<String>();
        List<Float> oldP = new ArrayList<Float>();
        Order o1 = new Order();
        service.registerNewClient("Georgescu", "Andreea", 'F', 20, in_cart, "andra@gmail.com", "0732519096", true, oldP, 3, o1);
        List<String> in_cart2 = new ArrayList<String>();
        List<Float> oldP2 = new ArrayList<Float>();
        Order o2 = new Order();
        service.registerNewClient("Marin", "Cristian", 'M', 25, in_cart2, "cristi@gmail.com", "0732519094", true, oldP2, 0, o2);

        /*adding some products, one of each kind*/
        List<String> ingr1 = new ArrayList<String>();
        ingr1.add("acid");
        ingr1.add("mere Bio");
        serviceP.registerNewLips("ruj", "dior", "21/10/2023", 150f, ingr1, "red", "matte");

        List<String> ingr2 = new ArrayList<String>();
        ingr2.add("acid");
        ingr2.add("crema de vanilie");
        List<String> colors = new ArrayList<String>();
        colors.add("albastru");
        colors.add("roz");
        colors.add("galben");
        colors.add("maro");
        serviceP.registerNewEyeshadow("amor", "anasthasia", "21/10/2050", 350f, ingr2, colors);

        List<String> ingr3 = new ArrayList<String>();
        ingr3.add("pudra de orez BIO");
        ingr3.add("pigment mat");
        ingr3.add("ulei de jojoba");
        serviceP.registerNewFoundation("belle", "benefit", "12/12/2023", 200f, ingr3, "C4", "dry skin");


        /*adding some delivery men*/
        serviceP.registerNewDeliveryMan("Cargus", "Matei Calin", "0741586752", 0, "car");
        serviceP.registerNewDeliveryMan("FanCourier", "Bogdan Andrei", "0733586345", 3, "bike");

        while(true) {
            System.out.println("Schimba cont-0/ Iesire-1");
            Integer com = Integer.parseInt(s.nextLine());
            if (com == 0) {
                System.out.println("Administrator-0 / Client-1");
                status = Integer.parseInt(s.nextLine());
                ok = true;

                if (status == 0) {
                    System.out.print("Password: ");
                    String password = s.nextLine();
                    if (password.equals("123"))
                        while (ok) {

                            app.showMenu();
                            int option = app.readOption();
                            clearScreen();
                            app.execute(option);

                        }
                    else System.out.println("Worong password.Try again!");
                } else {
                    System.out.print("Email to log in: ");
                    emailRegister = s.nextLine();
                    if (emailRegister.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") && service.validateEmail(emailRegister))

                        while (ok) {

                            app.showMenu();
                            int option = app.readOption();
                            clearScreen();
                            app.execute(option);

                        }
                    else System.out.println("Invalid email");
                }
            }
            else     System.exit(0);
        }
    }

    private void showMenu() {

        if (status == 0) {
            System.out.println("1. add client");
            System.out.println("2. list all clients");
            System.out.println("3. exit");
            System.out.println("4. remove a client");
            System.out.println("5. add employee");
            System.out.println("6. add product");
            System.out.println("7. remove product");
            System.out.println("8. list all products");
            System.out.println("9. find a product or more( by name )");
            System.out.println("10.list all products BIO");
            System.out.println("12.list all orders");
            System.out.println("13.list all clients and their orders");
            System.out.println("17. add delivery man");
            System.out.println("18. list all delivery team");
            System.out.println("19. remove a delivery man");
            System.out.println("20. Who is in charge of orders?");
        } else {

            System.out.println("3. exit");
            System.out.println("8. list all products");
            System.out.println("9. find a product or more( by name )");
            System.out.println("10.list all products BIO");
            System.out.println("11.place an order");
            System.out.println("14.see delivery detail");
            System.out.println("16.finish the order");

        }


    }

    private int readOption() {

        System.out.println("Enter your option: ");
        try {
            int option = readInt();
            if (option >= 1 && option <= 20) {
                return option;
            }
        } catch (InvalidDataExc invalid) {

        }
        System.out.println("Invalid option. Try again");

        return readOption();
    }

    private int readInt() throws InvalidDataExc {
        String line = s.nextLine();
        if (line.matches("^\\d+$")) {

            return Integer.parseInt(line);
        } else {
            throw new InvalidDataExc("Invalid number");
        }
    }

    private void execute(int option) {
        switch (option) {
            case 1:
                addClient();
                break;
            case 2:

                listAllClients();
                break;
            case 3:
                ok=false;
                break;

            case 4:
                System.out.print("Email: ");
                String email = s.nextLine();
                deleteClient(email);
                break;
            case 5:

                addEmployee();
                break;
            case 6:
                addProd();
                break;
            case 7:
                System.out.print("Product Name: ");
                String name = s.nextLine();
                System.out.print("Brand Name: ");
                String brand = s.nextLine();
                deleteProd(name, brand);
                break;
            case 8:
                listAllProds();
                break;
            case 9:
                listAllProdsContainingThisWord();
                break;
            case 10:
                listAllProdsBIO();
                break;
            case 11:
                addOrder();
                break;
            case 12:
                listAllOrders();
                break;
            case 13:
                listAllClientsAndTheirOrders();
                break;
            case 14:
                seeDeliveryDetails();
                break;
            case 15:
                break;
            case 16:
                finishOrder();
                break;
            case 17:
                addDeliveryMan();
                break;
            case 18:
                listAllDeliveryMen();
                break;
            case 19:
                System.out.print("Phone number: ");
                String phoneNumber = s.nextLine();
                System.out.print("Name: ");
                String deliveryMan = s.nextLine();
                deleteDeliveryMan(phoneNumber, deliveryMan);
                break;
            case 20:
                listOrDevREPO();
                break;


        }
    }

    private void addClient() {
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Surname: ");
        String surname = s.nextLine();
        System.out.print("Gender: ");
        String gender = s.nextLine();
        System.out.print("Age: ");
        String age = s.nextLine();
        System.out.print("Nr of products in cart: ");
        String nr = s.nextLine();
        List<String> in_cart = new ArrayList<String>();
        for (int i = 1; i <= Integer.parseInt(nr); i++) {
            String product = s.nextLine();
            in_cart.add(product);
        }
        System.out.print("Email: ");
        String email = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();
        boolean over18;
        if (Integer.parseInt(age) >= 18)
            over18 = true;
        else over18 = false;
        System.out.print("Nr of total placed orders and the cost of each one/ 0 : ");
        String nr1 = s.nextLine();
        List<Float> oldPayments = new ArrayList<Float>();
        for (int i = 1; i <= Integer.parseInt(nr1); i++) {
            String pay = s.nextLine();
            oldPayments.add(Float.parseFloat(pay));
        }
        System.out.print("For how long have you been our costumer( in years ): ");
        String yearsOfFidelity = s.nextLine();
        Order ord = new Order();


        if (age.matches("^\\d+$") && phone.matches("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$") && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {

            try {
                service.registerNewClient(name, surname, gender.charAt(0), Integer.parseInt(age), in_cart, email, phone, over18, oldPayments, Integer.parseInt(yearsOfFidelity), ord);
                Client l2=new Client(name, surname, gender.charAt(0), Integer.parseInt(age), in_cart, email, phone, over18, oldPayments, Integer.parseInt(yearsOfFidelity), ord);
                service.addClient(l2);
            } catch (InvalidDataExc invalidData) {
                System.out.println(invalidData.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid age or phone or email!");
        }
    }

    private void addEmployee() {
        System.out.print("Name: ");
        String name = s.nextLine();
        System.out.print("Surname: ");
        String surname = s.nextLine();
        System.out.print("Gender: ");
        String gender = s.nextLine();
        System.out.print("Age: ");
        String age = s.nextLine();
        System.out.print("Nr of products: ");
        String nr = s.nextLine();
        List<String> in_cart = new ArrayList<String>();
        for (int i = 1; i <= Integer.parseInt(nr); i++) {
            String product = s.nextLine();
            in_cart.add(product);
        }
        System.out.print("Email: ");
        String email = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();
        boolean over18;
        if (Integer.parseInt(age) >= 18)
            over18 = true;
        else over18 = false;
        System.out.print("Nr of total placed orders: ");
        String nr1 = s.nextLine();
        List<Float> oldPayments = new ArrayList<Float>();
        for (int i = 1; i <= Integer.parseInt(nr1); i++) {
            String pay = s.nextLine();
            oldPayments.add(Float.parseFloat(pay));
        }
        System.out.print("For how long have you been our costumer( in years ): ");
        String yearsOfFidelity = s.nextLine();
        Order ord = new Order();
        System.out.print("Job: ");
        String job = s.nextLine();


        if (age.matches("^\\d+$") && phone.matches("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$") && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            try {
                service.registerNewEmployee(name, surname, gender.charAt(0), Integer.parseInt(age), in_cart, email, phone, over18, oldPayments, Integer.parseInt(yearsOfFidelity), ord, job);

            } catch (InvalidDataExc invalidData) {
                System.out.println(invalidData.getMessage());
            }
        } else {
            System.out.println("Invalid age or phone or email!");
        }
    }

    private void listAllClients() {

        System.out.println("Clients:\n---------------------");
        for (Client e : service.getAllClients()) {
            System.out.println(e.getName() + "  " + e.getSurname() + " " + e.getEmail() + " (" + e.getAge() + " years)"+e.getYearsOfFidelity());
        }

    }

    private void deleteClient(String email) {
        if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))

            try {
                service.removeACLient(email);

            } catch (InvalidDataExc invalidData) {
                System.out.println(invalidData.getMessage());
            }
        else {
            System.out.println("Invalid email!");
        }

    }

    private void addProd() {
        System.out.print("Lipstick-1/Eyshadow-2/Foundation-3: ");
        int type_of_product = Integer.parseInt(s.nextLine());
        System.out.print("Product Name: ");
        String product_name = s.nextLine();
        System.out.print("Brand: ");
        String brand = s.nextLine();
        System.out.print("Valability (dd//mm/yyyy): ");
        String valability = s.nextLine();
        System.out.print("Nr of ingredients: ");
        String nr = s.nextLine();
        List<String> ingredients = new ArrayList<String>();
        for (int i = 1; i <= Integer.parseInt(nr); i++) {
            String product = s.nextLine();
            ingredients.add(product);
        }
        System.out.print("Price: ");
        String price = s.nextLine();
        if (valability.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")) {
            if (type_of_product == 1) {
                System.out.print("Shade: ");
                String shade = s.nextLine();
                System.out.print("Type: ");
                String type = s.nextLine();

                try {
                    serviceP.registerNewLips(product_name, brand, valability, Float.parseFloat(price), ingredients, shade, type);
                   Lips l2=new Lips(product_name, brand, valability, Float.parseFloat(price), ingredients, shade, type);
                    serviceP.addLips(l2);
                } catch (InvalidDataExc invalidData) {
                    System.out.println(invalidData.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (type_of_product == 2) {
                System.out.print("Nr of colors: ");
                String nr1 = s.nextLine();
                List<String> colors = new ArrayList<String>();
                for (int i = 1; i <= Integer.parseInt(nr1); i++) {
                    String col = s.nextLine();
                    colors.add(col);
                }
                try {
                    serviceP.registerNewEyeshadow(product_name, brand, valability, Float.parseFloat(price), ingredients, colors);
                    Eyeshadow l3=new Eyeshadow(product_name, brand, valability, Float.parseFloat(price), ingredients, colors);
                    serviceP.addEyeshadow(l3);
                } catch (InvalidDataExc | IOException invalidData) {
                    System.out.println(invalidData.getMessage());
                }
            } else {
                System.out.print("Foundation Shade: ");
                String foundaionShade = s.nextLine();
                System.out.print("For which Type of skin: ");
                String forTypeOfSKin = s.nextLine();
                try {
                    serviceP.registerNewFoundation(product_name, brand, valability, Float.parseFloat(price), ingredients, foundaionShade, forTypeOfSKin);
                    Foundation l2=new Foundation(product_name, brand, valability, Float.parseFloat(price), ingredients, foundaionShade, forTypeOfSKin);
                    serviceP.addFoundation(l2);
                } catch (InvalidDataExc | IOException invalidData) {
                    System.out.println(invalidData.getMessage());
                }
            }

        } else System.out.println("Invalid date for valability!");


    }

    private void listAllProds() {

        System.out.println("Products:\n---------------------");
        for (Product e : serviceP.getAllProducts()) {
            System.out.println(e.getProduct_name() + "  " + e.getBrand() + " " + e.getPrice() + " lei");



        }

    }

    private void deleteProd(String product_name, String brand) {

        try {
            serviceP.removeAProduct(product_name, brand);

        } catch (InvalidDataExc invalidData) {
            System.out.println(invalidData.getMessage());
        }

    }

    private void listAllProdsContainingThisWord() {
        System.out.print("Name : ");
        String word = s.nextLine();
        System.out.println("Products:\n---------------------");
        for (Product e : serviceP.getAllProductsContainingThisWord(word)) {
            System.out.println(e.getProduct_name() + "  " + e.getBrand() + " " + e.getPrice() + " lei");
        }

    }

    private void listAllProdsBIO() {

        System.out.println("Products BIO:\n---------------------");
        for (Product e : serviceP.getAllProductsBio()) {
            System.out.println(e.getProduct_name() + "  " + e.getBrand() + " " + e.getPrice() + " lei");
        }

    }

    private void addOrder() {

        System.out.print("Address: ");
        String address = s.nextLine();
        System.out.print(" Card details (VISA/MATER CARD): ");
        String cardDetails = s.nextLine();
        System.out.print("Postal code: ");
        String postalCode = s.nextLine();
        boolean k = true;
        System.out.print("Nr of eyeshadows: ");
        Integer nr = Integer.parseInt(s.nextLine());
        List<Eyeshadow> eyes = new ArrayList<Eyeshadow>();
        for (int i = 1; i <= nr; i++) {
            System.out.print("Name: ");
            String name = s.nextLine();
            System.out.print(" Brand: ");
            String brand = s.nextLine();
            for (Product e : serviceP.getAllProducts())
                if (e.getBrand().equals(brand) && e.getProduct_name().equals(name)) {
                    eyes.add((Eyeshadow) e);
                    k = false;
                }
            if (k == true) {
                System.out.println("Invalid item");
                i--;
            }

        }

        boolean k2 = true;
        System.out.print("Nr of lipsticks: ");
        Integer nr1 = Integer.parseInt(s.nextLine());
        List<Lips> lips = new ArrayList<Lips>();
        for (int i = 1; i <= nr1; i++) {
            System.out.print("Name: ");
            String name = s.nextLine();
            System.out.print(" Brand: ");
            String brand = s.nextLine();
            for (Product e : serviceP.getAllProducts())
                if (e.getBrand().equals(brand) && e.getProduct_name().equals(name)) {
                    lips.add((Lips) e);
                    k2 = false;
                }
            if (k2 == true) {
                System.out.println("Invalid item");
                i--;
            }
        }

        boolean k3 = true;
        System.out.print("Nr of foundations: ");
        Integer nr2 = Integer.parseInt(s.nextLine());
        List<Foundation> found = new ArrayList<Foundation>();
        for (int i = 1; i <= nr2; i++) {
            System.out.print("Name: ");
            String name = s.nextLine();
            System.out.print(" Brand: ");
            String brand = s.nextLine();
            for (Product e : serviceP.getAllProducts())
                if (e.getBrand().equals(brand) && e.getProduct_name().equals(name)) {
                    found.add((Foundation) e);
                    k3 = false;
                }
            if (k3 == true) {
                System.out.println("Invalid item");
                i--;
            }

        }
        /* example for postal code: 50025*/
        /*example for cardDetails: 411111111111111*/
        if ((cardDetails.matches("^4[0-9]{12}(?:[0-9]{3})?$") || cardDetails.matches("^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$")) && postalCode.matches("^\\d{6}$")) {


            try {
                serviceP.registerNewOrder(address, cardDetails, postalCode, eyes, lips, found);

            } catch (InvalidDataExc invalidData) {
                System.out.println(invalidData.getMessage());
            }

            for (Client e : service.getAllClients())
                if (emailRegister.equals(e.getEmail())) {
                    Order ord = new Order(address, cardDetails, postalCode, eyes, lips, found);
                    e.setOrd(ord);
                }


        } else System.out.println("Invalid details or postal code!");


    }

    private void listAllOrders() {

        System.out.println("Orders:\n---------------------");
        for (Order e : serviceP.getAllOrders()) {
            System.out.println(e.getEyes() + "  " + e.getLip() + " " + e.getFound());
        }

    }

    private void listAllClientsAndTheirOrders() {

        System.out.println("Clients:\n---------------------");
        for (Client e : service.getAllClients()) {
            if (e.getOrd() != null)
                System.out.println(e.getName() + "  " + e.getSurname() + " " + e.getEmail() + " (" + e.getAge() + " years)" + e.getOrd().getFound() + e.getOrd().getEyes() + e.getOrd().getLip());
        }

    }

    private void finishOrder() {

        System.out.println("Coupon code:\n---------------------");
        String coup = s.nextLine();
        for (Client e : service.getAllClients()) {
            if (e.getEmail().equals(emailRegister)) {
                System.out.println(e.getOrd().totalAmount());
                e.getOrd().transportFreeAlert();
                e.getOrd().addACoupon(coup);
                ordDev1Repo.add(e.getOrd(), serviceP.returnMinimum());
                serviceP.returnMinimum().setNumberOfDelivery(  serviceP.returnMinimum().getNumberOfDelivery()+1);
            }
        }


    }

    private void addDeliveryMan() {
        System.out.print("Company Name: ");
        String companyName = s.nextLine();
        System.out.print("Name : ");
        String delivaryMan = s.nextLine();
        System.out.print("Phone: ");
        String phone = s.nextLine();
        System.out.print("Nr of delivery: ");
        Integer numberOfDelivery = Integer.parseInt(s.nextLine());
        System.out.print("Type of delivery (car/ bike): ");
        String typeOfDelivery = s.nextLine();


        if (phone.matches("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$")) {

            try {
                serviceP.registerNewDeliveryMan(companyName, delivaryMan, phone, numberOfDelivery, typeOfDelivery);

            } catch (InvalidDataExc invalidData) {
                System.out.println(invalidData.getMessage());
            }
        } else {
            System.out.println("Invalid phone number!");
        }
    }

    private void deleteDeliveryMan(String phoneNumber, String deliveryMan) {

        try {
            serviceP.removeADeliveryMan(phoneNumber, deliveryMan);

        } catch (InvalidDataExc invalidData) {
            System.out.println(invalidData.getMessage());
        }

    }

    private void listAllDeliveryMen() {

        System.out.println("Delivery team:\n---------------------");
        for (Delivery e : serviceP.getAllDeliveryMen()) {
            System.out.println(e.getCompanyName() + "  " + e.getDeliveryMan() + " " + e.getPhoneNumber());
        }

    }

    private void listOrDevREPO() {
        ordDev1Repo.listALLOrdDEv();

    }

    private void seeDeliveryDetails() {
        for (Client e : service.getAllClients())
            if (e.getEmail().equals(emailRegister))
                try {
                    System.out.println(ordDev1Repo.get(e.getOrd()).getCompanyName() + " " + ordDev1Repo.get(e.getOrd()).getDeliveryMan() + " " + ordDev1Repo.get(e.getOrd()).getPhoneNumber());
                }
                catch(NullPointerException e2) {
                    System.out.println("NullPointerException!");
                }
    }



}

