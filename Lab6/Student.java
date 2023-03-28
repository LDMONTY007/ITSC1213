import java.util.ArrayList;

public class Student extends Person {
    private String major;
    private double gpa;
    private int credits;
    private double balance;
    private boolean transfer;
    private ArrayList<String> enrolledCourses;

    public Student(String f, String l, int id, String major, double gpa, int credits)
    {
        super(f, l, id);
        this.major = major;
        this.gpa = gpa;
        this.credits = credits;
        balance = 0;
        enrolledCourses = new ArrayList<>();
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addCourse(String c)
    {
        enrolledCourses.add(c);
    }

    public boolean dropCourse(String c)
    {
        if (enrolledCourses.contains(c))
        {
            enrolledCourses.remove(c);
            return true;
        }
        else
        return false;
    }
}
