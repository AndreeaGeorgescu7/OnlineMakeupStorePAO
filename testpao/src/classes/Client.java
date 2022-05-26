package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    protected String name;
    protected String surname;
    protected char gender;
    protected int age;
    protected List<String> in_cart = new ArrayList<String>();
    protected String email;
    protected String phone;
    protected boolean over18;
    protected List<Float> oldPayments = new ArrayList<Float>();
    protected int yearsOfFidelity;
    protected Order ord;

    public Client(String name, String surname, char gender, int age, List<String> in_cart, String email, String phone, boolean over18, List<Float> oldPayments, int yearsOfFidelity, Order ord) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.in_cart = in_cart;
        this.email = email;
        this.phone = phone;
        this.over18 = over18;
        this.oldPayments = oldPayments;
        this.yearsOfFidelity = yearsOfFidelity;
        this.ord = ord;
    }

    public Client(String name, String surname, char gender, int age, String email, String phone, boolean over18, int yearsOfFidelity) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.over18 = over18;
        this.yearsOfFidelity = yearsOfFidelity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getIn_cart() {
        return in_cart;
    }

    public void setIn_cart(List<String> in_cart) {
        this.in_cart = in_cart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isOver18() {
        return over18;
    }

    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return gender == client.gender && age == client.age && over18 == client.over18 && yearsOfFidelity == client.yearsOfFidelity && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(in_cart, client.in_cart) && Objects.equals(email, client.email) && Objects.equals(phone, client.phone) && Objects.equals(oldPayments, client.oldPayments) && Objects.equals(ord, client.ord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, age, in_cart, email, phone, over18, oldPayments, yearsOfFidelity, ord);
    }

    public List<Float> getOldPayments() {
        return oldPayments;
    }

    public void setOldPayments(List<Float> oldPayments) {
        this.oldPayments = oldPayments;
    }

    public int getYearsOfFidelity() {
        return yearsOfFidelity;
    }

    public void setYearsOfFidelity(int yearsOfFidelity) {
        this.yearsOfFidelity = yearsOfFidelity;
    }

    public Order getOrd() {
        return ord;
    }

    public void setOrd(Order ord) {
        this.ord = ord;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", in_cart=" + in_cart +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", over18=" + over18 +
                ", oldPayments=" + oldPayments +
                ", yearsOfFidelity=" + yearsOfFidelity +
                ", ord=" + ord +
                '}';
    }

    public String loyalty() {
        if (this.yearsOfFidelity > 3)
            return "Old customer";
        return "New customer";

    }

}


