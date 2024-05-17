package model;

public class Person {
    protected String name;
    protected String phone;
    protected String email;

    public Person() {this.name=""; this.phone=""; this.email="";}

    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone=phone;
        this.email=email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    @Override
    public String toString() {
        return "Person" +
                "name = " + name + '\n' +
                "phone = " + phone + '\n' +
                "email = " + email + '\n' ;
    }
}
