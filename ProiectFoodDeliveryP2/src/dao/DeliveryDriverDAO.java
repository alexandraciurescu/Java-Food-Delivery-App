package dao;

import daoservices.DatabaseConnection;
import model.DeliveryDriver;
import model.DeliveryDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDriverDAO implements DaoInterface<DeliveryDriver>{
    private static DeliveryDriverDAO driverDAO;

    private Connection connection = DatabaseConnection.getConnection();

    public Connection getConnection() {
        return connection;
    }

    private DeliveryDriverDAO() throws SQLException {}

    public static DeliveryDriverDAO getInstance() throws SQLException {
        if(driverDAO == null){
            driverDAO = new DeliveryDriverDAO();
        }
        return driverDAO;
    }

    @Override
    public void add(DeliveryDriver deliveryDriver) throws SQLException {
        String sql = "INSERT INTO fooddelivery.DeliveryDriver VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, deliveryDriver.getName());
            statement.setString(2, deliveryDriver.getPhone());
            statement.setString(3, deliveryDriver.getEmail());
            statement.setDouble(4, deliveryDriver.getRating());
            statement.setString(5, deliveryDriver.getVehicle());
            statement.executeUpdate();
        }
    }

    @Override
    public DeliveryDriver read(String name) throws SQLException {
        String sql = "SELECT * FROM fooddelivery.DeliveryDriver s WHERE s.name = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()) {
                DeliveryDriver deliveryDriver = new DeliveryDriver();
                deliveryDriver.setName(rs.getString(("name")));
                deliveryDriver.setPhone(rs.getString("phone"));
                deliveryDriver.setEmail(rs.getString("email"));
                deliveryDriver.setRating(rs.getDouble("rating"));
                deliveryDriver.setVehicle(rs.getString("vehicle"));
                return deliveryDriver;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(DeliveryDriver deliveryDriver) throws SQLException {
        String sql = "DELETE FROM fooddelivery.DeliveryDriver s WHERE s.name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, deliveryDriver.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(DeliveryDriver deliveryDriver) throws SQLException {
        String sql = "UPDATE fooddelivery.DeliveryDriver set phone = ? , email = ?" +
                " , rating = ? , vehicle = ? where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, deliveryDriver.getPhone());
            statement.setString(2, deliveryDriver.getEmail());
            statement.setDouble(3, deliveryDriver.getRating());
            statement.setString(4, deliveryDriver.getVehicle());
            statement.setString(5, deliveryDriver.getName());
            statement.executeUpdate();
        }

    }


}
