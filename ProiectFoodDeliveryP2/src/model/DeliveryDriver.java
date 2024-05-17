package model;

import java.util.List;

public class DeliveryDriver extends Person{
    //private List<Order> orders;
    private double rating;
    private String vehicle;


    public DeliveryDriver(Person person) {
        super(person.getName(), person.getPhone(), person.getEmail());
    }

    public DeliveryDriver()
    {super(); //this.orders=new ArrayList<>();
        this.rating=0;
        this.vehicle="";
    }

    public DeliveryDriver(String name, String phone, String email, List<Order> orders, String vehicle)
    {   super(name,phone,email);
        //this.orders=orders;
        this.rating=0;
        this.vehicle=vehicle;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }



    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "DeliveryDriver" +  "\n" +
                " name = " + getName() + "\n" +
                " phoneNumber = " + getPhone() + "\n" +
                " emailAddress = " + getEmail() + "\n" +
                " rating = " + getRating() + "\n" +
                " vehicle = " + getVehicle() + "\n"
                ;
    }
}
