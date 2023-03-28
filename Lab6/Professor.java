import java.util.ArrayList;

public class Professor extends Person {
    private String department;
    private double salary;
    private ArrayList<Student> advisees;

    public Professor(String f, String l, int id, String d, double s)
    {
        super(f, l, id);
        department = d;
        salary = s;
        advisees = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public ArrayList<Student> getAdvisees() {
        return advisees;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void addAdvisee(Student s)
    {
        advisees.add(s);
    }

    public boolean removeAdvisee(int id)
    {
        for (Student s : advisees) {
            
            if (s.getId() == id)
            {
                advisees.remove(s);
                return true;
            }
        }
        return false;
    }

}
