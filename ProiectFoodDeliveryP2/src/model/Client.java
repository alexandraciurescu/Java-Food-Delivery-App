package model;

import java.util.List;

public class Client extends Person{
    private String address;
    private String card;

    public Client(Person person) {
        super(person.getName(), person.getPhone(), person.getEmail());
    }

    public Client()
    {
        super();
        this.address="";
        this.card="";
        //this.orders=new ArrayList<>();
    }
    public Client(String name, String phone, String email, String address, String cards) {
        super(name, phone, email);
        this.address = address;
        this.card=cards;
        //this.orders=orders;
    }

    public String getAddress() {
        return address;
    }

    public String getCard() {
        return card;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Client:" + "\n" +
                "name = " + getName() + "\n" +
                "phoneNumber =" + getPhone() + "\n" +
                "emailAddress =" + getEmail() + "\n" +
                "adress =" + getAddress() + "\n" +
                "card =" + getCard() + "\n" ;
    }
}
