package dao;

import daoservices.DatabaseConnection;
import model.Milkshake;
import model.Milkshake;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MilkshakeDAO implements DaoInterface<Milkshake> {

    private static MilkshakeDAO milkshakeDAO;

    private Connection connection = DatabaseConnection.getConnection();


    private MilkshakeDAO() throws SQLException {}

    public static MilkshakeDAO getInstance() throws SQLException {
        if(milkshakeDAO == null){
            milkshakeDAO = new MilkshakeDAO();
        }
        return milkshakeDAO;
    }



    @Override
    public void add(Milkshake milkshake) throws SQLException {
        String sql = "INSERT INTO fooddelivery.milkshake VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, milkshake.getName());
            statement.setDouble(2, milkshake.getPrice());
            statement.setString(3, milkshake.getDescription());
            statement.setString(4, milkshake.getFlavour());
            statement.setString(5, milkshake.getTopping());
            statement.setBoolean(6, milkshake.isWhippingCream());
            statement.executeUpdate();
        }
    }

    @Override
    public Milkshake read(String name) throws SQLException {
        String sql = "SELECT * FROM fooddelivery.milkshake s WHERE s.name = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()) {
                Milkshake milkshake = new Milkshake();
                milkshake.setName(rs.getString("name"));
                milkshake.setPrice(rs.getDouble("price"));
                milkshake.setDescription(rs.getString("description"));
                milkshake.setFlavour(rs.getString("flavour"));
                milkshake.setTopping(rs.getString("topping"));
                milkshake.setWhippingCream(rs.getBoolean("whippingCream"));
                return milkshake;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Milkshake milkshake) throws SQLException {
        String sql = "DELETE FROM fooddelivery.milkshake s WHERE s.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, milkshake.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Milkshake milkshake) throws SQLException {
        String sql = "UPDATE fooddelivery.milkshake set  price = ?" +
                " , description = ? , flavour = ?, topping = ?, whippingCream = ? where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            //statement.setString(1, milkshake.getName());
            statement.setDouble(1, milkshake.getPrice());
            statement.setString(2, milkshake.getDescription());
            statement.setString(3, milkshake.getFlavour());
            statement.setString(4, milkshake.getTopping());
            statement.setBoolean(5, milkshake.isWhippingCream());
            statement.setString(6, milkshake.getName());
            statement.executeUpdate();
        }

    }
}
