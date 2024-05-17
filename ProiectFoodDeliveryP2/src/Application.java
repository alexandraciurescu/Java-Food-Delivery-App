import java.sql.SQLException;
import java.util.Scanner;
import service.*;

public class Application {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int mode; // 1- client mode; 2- admin mode
        PersonService personService = new PersonService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        while (true) {
            System.out.println("Welcome to Patty Planet!");
            System.out.println("Choose your option: ");
            System.out.println("1. I am a client");
            System.out.println("2. I am the admin");
            mode = scanner.nextInt();
            scanner.nextLine();
            if (mode == 1) {
                while (true) {
                    menu1();
                    int command = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Command received: " + command);
                    if (command == 9) break;
                    if (command == 10) {System.out.println("Exiting..."); return;}
                    switch1(scanner,personService,productService,orderService,mode,command);
                }
            } else if (mode == 2) {
                while (true) {
                    menu2();
                    int command = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Command received: " + command);
                    if (command == 10) break;
                    if (command == 11) {System.out.println("Exiting..."); return;}
                    switch2(scanner,personService,productService,orderService,mode,command);
                }
            }
        }
    }

    private static void menu1() {
        System.out.println("Available commands: ");
        //System.out.println("1. Make an order");
        System.out.println("1. Create profile");
        System.out.println("2. Search for client");
        System.out.println("3. Update your data");
        System.out.println("4. Delete my account");
        System.out.println("5. Make an order");
        System.out.println("6. Review my order details");
        System.out.println("7. Cancel my order");
        System.out.println("8. Rate a driver");
        System.out.println("9. Switch user mode");
        System.out.println("10. Exit");
        System.out.println("Enter command:");
    }

    private static void menu2() {
        System.out.println("Available commands: ");
        System.out.println("1. Add new delivery driver");
        System.out.println("2. Search for delivery driver");
        System.out.println("3. Update driver's data");
        System.out.println("4. Delete driver");
        System.out.println("5. Add new product");
        System.out.println("6. Search for product");
        System.out.println("7. Update product");
        System.out.println("8. Delete product");
        System.out.println("9. Show driver list sorted by rating");
        System.out.println("10. Switch user mode");
        System.out.println("11. Exit");
        System.out.println("Enter command:");
    }

    private static void switch1(Scanner scanner, PersonService personService, ProductService productService, OrderService orderService, int mode, int command) throws SQLException {
        switch (command) {
            case 1:
                personService.create(scanner, mode);
                break;
            case 2:
                personService.read(scanner, mode);
                break;
            case 3:
                personService.update(scanner, mode);
                break;
            case 4:
                personService.delete(scanner, mode);
                break;
            case 5:
                orderService.create(scanner);
                break;
            case 6:
                orderService.read(scanner);
                break;
            case 7:
                orderService.delete(scanner);
                break;
            case 8:
                personService.giveRatingtoDriver(scanner);
                break;
            case 9:
                break;

            default:
                System.out.println("Wrong command");
        }


    }

    private static void switch2(Scanner scanner, PersonService personService, ProductService productService, OrderService orderService, int mode, int command) throws SQLException {
        switch (command) {
            case 1:
                personService.create(scanner, mode);
                break;
            case 2:
                personService.read(scanner, mode);
                break;
            case 3:
                personService.update(scanner, mode);
                break;
            case 4:
                personService.delete(scanner, mode);
                break;
            case 5:
                productService.create(scanner);
                break;
            case 6:
                productService.read(scanner);
                break;
            case 7:
                productService.update(scanner);
                break;
            case 8:
                productService.delete(scanner);
                break;
            case 9:
                personService.DriverRatingSort();
                break;
            case 10:
                break;
            default:
                System.out.println("Wrong command");
        }
    }

}
