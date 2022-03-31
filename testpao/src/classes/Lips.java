package classes;

import java.util.List;
import java.util.Objects;

public class Lips extends Product {
    private String shade;
    private String type;

    public Lips(String product_name, String brand, String valability, float price, List<String> ingredients, String shade, String type) {
        super(product_name, brand, valability, price, ingredients);
        this.shade = shade;
        this.type = type;
    }

    public String getShade() {
        return shade;
    }

    public String getType() {
        return type;
    }

    public Lips(String shade, String type) {
        this.shade = shade;
        this.type = type;
    }

    public Lips() {
        this.shade = null;
        this.type = null;
    }

    public void setShade(String shade) {
        this.shade = shade;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lips lips = (Lips) o;
        return Objects.equals(shade, lips.shade) && Objects.equals(type, lips.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shade, type);
    }

    @Override
    public String toString() {
        return "Lips{" +
                "product_name='" + product_name + '\'' +
                ", brand='" + brand + '\'' +
                ", valability='" + valability + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ",shade='" + shade + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
