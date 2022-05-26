package classes;

import java.util.Locale;
import java.util.Objects; ///for sout
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;



public class Product {
    protected String product_name;
    protected String brand;
    protected String valability;
    protected float price;
    List<String> ingredients = new ArrayList<String>();


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getValability() {
        return valability;
    }

    public void setValability(String valability) {
        this.valability = valability;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Product(String product_name, String brand, String valability, float price, List<String> ingredients) {
        this.product_name = product_name;
        this.brand = brand;
        this.valability = valability;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Product(String product_name, String brand, String valability, float price) {
        this.product_name = product_name;
        this.brand = brand;
        this.valability = valability;
        this.price = price;
    }

    public Product() {
        this.product_name = null;
        this.brand = null;
        this.valability = null;
        this.price = 0;
        this.ingredients.add("Not available");
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean includeIngredient(String word) {
        return ingredients.contains(word);
    }

    public boolean isBIO() {
      if(ingredients != null)
        for (String temp : ingredients) {
            String[] parts = temp.split(" ");
            for (String str : parts)
                if (str.toLowerCase(Locale.ROOT).equals("bio"))
                    return true;


        }
        return false;
    }

    public boolean includeWord(String word) {
        String p1[] = this.product_name.split(" ");
        for (String str : p1)
            if (str.toLowerCase(Locale.ROOT).equals(word))
                return true;
        String p2[] = this.brand.split(" ");
        for (String str : p2)
            if (str.toLowerCase(Locale.ROOT).equals(word))
                return true;
        return false;


    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 && Objects.equals(product_name, product.product_name) && Objects.equals(brand, product.brand) && Objects.equals(valability, product.valability) && Objects.equals(ingredients, product.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_name, brand, valability, price, ingredients);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + product_name + '\'' +
                ", brand='" + brand + '\'' +
                ", valability='" + valability + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
    ///for writing in CSV files
    public String stringFormat() {
        return '\n'+ product_name + ',' +
                brand + ',' +
                 valability + ',' +
                + price ;
    }
}
