package model;

public class Burger extends Product{

    private String typeOfMeat;
    private String sauce;
    private Boolean cheese;

    public Burger(){
        super("",0,"");
        this.typeOfMeat="";
        this.sauce="";
        this.cheese=false;
    }

    public Burger(String name, double price, String desc, String typeOfMeat, String sauce, Boolean cheese) {
        super(name, price, desc);
        this.typeOfMeat = typeOfMeat;
        this.sauce=sauce;
        this.cheese=cheese;
    }

    public Burger(Product product)
    {
        super(product.name, product.price, product.description);
    }


    public String getTypeOfMeat() {
        return typeOfMeat;
    }

    public void setTypeOfMeat(String typeOfMeat) {
        this.typeOfMeat = typeOfMeat;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public Boolean getCheese() {
        return cheese;
    }

    public void setCheese(Boolean cheese) {
        this.cheese = cheese;
    }

    @Override
    public String toString() {
        return "Burger" + "\n" +
                "name = " + name + "\n" +
                "price = " + price + "\n" +
                "description = " + description + "\n" +
                "typeOfMeat = " + typeOfMeat + "\n" +
                "sauce = " + sauce + "\n" +
                "cheese = " + cheese
                ;
    }
}
