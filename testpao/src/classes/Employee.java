package classes;

import java.util.List;
import java.util.Objects;

public class Employee extends Client {
    private String job;

    public Employee(String name, String surname, char gender, int age, List<String> in_cart, String email, String phone, boolean over18, List<Float> oldPayments, int yearsOfFidelity, Order ord, String job) {
        super(name, surname, gender, age, in_cart, email, phone, over18, oldPayments, yearsOfFidelity, ord);

        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), job);
    }

    @Override
    public String toString() {
        return "Employee{" +
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
                ", job='" + job + '\'' +
                '}';
    }
}
