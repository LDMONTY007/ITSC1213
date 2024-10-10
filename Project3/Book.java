public class Book extends Product {
    private String author; //author of this book.

    public Book(String name, double price, String author, int stock)
    {
        super(name, price, stock);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return this.getName() + ", written by " + author + ", is $" + this.getPrice() + ", Stock: " + stock;
    }

    /**
     * 
     * @return negative value if this product's price is less than the compared
     *         product, positive if this products price is greater than the compared
     *         product, 0 if they are equal.
     */
    public int compareTo(Product o) {
        if (o.getPrice() == this.getPrice()) {
            return 0;
        } else if (this.getPrice() > o.getPrice()) {
            return 1;
        } else if (this.getPrice() < o.getPrice()) {
            return -1;
        }
        return 0; // 0 if they are equal.
    }
}
