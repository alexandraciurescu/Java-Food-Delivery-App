package model;

public class Product {
    protected String name;
    protected double price;
    protected String description;

    public Product(String name, double price, String desc) {
        this.name = name;
        this.price=price;
        this.description=desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product: " + "\n" +
                "name = " + name + "\n" +
                "price = " + price +  "\n" +
                "description = " + description
                ;
    }
}
