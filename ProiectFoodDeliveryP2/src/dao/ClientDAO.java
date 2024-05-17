package dao;

import daoservices.DatabaseConnection;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO implements DaoInterface<Client> {
    private static ClientDAO clientDAO;

    private Connection connection = DatabaseConnection.getConnection();


    private ClientDAO() throws SQLException {}

    public static ClientDAO getInstance() throws SQLException {
        if(clientDAO == null){
            clientDAO = new ClientDAO();
        }
        return clientDAO;
    }



    @Override
    public void add(Client Client) throws SQLException {
        String sql = "INSERT INTO fooddelivery.client VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, Client.getName());
            statement.setString(2, Client.getPhone());
            statement.setString(3, Client.getEmail());
            statement.setString(4, Client.getAddress());
            statement.setString(5, Client.getCard());
            statement.executeUpdate();
        }
    }

    @Override
    public Client read(String name) throws SQLException {
        String sql = "SELECT * FROM fooddelivery.client s WHERE s.name = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, name);
            rs = statement.executeQuery();

            while (rs.next()) {
                Client Client = new Client();
                Client.setName(rs.getString("name"));
                Client.setPhone(rs.getString("phone"));
                Client.setEmail(rs.getString("email"));
                Client.setAddress(rs.getString("address"));
                Client.setCard(rs.getString("card"));
                return Client;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public void delete(Client client) throws SQLException {
        String sql = "DELETE FROM fooddelivery.client s WHERE s.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, client.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Client Client) throws SQLException {
        String sql = "UPDATE fooddelivery.client set phone = ? , email = ?" +
                " , address = ? , card = ? where name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, Client.getPhone());
            statement.setString(2, Client.getEmail());
            statement.setString(3, Client.getAddress());
            statement.setString(4, Client.getCard());
            statement.setString(5, Client.getName());
            statement.executeUpdate();
        }

    }
}
