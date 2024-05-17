package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;
    private int id;
    private LocalDateTime data;
    private float cost;
    private String paymentType;
    private List<Product> products;
    private Client client;

    private DeliveryDriver driver;

    public int getId() {
        return id;
    }

    public Order(LocalDateTime data, float cost, String paymentType,
                 List<Product> products, Client client, DeliveryDriver d) {

        this.data= data;
        this.cost=cost;
        this.paymentType=paymentType;
        this.products=products;
        this.client=client;
        this.id = nextId;
        this.driver=d;
        nextId++;
    }

    public Order() {
        this.id = nextId++;
        this.data= null;
        this.cost=0;
        this.paymentType=null;
        this.products=new ArrayList<>();
        this.client=null;
        this.driver=null;
    }

    public DeliveryDriver getDriver() {
        return driver;
    }

    public void setDriver(DeliveryDriver driver) {
        this.driver = driver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public  List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void addProductToOrder(Product product)
    {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Order" + '\n' +
                "id = " + id + '\n' +
                "data = " + data + '\n' +
                "cost = " + cost + '\n' +
                "paymentType = " + paymentType + '\n' +
                "products = " + products + '\n' +
                "client = " + client + '\n' +
                "deliveryDriver = " + driver
                ;
    }
}
