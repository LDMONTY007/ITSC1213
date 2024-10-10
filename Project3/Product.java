public abstract class Product implements Comparable<Product>
{
    public int stock = 100; //tracking total amount of these items, default to one as we are only allowed to use methods to change a stock amount.
    public static int nextId = 1; //static nextId to be used to increment product ids automaticall upon instantiation.
    private int id; //product id
    private String name; //name of this product
    private double price; //price of this product


    public Product(String name, double price, int stock)
    {
        this.name = name;
        this.price = price;
        this.id = nextId; //set our id
        this.stock = stock; //add stock.
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

    public int getStock()
    {
        return stock;
    }

    public void setStock(int s)
    {
        stock = s;
    }

    public String toString()
    {
        return this.name + ", is $" + this.price + ", Stock: " + stock;
    }
}