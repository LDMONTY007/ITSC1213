
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //System.out.println("*** Part A ***");

        //Person p = new Person("Raya", "Whitaker", 800555555);
        //p.display();

        //System.out.println("*** Part A ***");
        Student s1 = new Student("Xavier", "Cato", 900111222, "CS", 3.5, 75);
        // s1.setTransfer(true);
        // s1.setBalance(100);
        // s1.addCourse("Java Programming");
        // s1.addCourse("Calculus");

        //s1.display();

        // if (s1.dropCourse("Spanish"))
        // {
        //     System.out.println("The class has been dropped");
        // }
        // else
        // {
        //     System.out.println("You are not enrolled in this course. " + "No courses have been dropped.");
        // }

        // ArrayList<String> enrolledCourses = s1.getEnrolledCourses();
        // System.out.println("You are enrolled in the following courses:");
        // for (String course : enrolledCourses)
        // {
        //     System.out.println(course);
        // }
        
        //System.out.println("*** Part B ***");

        Student s2 = new Student("Kathrine", "Johnson", 900, "CS", 4.0, 100);
        Student s3 = new Student("Roy", "Clay", 901, "Biology", 3.2, 85);
        Student s4 = new Student("Kimberly", "Bryant", 902, "EE", 3.0, 80);

        Professor prof1 = new Professor("Mary", "Castro", 300, "CS", 800000);

        // prof1.addAdvisee(s2);
        // prof1.addAdvisee(s3);
        // prof1.addAdvisee(s4);

        // prof1.display();
        
        // System.out.println("*** Part C ***");
        // System.out.println(s1);
        // System.out.println(prof1);

        // System.out.println("s1.equals(s4)");
        // System.out.println(s1.equals(s4));
        
        // Student s5 = new Student("Xavier", "Cato", 900111222, "CS", 3.5, 75);

        // System.out.println("s1.equals(s5)");
        // System.out.println(s1.equals(s5));

        // System.out.println("s1.equals(prof1)");
        // System.out.println(s1.equals(prof1));

        // System.out.println("*** Part D ***");

        // Person[] personArray = {s1, s2, prof1};

        // ArrayList<Person> personList = new ArrayList(3);
        // personList.add(s1);
        // personList.add(s2);
        // personList.add(prof1);

        // System.out.println("Content of personList:");
        
        // for (Person person : personList)
        // {
        //     System.out.println(person);
        // }
        
        //PART A
        System.out.println("*** Part A Lab 8***");
        ArrayList<Person> contactList = new ArrayList<Person>(50);

        //contactList.add(p);
        contactList.add(s1);
        contactList.add(s2);
        contactList.add(s3);
        contactList.add(s4);
        contactList.add(prof1);

        //Person p2 = new Person("Elle", "Kambol", 800);
        //contactList.add(p2);

        Professor prof2 = new Professor("Frank", "Black", 801, "Math", 85000);
        contactList.add(prof2);

        Student s5 = new Student("Grace", "Maxeem", 903, "Psychology", 3.4, 95);
        contactList.add(s5);

        for (Person person : contactList) {
            person.display();
        }

        System.out.println("*** Part B Lab 8 ***");

        for (Person person : contactList) {
            showProfile(person, 903);
        }

        System.out.println("*** Part C Lab 8 ***");

        Person p3 = new Student("Maya", "Adams", 700, "Music", 3.5, 105);
        System.out.println(((Student)p3).getGpa());

        //Person p4 = new Person("Bob", "Lowe", 701);
        // if (p4 instanceof Student)
        // {
        //     System.out.println(((Student)p4).getGpa());
        // }

        System.out.println("Students eligible for a scholarship:");
        for (Person person : contactList) {
            if (person instanceof Student)
            {
                if (((Student)person).getGpa() > 3.5)
                {
                    System.out.println("\t" + person.getFirstName() + " " + person.getLastName());
                }
            }
        }

        System.out.println("Professors in the CS department:");
        for (Person person : contactList)
        {
            if (person instanceof Professor)
            {
                if (((Professor)person).getDepartment() == "CS")
                {
                    System.out.println("\t" + person.getFirstName() + " " + person.getLastName());
                }
            }
        }

        System.out.println("*** Part C Lab 9 ***");
        int compareStudents = s4.compareTo(s1);
        System.out.println(compareStudents);

        ArrayList<Student> students = new ArrayList<>(50);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        Collections.sort(students);
        for (Student s : students)
        {
            System.out.println(s.getGpa());
        }
        System.out.println("*** BONUS ***");
        System.out.println(prof1.compareTo(prof2)); //BONUS

        // if (prof1.removeAdvisee(902))
        // {
        //     System.out.println("This advisee has been removed");
        // }
        // else
        // {
        //     System.out.println("This student is not an advisee of Professor " + prof1.getFirstName() + " " + prof1.getLastName());
        // }

        // ArrayList<Student> myAdvisees = prof1.getAdvisees();
        // System.out.println("Professor " + prof1.getFirstName() + " " + prof1.getLastName() + " advisees:");
        // for (Student s : myAdvisees)
        // {
        //     System.out.println(s.getFirstName() + " " + s.getLastName());
        // }

    }

    public static void showProfile(Person p, int id)
    {
        if (p.getId() == id)
        {
            p.display();
        }
    }
}
