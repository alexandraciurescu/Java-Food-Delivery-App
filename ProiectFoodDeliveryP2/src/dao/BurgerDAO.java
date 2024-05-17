package dao;

import daoservices.DatabaseConnection;
import model.Burger;
import model.Burger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BurgerDAO implements DaoInterface<Burger>{
    private static BurgerDAO burgerDAO;

    private Connection connection = DatabaseConnection.getConnection();


    private BurgerDAO() throws SQLException {}

    public static BurgerDAO getInstance() throws SQLException {
        if(burgerDAO == null){
            burgerDAO = new BurgerDAO();
        }
        return burgerDAO;
    }



    @Override
    public void add(Burger burger) throws SQLException {
        String sql = "INSERT INTO fooddelivery.burger VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, burger.getName());
            statement.setDouble(2, burger.getPrice());
            statement.setString(3, burger.getDescription());
            statement.setString(4, burger.getTypeOfMeat());
            statement.setString(5, burger.getSauce());
            statement.setBoolean(6, burger.getCheese());
            statement.executeUpdate();
        }
    }

    @Override
    public Burger read(String name) throws SQLException {
        String sql = "SELECT * FROM fooddelivery.burger s WHERE s.name = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()) {
                Burger burger = new Burger();
                burger.setName(rs.getString("name"));
                burger.setPrice(rs.getDouble("price"));
                burger.setDescription(rs.getString("description"));
                burger.setTypeOfMeat(rs.getString("typeOfMeat"));
                burger.setSauce(rs.getString("sauce"));
                burger.setCheese(rs.getBoolean("cheese"));
                return burger;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Burger burger) throws SQLException {
        String sql = "DELETE FROM fooddelivery.burger s WHERE s.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, burger.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Burger burger) throws SQLException {
        String sql = "UPDATE fooddelivery.burger set  price = ?" +
                " , description = ? , typeOfMeat = ?, sauce = ?, cheese = ? where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            //statement.setString(1, burger.getName());
            statement.setDouble(1, burger.getPrice());
            statement.setString(2, burger.getDescription());
            statement.setString(3, burger.getTypeOfMeat());
            statement.setString(4, burger.getSauce());
            statement.setBoolean(5, burger.getCheese());
            statement.setString(6, burger.getName());
            statement.executeUpdate();
        }

    }
   
}
