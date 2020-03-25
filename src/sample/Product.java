package sample;

import javax.swing.*;
import java.util.Objects;

public class Product {

    private int id;
    private String brand;
    private String name;
    private float price;
    private int quantity;

    public Product(int id, String brand, String name, float price, int quantity) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(name, product.name);
    }

    @Override
    public String toString() {
        return brand + ' ' +
                name + ' ' +
                " " + price;
    }

    public Product() { }

    public boolean buyProduct(int quantity)
    {
        boolean ok = true;
        if(this.getQuantity() < quantity)
        {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,"Out of stock!");
            ok = false;
        }
        else
            this.setQuantity(this.getQuantity() - quantity);

        return ok;
    }
}
