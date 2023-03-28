public class DVD extends Product {
    private String director; //the director
    private String leadActor; //the lead actor

    public DVD(String name, double price, String director)
    {
        super(name, price);
        this.director = director;
    }
    
    public DVD(String name, double price, String director, String leadActor) {
        super(name, price);
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
        return this.getName() + ", is $" + this.getPrice() + "\nDirected by:" + ((leadActor == "") ? this.director : this.director + "Featuring " + this.leadActor + "!");
    }
}
