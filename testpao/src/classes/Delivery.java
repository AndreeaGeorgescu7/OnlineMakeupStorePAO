package classes;

import java.util.Objects;

public class Delivery {
    private String companyName;
    private String deliveryMan;
    private String phoneNumber;
    private int numberOfDelivery;
    private String typeOfDelivery; /*car/bike*/

    public Delivery(String companyName, String deliveryMan, String phoneNumber, int numberOfDelivery, String typeOfDelivery) {
        this.companyName = companyName;
        this.deliveryMan = deliveryMan;
        this.phoneNumber = phoneNumber;
        this.numberOfDelivery = numberOfDelivery;
        this.typeOfDelivery = typeOfDelivery;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfDelivery() {
        return numberOfDelivery;
    }

    public void setNumberOfDelivery(int numberOfDelivery) {
        this.numberOfDelivery = numberOfDelivery;
    }

    public String getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(String typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return numberOfDelivery == delivery.numberOfDelivery && Objects.equals(companyName, delivery.companyName) && Objects.equals(deliveryMan, delivery.deliveryMan) && Objects.equals(phoneNumber, delivery.phoneNumber) && Objects.equals(typeOfDelivery, delivery.typeOfDelivery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, deliveryMan, phoneNumber, numberOfDelivery, typeOfDelivery);
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "companyName='" + companyName + '\'' +
                ", deliveryMan='" + deliveryMan + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", numberOfDelivery=" + numberOfDelivery +
                ", typeOfDelivery='" + typeOfDelivery + '\'' +
                '}';
    }

    public void bonus() {
        if (this.numberOfDelivery % 250 == 0)
            System.out.println("Alert! His commission must be increased by 2% ");

    }
}
