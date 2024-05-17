package model;

public class Milkshake extends Product{

    private String flavour;
    private String topping;
    private boolean whippingCream;

    public Milkshake()
    {
        super("",0,"");
        this.flavour = "";
        this.topping= "";
        this.whippingCream = false;

    }

    public Milkshake(String name, double price, String desc, String flavour, String t, boolean w) {
        super(name, price, desc);
        this.flavour = flavour;
        this.topping= t;
        this.whippingCream = w;

    }

    public Milkshake(Product product)
    {
        super(product.name, product.price, product.description);
    }

    public String getTopping() {
        return topping;
    }

    public boolean isWhippingCream() {
        return whippingCream;
    }

    public void setWhippingCream(boolean whippingCream) {
        this.whippingCream = whippingCream;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    //public String getSize() {
    //return size;
    //}

    //public void setSize(String size) {
    //  this.size = size;
    //}

    @Override
    public String toString() {
        return "Milkshake" + "\n" +
                "name = " + name + "\n" +
                "price = " + price + "\n" +
                "description = " + description + "\n" +
                "flavour = " + flavour + "\n" +
                "topping = " + topping + "\n" +
                "whippingCream = " + whippingCream
                ;
    }
}
