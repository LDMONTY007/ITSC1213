public class DVD extends Product {
    private String director; //the director
    private String leadActor; //the lead actor

    public DVD(String name, double price, String director, int stock)
    {
        super(name, price, stock);
        this.director = director;
    }
    
    public DVD(String name, double price, String director, String leadActor, int stock) {
        super(name, price, stock);
        this.director = director;
        this.leadActor = leadActor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadActor() {
        return leadActor;
    }

    public void setLeadActor(String leadActor) {
        this.leadActor = leadActor;
    }

    @Override
    public String toString()
    {
        //I was playing with Java's version of the ternary operator to learn how it worked as it may be useful in later projects. I love making code as compact as possible.
        return this.getName() + ", is $" + this.getPrice() + "\nDirected by:" + ((leadActor == "") ? this.director : this.director + "Featuring " + this.leadActor + "!") 
                + ", Stock: " + stock;
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
        }
        else if (this.getPrice() > o.getPrice())
        {
            return 1;
        }
        else if (this.getPrice() < o.getPrice())
        {
            return -1;
        }
        return 0; // 0 if they are equal.
    }
}
