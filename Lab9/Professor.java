import java.util.ArrayList;

public class Professor extends Person implements Comparable<Professor> {
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

    @Override
    public void display() {

        System.out.println("Name: " + getFirstName() + " " + getLastName() + "\nID: " + getId());
        System.out.println("Department: " + department + "\tSalary: " + salary);
        System.out.println("Advisees:");
        for (Student s : advisees)
        {
            System.out.println("\t" + s.getFirstName() + " " + s.getLastName());
        }
    }

    @Override
    public String toString() {
        return "Professor - " + this.getFirstName() + " " + this.getLastName();
    } 

    @Override
    public int compareTo(Professor o) {
        System.out.println("Professors Salary: " + this.getSalary() + " and " + o.getSalary());
        if (this.getSalary() == o.getSalary()) {
            return 0;
        } else if (this.getSalary() > o.getSalary()) {
            return 1;
        } else if (this.getSalary() < o.getSalary()) {
            return -1;
        }
        return 0;
    }
}
