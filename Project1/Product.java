public class Product
{
    public static int nextId = 1; //static nextId to be used to increment product ids automaticall upon instantiation.
    private int id; //product id
    private String name; //name of this product
    private double price; //price of this product


    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
        this.id = nextId; //set our id
        nextId++; //increment next id
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String toString()
    {
        return this.name + ", is $" + this.price;
    }
}