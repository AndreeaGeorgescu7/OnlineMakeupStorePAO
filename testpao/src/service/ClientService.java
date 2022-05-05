package service;

import classes.Client;
import classes.Delivery;
import classes.*;
import classes.Order;
import persistence.ClientRepo;
import exceptions.InvalidDataExc;
import persistence.DeliveryRepo;


import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ClientService {
    private ClientRepo client1Repo = new ClientRepo();

    public void registerNewClient(String name, String surname, char gender, int age, List<String> in_cart, String email, String phone, boolean over18, List<Float> oldPayments, int yearsOfFidelity, Order ord)
            throws InvalidDataExc {


        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid name");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid surname");
        }
        if (gender == ' ') {
            throw new InvalidDataExc("Invalid gender");
        }
        if (age <= 0) {
            throw new InvalidDataExc("Invalid age");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid email");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid phone number");
        }
        Client entity = new Client(name, surname, gender, age, in_cart, email, phone, over18, oldPayments, yearsOfFidelity, ord);
        client1Repo.add(entity);
    }

    public void registerNewEmployee(String name, String surname, char gender, int age, List<String> in_cart, String email, String phone, boolean over18, List<Float> oldPayments, int yearsOfFidelity, Order ord, String job)
            throws InvalidDataExc {


        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid name");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid surname");
        }
        if (gender != 'F' && gender != 'M') {
            throw new InvalidDataExc("Invalid gender");
        }
        if (age <= 0) {
            throw new InvalidDataExc("Invalid age");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid email");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid phone number");
        }
        if (job == null || job.trim().isEmpty()) {
            throw new InvalidDataExc("Invalid job ");
        }
        Employee entity = new Employee(name, surname, gender, age, in_cart, email, phone, over18, oldPayments, yearsOfFidelity, ord, job);
        client1Repo.add(entity);
    }

    public Client[] getAllClients() {
        List<Client> result = new ArrayList<>();
        for (int i = 0; i < client1Repo.getSize(); i++) {
            if (client1Repo.get(i) != null) {
                result.add(client1Repo.get(i));
            }
        }
        return result.toArray(new Client[0]);
    }

    public void removeACLient(String email) throws InvalidDataExc {
        boolean ok = true;
        int i;

        for (i = 0; i < client1Repo.getNumberOf() && ok; i++) {
            if (Objects.equals(client1Repo.get(i).getEmail(), email))
                ok = false;

        }
        if (ok)
            throw new InvalidDataExc("Invalid Client");

        else
            client1Repo.delete(client1Repo.get(i - 1));
    }

    public boolean validateEmail(String emailRegister) {
        for (int i = 0; i < client1Repo.getNumberOf(); i++)
            if (Objects.equals(client1Repo.get(i).getEmail(), emailRegister))
                return true;
        return false;


    }
    public void readClient(){
        try {
             List<Client> client=new ArrayList<Client>();

            client = ReaderWriterIO.readClient("ClientCSV.csv");
            for (Client c : client) {
                client1Repo.add(c);
            }

            Audit.writeAuditCSV("Read client from CSV read file");
        } catch ( IOException | InvalidDataExc e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void addClient(Client c) throws IOException {
       // client1Repo.add(c);

            try {

                ReaderWriterIO.writeClient("ClientCSV.csv", c);

                Audit.writeAuditCSV("Write client to CSV");
            } catch (InvalidDataExc e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

    }

}