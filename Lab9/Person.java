public abstract class Person {
    private String firstName;
    private String lastName;
    private int id;
    // Put yes beside the methods that should be abstract
    // public Person(String firstName, String lastName, int id) [constructor] //No, we can super this and still make our own constructor.
    // public String getFirstName() //no
    // public String getLastName() //no
    // public int getId() //no
    // public void display() //yes

    public Person(String f, String l, int id)
    {
        firstName = f;
        lastName = l;
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getId()
    {
        return id;
    }

    public abstract void display();

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        {
            return false;
        }

        if (this.getClass() != obj.getClass())
        {
            return false;
        }

        return id == ((Person)obj).getId();
    }
}
