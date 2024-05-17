package dao;

import model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static List<Order> orders = new ArrayList<>();

    public static List<Order> getOrders() {
        return orders;
    }

    public void create(Order order) {orders.add(order);}

    public Order read(int id) {
        if(!orders.isEmpty()){
            for(Order o : orders){
                if(o.getId()==id){
                    return o;
                }
            }
        }
        return null;
    }


    public void delete(Order o) {
        orders.remove(o);
    }
}
