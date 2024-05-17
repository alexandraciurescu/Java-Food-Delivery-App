package dao;

import daoservices.DatabaseConnection;
import model.Fries;
import model.Fries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriesDAO implements DaoInterface<Fries> {
    private static FriesDAO friesDAO;

    private Connection connection = DatabaseConnection.getConnection();


    private FriesDAO() throws SQLException {}

    public static FriesDAO getInstance() throws SQLException {
        if(friesDAO == null){
            friesDAO = new FriesDAO();
        }
        return friesDAO;
    }



    @Override
    public void add(Fries fries) throws SQLException {
        String sql = "INSERT INTO fooddelivery.fries VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, fries.getName());
            statement.setDouble(2, fries.getPrice());
            statement.setString(3, fries.getDescription());
            statement.setString(4, fries.getType());
            statement.setString(5, fries.getSize());
            statement.setString(6, fries.getTopping());
            statement.executeUpdate();
        }
    }

    @Override
    public Fries read(String name) throws SQLException {
        String sql = "SELECT * FROM fooddelivery.fries s WHERE s.name = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()) {
                Fries fries = new Fries();
                fries.setName(rs.getString("name"));
                fries.setPrice(rs.getDouble("price"));
                fries.setDescription(rs.getString("description"));
                fries.setType(rs.getString("type"));
                fries.setSize(rs.getString("size"));
                fries.setTopping(rs.getString("topping"));
                return fries;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Fries fries) throws SQLException {
        String sql = "DELETE FROM fooddelivery.fries s WHERE s.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, fries.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Fries fries) throws SQLException {
        String sql = "UPDATE fooddelivery.fries set  price = ?" +
                " , description = ? , type = ?, size = ?, topping = ? where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
           // statement.setString(1, fries.getName());
            statement.setDouble(1, fries.getPrice());
            statement.setString(2, fries.getDescription());
            statement.setString(3, fries.getType());
            statement.setString(4, fries.getSize());
            statement.setString(5, fries.getTopping());
            statement.setString(6, fries.getName());
            statement.executeUpdate();
        }

    }
}
