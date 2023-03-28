public class Person {
    private String firstName;
    private String lastName;
    private int id;

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

    public void display()
    {
        System.out.println("Name: " + firstName + " " + lastName + "\nID: " + id);
    }
}
