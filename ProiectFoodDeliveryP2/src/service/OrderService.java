package service;

import dao.*;
import daoservices.DatabaseConnection;
import daoservices.PersonRepositoryService;
import daoservices.ProductRepositoryService;
import model.*;
import daoservices.OrderRepositoryService;
import util.Randomizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class OrderService {
    private OrderRepositoryService databaseService;
    private Connection connection;

    {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public OrderService() {
        this.databaseService = new OrderRepositoryService();
    }

    public void create(Scanner scanner) throws SQLException {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        PersonRepositoryService prs = new PersonRepositoryService();
        Client c = prs.getClientByName(name);
        if (c == null) return;
        else {Order order = new Order(); order.setClient(c); afisareMeniu(); orderAddProducts(scanner, order);}
    }

    public void read(Scanner scanner) {
        System.out.println("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if(OrderDAO.getOrders().size()<id || id<=0)
        {System.out.println("Incorrect ID..."); return;}
        databaseService.getOrderById(id);
    }

    public void delete(Scanner scanner) {
        System.out.println("ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        //validare id
        if(OrderDAO.getOrders().size()<id || id<=0)
        {System.out.println("Incorrect ID..."); return;}
        databaseService.removeOrder(id);
    }

    public List<DeliveryDriver> getDeliveryDrivers() throws SQLException {
        List<DeliveryDriver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM deliveryDriver"; // Asumând că tabela se numește DeliveryDrivers
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DeliveryDriver driver = new DeliveryDriver();
                driver.setName(resultSet.getString("name")); // Asumând că coloanele se numesc name, phone, etc.
                driver.setPhone(resultSet.getString("phone"));
                driver.setEmail(resultSet.getString("email"));
                driver.setRating(resultSet.getDouble("rating"));
                driver.setVehicle(resultSet.getString("vehicle"));
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public List<Burger> getBurgers() throws SQLException {
        List<Burger> burgers = new ArrayList<>();
        String sql = "SELECT * FROM burger";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Burger burger = new Burger();
                burger.setName(resultSet.getString("name"));
                burger.setPrice(resultSet.getDouble("price"));
                burger.setDescription(resultSet.getString("description"));
                burger.setTypeOfMeat(resultSet.getString("typeOfMeat"));
                burger.setSauce(resultSet.getString("sauce"));
                burger.setCheese(resultSet.getBoolean("cheese"));
                burgers.add(burger);
            }
        }
        return burgers;
    }

    public List<Fries> getFries() throws SQLException {
        List<Fries> friess = new ArrayList<>();
        String sql = "SELECT * FROM fries";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Fries fries = new Fries();
                fries.setName(resultSet.getString("name"));
                fries.setPrice(resultSet.getDouble("price"));
                fries.setDescription(resultSet.getString("description"));
                fries.setType(resultSet.getString("type"));
                fries.setSize(resultSet.getString("size"));
                fries.setTopping(resultSet.getString("topping"));
                friess.add(fries);
            }
        }
        return friess;
    }

    public List<Milkshake> getMilkshakes() throws SQLException {
        List<Milkshake> milkshakes = new ArrayList<>();
        String sql = "SELECT * FROM milkshake";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Milkshake milkshake = new Milkshake();
                milkshake.setName(resultSet.getString("name"));
                milkshake.setPrice(resultSet.getDouble("price"));
                milkshake.setDescription(resultSet.getString("description"));
                milkshake.setFlavour(resultSet.getString("flavour"));
                milkshake.setTopping(resultSet.getString("topping"));
                milkshake.setWhippingCream(resultSet.getBoolean("whippingCream"));
                milkshakes.add(milkshake);
            }
        }
        return milkshakes;
    }

    private void afisareMeniu() throws SQLException {
        System.out.println("Our menu: ");
        System.out.print("Burgers: ");
        for (Burger b : this.getBurgers())
            System.out.print(b.getName() + ", ");
        System.out.println();
        System.out.print("Fries: ");
        for (Fries f : this.getFries())
            System.out.print(f.getName() + ", ");
        System.out.println();
        System.out.print("Milkshakes: ");
        for (Milkshake m : this.getMilkshakes())
            System.out.print(m.getName() + ", ");
        System.out.println();

    }

    private void orderInit(Scanner scanner, Order order) throws SQLException {
        //calculam totalul de plata
        float total = 0;
        for (Product p : order.getProducts())
            total += p.getPrice();
        System.out.println("Total price: " + total + "$");
        order.setCost(total);
        while (true) {
            System.out.println("How would you like to pay? [cash/card]");
            String pay = scanner.nextLine();
            if (!pay.equals("cash") && !pay.equals("card"))
                System.out.println("Incorrect type...");
            else {
                order.setPaymentType(pay);
                break;
            }
        }
        order.setData(LocalDateTime.now());
        this.setDriverForOrder(order);
    }

    private void setDriverForOrder(Order order) throws SQLException {
        Randomizer r = new Randomizer();
        if (this.getDeliveryDrivers().isEmpty()) {
            System.out.println("There are no available delivery drivers at the moment... Please try again later.");
            return;
        } else {
            int randomNumber = r.GenerateNumber(this.getDeliveryDrivers().size());
            System.out.println(randomNumber);
            DeliveryDriver d = this.getDeliveryDrivers().get(randomNumber);
            order.setDriver(d);
        }
        System.out.println("You order ID is: " + order.getId());
        System.out.println("The delivery will be handled by " + order.getDriver().getName());
        databaseService.addOrder(order);
    }

    private void orderAddProducts(Scanner scanner, Order order) throws SQLException {
        if(this.getBurgers().isEmpty() && this.getFries().isEmpty() && this.getMilkshakes().isEmpty())
            System.out.println("Please try again later!");
        else {
            List<Product> lista = new ArrayList<>();
            while (true) {
                System.out.println("What would you like to order? [type 'quit' if you finished]");
                String name = scanner.nextLine();
                if (name.equals("quit")) break;
                ProductRepositoryService prs = new ProductRepositoryService();
                Burger b = prs.getBurgerByName(name);
                Fries f = prs.getFriesByName(name);
                Milkshake m = prs.getMilkshakeByName(name);
                if (b != null)  lista.add(b);
                if (f != null)  lista.add(f);
                if (m != null)  lista.add(m);
            }
            if (lista.isEmpty()) {
                System.out.println("You did not add any products...");
                return;
            }
            order.setProducts(lista);
            this.orderInit(scanner,order);
        }


    }
}