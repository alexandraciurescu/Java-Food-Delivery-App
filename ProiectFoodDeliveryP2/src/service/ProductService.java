package service;

import daoservices.PersonRepositoryService;
import daoservices.ProductRepositoryService;
import model.*;
import util.FileManagement;

import java.sql.SQLException;
import java.util.Scanner;

import static util.Constants.*;

public class ProductService {

    private ProductRepositoryService databaseService;

    public ProductService() throws SQLException  {
        this.databaseService = new ProductRepositoryService();
    }

    public void create(Scanner scanner) throws SQLException  {
        System.out.println("Enter type of product [burger/fries/milkshake]:");
        String typeOfProduct = scanner.nextLine().toLowerCase();
        if(!typeOfProductValidation(typeOfProduct)) { return; }
        productInit(scanner, typeOfProduct);
    }

    public void read(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        try {
            databaseService.getBurgerByName(name);
            databaseService.getFriesByName(name);
            databaseService.getMilkshakeByName(name);
            FileManagement.scriereFisierChar(AUDIT_FILE, "citire produs " + name);
        }
        catch (SQLException e) {
            System.out.println("Cannot find product " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Type of product: ");
        String typeOfProduct = scanner.nextLine();
        if(!typeOfProductValidation(typeOfProduct)) { return; }
        try{
        databaseService.removeProduct(typeOfProduct, name);
        FileManagement.scriereFisierChar(AUDIT_FILE, "stergere produs " + name);
        }
        catch (SQLException e) {
            System.out.println("Cannot delete product " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void update(Scanner scanner) {
        System.out.println("Type of product: ");
        String typeOfProduct = scanner.nextLine();
        if(!typeOfProductValidation(typeOfProduct)) { return; }
        System.out.println("Name: ");
        String name = scanner.nextLine();
        Product product = databaseService.getProduct(typeOfProduct, name);
        if (product == null) { return;}
        Product productGeneralInfo = setGeneralInfo(name, scanner);
        product.setName(name);
        product.setPrice(productGeneralInfo.getPrice());
        product.setDescription(productGeneralInfo.getDescription());
        if(typeOfProduct.equals(BURGER)){
            burgerInit(scanner, (Burger) product);
        }else if(typeOfProduct.equals(FRIES)){
            friesInit(scanner, (Fries) product);
        }
        else milkshakeInit(scanner, (Milkshake) product);
        try { databaseService.update(product);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update produs " + product.getName());
        }catch (SQLException e){
            System.out.println("Cannot update product " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public boolean typeOfProductValidation(String typeOfProduct) {

        if(! typeOfProduct.equals(BURGER) && !typeOfProduct.equals(MILKSHAKE)
                && !typeOfProduct.equals(FRIES)){
            System.out.println("Wrong type");
            return false;
        }
        return true;
    }

    private void productInit(Scanner scanner, String typeOfProduct) throws SQLException {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        if (typeOfProduct.equals(BURGER) && databaseService.getBurgerByName(name) != null) {return;}
        if (typeOfProduct.equals(FRIES) && databaseService.getFriesByName(name) != null) {return;}
        if (typeOfProduct.equals(MILKSHAKE) && databaseService.getMilkshakeByName(name) != null) {return;}
        Product product = setGeneralInfo(name, scanner);
        if(typeOfProduct.equals(BURGER)){
            Burger burger = new Burger(product);
            burgerInit(scanner, burger);
            product = burger;
        } else if (typeOfProduct.equals(FRIES)){
            Fries fries = new Fries(product);
            friesInit(scanner, fries);
            product = fries;
        }
        else {
            Milkshake m = new Milkshake(product);
            milkshakeInit(scanner, m);
            product = m;
        }
        try {
            databaseService.addProduct(product);
            System.out.println("Created: " + product);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add produs " + product.getName());
        } catch (SQLException e) {
            System.out.println("Cannot create " + product + " exception " + e.getSQLState() + " " + e.getMessage());
        }
    }

    private Product setGeneralInfo(String name, Scanner scanner) {
        System.out.println("Price: ");
        Float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Description: ");
        String desc = scanner.nextLine();
        return new Product(name, price, desc);
    }

    private void burgerInit(Scanner scanner, Burger burger) {
        System.out.println("Type of meat:");
        String meat = scanner.nextLine();
        System.out.println("Sauce:");
        String sauce = scanner.nextLine();
        System.out.println("Cheese: [Type 'Yes' if you want]");
        String cheese = scanner.nextLine();
        if(cheese.equals("Yes") || cheese.equals("yes")) burger.setCheese(true);
        else burger.setCheese(false);


        burger.setTypeOfMeat(meat);
        burger.setSauce(sauce);


    }
    private void friesInit(Scanner scanner, Fries f) {
        System.out.println("Type:");
        String type = scanner.nextLine();
        System.out.println("Size:");
        String size = scanner.nextLine();
        System.out.println("Topping:");
        String topping = scanner.nextLine();


        f.setType(type);
        f.setTopping(topping);
        f.setSize(size);

    }
    private void milkshakeInit(Scanner scanner, Milkshake milkshake) {
        System.out.println("Flavour:");
        String flavour = scanner.nextLine();
        System.out.println("Topping:");
        String topping = scanner.nextLine();
        System.out.println("Whipping cream: [Type 'Yes' if you want]");
        String cream = scanner.nextLine();
        if(cream.equals("Yes") || cream.equals("yes")) milkshake.setWhippingCream(true);
        else milkshake.setWhippingCream(false);

        milkshake.setFlavour(flavour);
        milkshake.setTopping(topping);

    }

}
