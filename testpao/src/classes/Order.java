package classes;

import java.util.*;

public class Order {
    private String address;
    private String cardDetails;
    private float transportTax;
    private String dateOfOrder;
    private String postalCode;
    private int estimatedDaysDelivery;
    private List<Eyeshadow> eyes = new ArrayList<Eyeshadow>();
    private List<Lips> lip = new ArrayList<Lips>();
    private List<Foundation> found = new ArrayList<Foundation>();

    public Order(String address, String cardDetails, float transportTax, String dateOfOrder, String postalCode, int estimatedDaysDelivery, List<Eyeshadow> eyes, List<Lips> lip, List<Foundation> found) {
        this.address = address;
        this.cardDetails = cardDetails;
        this.transportTax = transportTax;
        this.dateOfOrder = dateOfOrder;
        this.postalCode = postalCode;
        this.estimatedDaysDelivery = estimatedDaysDelivery;
        this.eyes = eyes;
        this.lip = lip;
        this.found = found;
    }

    public Order() {
        this.address = " ";
        this.cardDetails = " ";
        this.transportTax = 15;
        this.dateOfOrder = " ";
        this.postalCode = " ";
        this.estimatedDaysDelivery = 7;
        /*this.eyes = ;
        this.lip = ;
        this.found = ;*/
    }

    public Order(String address, String cardDetails, String postalCode, List<Eyeshadow> eyes, List<Lips> lip, List<Foundation> found) {
        this.address = address;
        this.cardDetails = cardDetails;
        this.postalCode = postalCode;
        this.eyes = eyes;
        this.lip = lip;
        this.found = found;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public float getTransportTax() {
        return transportTax;
    }

    public void setTransportTax(float transportTax) {
        this.transportTax = transportTax;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getEstimatedDaysDelivery() {
        return estimatedDaysDelivery;
    }

    public void setEstimatedDaysDelivery(int estimatedDaysDelivery) {
        this.estimatedDaysDelivery = estimatedDaysDelivery;
    }

    public List<Eyeshadow> getEyes() {
        return eyes;
    }

    public void setEyes(List<Eyeshadow> eyes) {
        this.eyes = eyes;
    }

    public List<Lips> getLip() {
        return lip;
    }

    public void setLip(List<Lips> lip) {
        this.lip = lip;
    }

    public List<Foundation> getFound() {
        return found;
    }

    public void setFound(List<Foundation> found) {
        this.found = found;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.transportTax, transportTax) == 0 && estimatedDaysDelivery == order.estimatedDaysDelivery && Objects.equals(address, order.address) && Objects.equals(cardDetails, order.cardDetails) && Objects.equals(dateOfOrder, order.dateOfOrder) && Objects.equals(postalCode, order.postalCode) && Objects.equals(eyes, order.eyes) && Objects.equals(lip, order.lip) && Objects.equals(found, order.found);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, cardDetails, transportTax, dateOfOrder, postalCode, estimatedDaysDelivery, eyes, lip, found);
    }

    @Override
    public String toString() {
        return "Order{" +
                "address='" + address + '\'' +
                ", cardDetails='" + cardDetails + '\'' +
                ", transportTax=" + transportTax +
                ", dateOfOrder='" + dateOfOrder + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", estimatedDaysDelivery=" + estimatedDaysDelivery +
                ", eyes=" + eyes +
                ", lip=" + lip +
                ", found=" + found +
                '}';
    }

    public float totalAmount() {
        float s = 0;
        for (Eyeshadow element : eyes) {
            s += element.getPrice();

        }

        for (Lips element : lip) {
            s += element.getPrice();

        }
        for (Foundation element : found) {
            s += element.getPrice();

        }
        Random rand = new Random();
        if (rand.nextInt(1000) % 100 == 0) {
            setTransportTax(0);
            System.out.println("Congrats!The transport cost is 0 lei today!");
        }


        return s + transportTax;
    }

    public void transportFreeAlert() {
        float s = totalAmount();
        if (s < 150)
            System.out.println("Another " + (150 - s) + " lei and the transport is free!");
    }

    public void addACoupon(String coupon) {
        if (coupon.toUpperCase(Locale.ROOT).equals("ANDA")) {
            float x = totalAmount();

            float s = (x + transportTax) - (x + transportTax) * 20 / 100;
            System.out.println("Total: " + s + " lei");

        } else
            System.out.println("Sorry! This code does not exist");

    }

    public void deleteEyeshdow(Eyeshadow e) {
        eyes.remove(e);
    }

    public void deleteLips(Lips l) {
        lip.remove(l);
    }

    public void deleteFoundation(Foundation f) {
        found.remove(f);
    }


}
