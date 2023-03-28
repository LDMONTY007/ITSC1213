public class Book extends Product {
    private String author; //author of this book.

    public Book(String name, double price, String author)
    {
        super(name, price);
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
        return this.getName() + ", written by " + author + ", is $" + this.getPrice();
    }
}
