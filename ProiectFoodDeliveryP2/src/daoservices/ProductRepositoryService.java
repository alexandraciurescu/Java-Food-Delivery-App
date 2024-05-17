package daoservices;

import dao.*;
import model.*;

import java.sql.SQLException;

import static util.Constants.*;

public class ProductRepositoryService {
    private BurgerDAO burgerDAO = BurgerDAO.getInstance();
    private FriesDAO friesDAO = FriesDAO.getInstance();
    private MilkshakeDAO milkshakeDAO = MilkshakeDAO.getInstance();

    public ProductRepositoryService() throws SQLException {}


    public Burger getBurgerByName(String name) throws SQLException{
        Burger burger = burgerDAO.read(name);
        if(burger != null){
            System.out.println(burger);
        }else {
            System.out.println("No burger having this name...");
        }

        return burger;
    }

    public Fries getFriesByName(String name) throws SQLException{
        Fries fries = friesDAO.read(name);
        if(fries != null){
            System.out.println(fries);
        }else {
            System.out.println("No fries having this name...");
        }
        return fries;
    }

    public Milkshake getMilkshakeByName(String name) throws SQLException{
        Milkshake milkshake = milkshakeDAO.read(name);
        if(milkshake != null){
            System.out.println(milkshake);
        }else {
            System.out.println("No milkshake having this name...");
        }
        return milkshake;
    }

    public void removeProduct(String typeOfProduct, String name) throws SQLException {
        Product product = getProduct(typeOfProduct, name);
        if (product == null) return;

        switch (product){
            case Burger burger -> burgerDAO.delete(burger);
            case Fries fries -> friesDAO.delete(fries);
            case Milkshake m -> milkshakeDAO.delete(m);
            default -> throw new IllegalStateException("Unexpected value: " + product);
        }

        System.out.println("Removed " + product);
    }

    public void addProduct(Product product) throws SQLException{
        if(product != null){
            switch (product){
                case Burger burger -> burgerDAO.add(burger);
                case Fries fries -> friesDAO.add(fries);
                case Milkshake m -> milkshakeDAO.add(m);
                default -> throw new IllegalStateException("Unexpected value: " + product);
            }
        }
    }

    public Product getProduct(String typeOfProduct, String name) {
        Product product = null ;
        try{
        if(typeOfProduct.equals(BURGER))
            product = getBurgerByName(name);
        else if(typeOfProduct.equals(FRIES))
            product = getFriesByName(name);
        else  product = getMilkshakeByName(name);

        if(product == null) {
            System.out.println("No product having name " + name);
            return null;
        } }
        catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
        return product;
    }

    public void update(Product product) throws SQLException {
        if(product != null){
            switch (product){
                case Burger b-> burgerDAO.update(b);
                case Fries f -> friesDAO.update(f);
                case Milkshake m -> milkshakeDAO.update(m);
                default -> throw new IllegalStateException("Unexpected value: " + product);
            }
        }
    }
}
