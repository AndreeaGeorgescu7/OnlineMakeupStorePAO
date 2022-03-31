package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Eyeshadow extends Product {
    private List<String> colors = new ArrayList<String>();

    public Eyeshadow(String product_name, String brand, String valability, float price, List<String> ingredients, List<String> colors) {
        super(product_name, brand, valability, price, ingredients);
        this.colors = colors;
    }

    public Eyeshadow(List<String> colors) {
        this.colors = colors;
    }

    public Eyeshadow() {
        this.colors.add("Not available");
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Eyeshadow eyeshadow = (Eyeshadow) o;
        return Objects.equals(colors, eyeshadow.colors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colors);
    }

    @Override
    public String toString() {
        return "Eyeshadow{" +
                " product_name='" + product_name + '\'' +
                ", brand='" + brand + '\'' +
                ", valability='" + valability + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients + "colors=" + colors +
                '}';
    }

    public String dimension() {
        if (this.colors.size() > 8)
            return "BIG";
        return "SMALL";

    }
}
