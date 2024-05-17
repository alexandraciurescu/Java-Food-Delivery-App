package model;

public class Fries extends Product{

    private String type;
    private String size;
    private String topping;

    public Fries()
    {
        super("",0,"");
        this.type="";
        this.size="";
        this.topping="";
    }

    public Fries(String name, double price, String desc, String type, String size, String t) {
        super(name, price, desc);
        this.type = type;
        this.size=size;
        this.topping=t;
    }

    public Fries(Product product)
    {
        super(product.name, product.price, product.description);
    }
    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Fries" + '\n' +
                "name = " + name + "\n" +
                "price = " + price + "\n" +
                "description = " + description + "\n" +
                "type = " + type + '\n' +
                "size = " + size + '\n' +
                "topping = " + topping + '\n'
                ;
    }
}
