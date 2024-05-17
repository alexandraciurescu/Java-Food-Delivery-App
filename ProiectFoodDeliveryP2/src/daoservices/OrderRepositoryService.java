package daoservices;

import dao.ClientDAO;
import dao.DeliveryDriverDAO;
import dao.OrderDAO;
import model.Client;
import model.DeliveryDriver;
import model.Order;
import model.Person;

import static util.Constants.DRIVER;

public class OrderRepositoryService {

    private OrderDAO orderDAO;

    public OrderRepositoryService() {
        this.orderDAO = new OrderDAO();
    }

    public Order getOrderById(int id){
        Order order = orderDAO.read(id);
        if(order != null){
            System.out.println(order);
        }else {
            System.out.println("No order having this id...");
        }

        return order;
    }


    public void removeOrder(int id) {
        Order order = getOrder(id);
        if (order == null) return;

        orderDAO.delete(order);

        System.out.println("Removed " + order);
    }

    public void addOrder(Order order) {
        if(order != null)
            orderDAO.create(order);
    }

    public Order getOrder(int id) {
        Order order;
        order = getOrderById(id);

        if(order == null) {
            System.out.println("No order having id " + id);
            return null;
        }
        return order;
    }
}
