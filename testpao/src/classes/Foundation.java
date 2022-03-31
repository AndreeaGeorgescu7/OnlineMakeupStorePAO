package classes;

import java.util.List;
import java.util.Objects;

public class Foundation extends Product {
    private String foundationShade;
    private String forTypeOfSkin;

    public Foundation(String product_name, String brand, String valability, float price, List<String> ingredients, String foundationShade, String forTypeOfSkin) {
        super(product_name, brand, valability, price, ingredients);
        this.foundationShade = foundationShade;
        this.forTypeOfSkin = forTypeOfSkin;
    }

    public String getFoundationShade() {
        return foundationShade;
    }

    public void setFoundationShade(String foundationShade) {
        this.foundationShade = foundationShade;
    }

    public String getForTypeOfSkin() {
        return forTypeOfSkin;
    }

    public void setForTypeOfSkin(String forTypeOfSkin) {
        this.forTypeOfSkin = forTypeOfSkin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Foundation that = (Foundation) o;
        return Objects.equals(foundationShade, that.foundationShade) && Objects.equals(forTypeOfSkin, that.forTypeOfSkin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foundationShade, forTypeOfSkin);
    }

    @Override
    public String toString() {
        return "Foundation{" +
                "foundationShade='" + foundationShade + '\'' +
                ", forTypeOfSkin='" + forTypeOfSkin + '\'' +
                ", product_name='" + product_name + '\'' +
                ", brand='" + brand + '\'' +
                ", valability='" + valability + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
