package daoservices;
import model.Client;
import model.DeliveryDriver;
import model.Person;
import dao.*;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.DRIVER;

public class PersonRepositoryService {

    private ClientDAO clientDAO = ClientDAO.getInstance();
    private DeliveryDriverDAO deliveryDriverDAO = DeliveryDriverDAO.getInstance();

    public PersonRepositoryService() throws SQLException {
    }


    public Client getClientByName(String name) throws SQLException {
        Client client = clientDAO.read(name);
        if (client != null) {
            System.out.println(client);
        } else {
            System.out.println("No client having this name...");
        }

        return client;
    }


    public DeliveryDriver getDriverByName(String name) throws SQLException {
        DeliveryDriver driver = deliveryDriverDAO.read(name);
        if (driver != null) {
            System.out.println(driver);
        } else {
            System.out.println("No driver having this name...");
        }
        return driver;
    }


    public void removePerson(String typeOfPerson, String name) throws SQLException {
        Person person = getPerson(typeOfPerson, name);
        if (person == null) return;

        switch (person) {
            case DeliveryDriver driver -> deliveryDriverDAO.delete(driver);
            case Client client -> clientDAO.delete(client);
            default -> throw new IllegalStateException("Unexpected value: " + person);
        }

        System.out.println("Removed " + person);
    }


    public void addPerson(Person person) throws SQLException {
        if (person != null) {
            switch (person) {
                case DeliveryDriver driver -> deliveryDriverDAO.add(driver);
                case Client client -> clientDAO.add(client);
                default -> throw new IllegalStateException("Unexpected value: " + person);
            }
        }
    }

    public Person getPerson(String typeOfPerson, String name) {
        Person person = null;
        try {
            if (typeOfPerson.equals(DRIVER)) {
                person = getDriverByName(name);
            } else {
                person = getClientByName(name);
            }
            if (person == null) {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getSQLState() + " " + e.getMessage());
        }
        return person;
    }

    public void update(Person person) throws SQLException {
        if(person != null){
            switch (person){
                case DeliveryDriver d-> deliveryDriverDAO.update(d);
                case Client c -> clientDAO.update(c);
                default -> throw new IllegalStateException("Unexpected value: " + person);
            }
        }
    }



}


