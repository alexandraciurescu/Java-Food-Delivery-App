package service;

import dao.DeliveryDriverDAO;
import dao.OrderDAO;
import daoservices.PersonRepositoryService;
import model.Client;
import model.DeliveryDriver;
import model.Order;
import model.Person;
import util.DriverComparator;
import util.FileManagement;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static util.Constants.*;

public class PersonService {

    private PersonRepositoryService databaseService;


    public PersonService() throws SQLException {
        this.databaseService = new PersonRepositoryService();
    }

    public void create(Scanner scanner, int mode) {

        try{ personInit(scanner, mode);
        } catch (SQLException e) {
            System.out.println("Cannot create person " + e.getSQLState() + " " + e.getMessage());
        }
    }

    public void read(Scanner scanner, int mode) {
        System.out.println("Name:");
        String name = scanner.nextLine();
        try{
            if(mode==1) {databaseService.getClientByName(name);
                FileManagement.scriereFisierChar(AUDIT_FILE, "citire client " + name);}
            else if(mode==2) {databaseService.getDriverByName(name);
                FileManagement.scriereFisierChar(AUDIT_FILE, "citire delivery driver " + name);}
        }
        catch (SQLException e) {
            System.out.println("Persoana nu se poate gasi " + e.getSQLState() + " " + e.getMessage());
        }
    }


    public void delete(Scanner scanner,int mode) {
        System.out.println("Name:");
        String name = scanner.nextLine();

        String typeOfPerson="";
        if(mode==1) typeOfPerson=CLIENT;
        else if(mode==2) typeOfPerson=DRIVER;
        try {
            databaseService.removePerson(typeOfPerson, name);
            FileManagement.scriereFisierChar(AUDIT_FILE, "stergere persoana " + name);
        } catch (SQLException e) {
            System.out.println("Cannot delete person " + e.getSQLState() + " " + e.getMessage());
        }
    }



    public void update(Scanner scanner, int mode) {
        String typeOfPerson="";
        if(mode==1) typeOfPerson=CLIENT;
        else if(mode==2) typeOfPerson=DRIVER;
        System.out.println("Name: ");
        String name = scanner.nextLine();

        Person person = databaseService.getPerson(typeOfPerson, name);
        if (person == null) { return;}

        Person personGeneralInfo = setGeneralInfo(name, scanner);
        person.setName(name);
        person.setPhone(personGeneralInfo.getPhone());
        person.setEmail(personGeneralInfo.getEmail());

        if(typeOfPerson.equals(DRIVER)){
            driverInit(scanner, (DeliveryDriver) person);
        }else{
            clientInit(scanner, (Client) person);
        }

        try {
            databaseService.update(person);
            FileManagement.scriereFisierChar(AUDIT_FILE, "update persoana " + person.getName());
        }catch (SQLException e){
            System.out.println("Cannot update person " + e.getSQLState() + " " + e.getMessage());
        }
    }


    private void personInit(Scanner scanner, int mode) throws SQLException{
        System.out.println("Name: ");
        String name = scanner.nextLine();
        if (mode==2 && databaseService.getDriverByName(name) != null) {return;}
        if (mode==1 && databaseService.getClientByName(name) != null) {return;}
        Person person = setGeneralInfo(name, scanner);
        if(mode==2){
            DeliveryDriver driver = new DeliveryDriver(person);
            driverInit(scanner, driver);
            person = driver;
        } else {
            Client client = new Client(person);
            clientInit(scanner, client);
            person = client;
        }
        try {
            databaseService.addPerson(person);
            System.out.println("Created " + person);
            FileManagement.scriereFisierChar(AUDIT_FILE, "add persoana " + person.getName());
        } catch (SQLException e) {
            System.out.println("Cannot create " + person + " exception " + e.getSQLState() + " " + e.getMessage());
        }
    }

    private Person setGeneralInfo(String name, Scanner scanner) {
        System.out.println("Phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        return new Person(name, phoneNumber, email);
    }

    private void clientInit(Scanner scanner, Client client) {
        System.out.println("Address:");
        String clientAddress = scanner.nextLine();
        System.out.println("Card:");
        String clientCard = scanner.nextLine();

        client.setAddress(clientAddress);
        client.setCard(clientCard);
    }

    private void driverInit(Scanner scanner, DeliveryDriver driver) {

        System.out.println("Vehicle: ");
        String vehicle = scanner.nextLine();
        driver.setVehicle(vehicle);

    }

    public void giveRatingtoDriver(Scanner scanner) throws SQLException {
        /*
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        try { Client c = databaseService.getClientByName(name);
        } catch (SQLException e) {
            System.out.println("You are not one of our clients...Please make an account first!");
        }
        */
        System.out.println("What is the driver's name?");
        String driverName = scanner.nextLine();
        DeliveryDriver d = null;
        try { d = databaseService.getDriverByName(driverName);
        } catch (SQLException e) {
            System.out.println("There is no driver having this name...");
        }
        int nrComenzi = 0;
        List<Order> orders = OrderDAO.getOrders();
        for (Order o : orders)
            if (o.getDriver().getName().equals(d.getName())) nrComenzi++;
        if (nrComenzi == 0) System.out.println("There have been no deliveries carried out by " + driverName);
        else {
            System.out.println("What is your rating? [1-5 stars]");
            double review = scanner.nextDouble();
            scanner.nextLine();
            d.setRating((d.getRating() + review) / nrComenzi);
            DeliveryDriverDAO driverDAO = DeliveryDriverDAO.getInstance();
            driverDAO.update(d);
        }
    }


    public void DriverRatingSort() throws SQLException {
        OrderService oS = new OrderService();
        if(oS.getDeliveryDrivers().isEmpty())
           {System.out.println("You have not introduced any drivers yet..."); return;}
       Collections.sort(oS.getDeliveryDrivers(), new DriverComparator());
       for(DeliveryDriver d: oS.getDeliveryDrivers())
           System.out.println(d + "\n");
   }

}
