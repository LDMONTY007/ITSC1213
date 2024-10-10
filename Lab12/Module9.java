import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Module9 {
    public static void main(String[] args)
    {
        Scanner fileScanner;
        try 
        {
            fileScanner = new Scanner(new File("inputData.txt"));
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Caught FileNotFoundException, File could not be found. Please ensure your syntax is correct and that your file name and path match..");
            return; //return if we don't have a scanner.
        }
        //Part A
        // while (fileScanner.hasNext())
        //     System.out.println(fileScanner.nextLine());

        // fileScanner.close();

        //Part B
        System.out.println("PART B:");
        ArrayList<Student> students = new ArrayList<>(50);
        
            
            while (fileScanner.hasNext())
            {
                String[] segments = fileScanner.nextLine().split(","); //split into array of strings.
                if (segments[0].contains("student"))
                {
                    try
                    {
                        Student s = new Student(segments[1], segments[2], Integer.parseInt(segments[3]), segments[4], Double.parseDouble(segments[5]), Integer.parseInt(segments[6]));
                        students.add(s);
                    }
                    catch (ArrayIndexOutOfBoundsException ex)
                    {
                        System.out.println("Caught ArrayIndexOutOfBoundsException, incorrect data input, expected 6 arguments per line");
                        return;
                    }
                }
            }

            System.out.println("UNSORTED");
            for (Student student : students) {
                student.display();
            }

            Collections.sort(students); //Sort our list by GPA
            System.out.println("SORTED");
            for (Student student : students) {
                student.display();
            }

            fileScanner.close();
        
        try
        {
            FileOutputStream fs = new FileOutputStream("ScholarshipCandidates.txt");
            PrintWriter outFS = new PrintWriter(fs);

            outFS.println("***************************************************");
            outFS.println("Our top candidates for the scholarship are: ");
            outFS.println("***************************************************");
            for (int i = 0; i < students.size(); i++)
            {
                if (students.get(i).getGPA() >= 3.5)
                    outFS.println(students.get(i).getFirstName() + " " + students.get(i).getLastName() + " " + students.get(i).getId() + " " + students.get(i).getMajor() + " " + students.get(i).getGPA() + " " + students.get(i).getCredits());
                else if (students.get(i).getGPA() >= 3.2 && (i-1) >= 0 && students.get(i - 1).getGPA() >= 3.5) //only print the divider where the previous student and the current student are in different divisions.
                {
                    outFS.println("***************************************************");
                    outFS.println("Our middle candidates for the scholarship are: ");
                    outFS.println("***************************************************");
                    outFS.println(students.get(i).getFirstName() + " " + students.get(i).getLastName() + " "
                            + students.get(i).getId() + " " + students.get(i).getMajor() + " " + students.get(i).getGPA()
                            + " " + students.get(i).getCredits());
                }
                else if (students.get(i).getGPA() >= 3.2 && (i-1) >= 0 && students.get(i - 1).getGPA() < 3.5) //print without divider when the previous student and current student are in the same division.
                {
                    outFS.println(students.get(i).getFirstName() + " " + students.get(i).getLastName() + " "
                            + students.get(i).getId() + " " + students.get(i).getMajor() + " " + students.get(i).getGPA()
                            + " " + students.get(i).getCredits());
                }
                else if (students.get(i).getGPA() < 3.2 && (i - 1) >= 0 && students.get(i - 1).getGPA() >= 3.2) {
                    outFS.println("***************************************************");
                    outFS.println("Our ineligible candidates for the scholarship are: ");
                    outFS.println("***************************************************");
                    outFS.println(students.get(i).getFirstName() + " " + students.get(i).getLastName() + " "
                            + students.get(i).getId() + " " + students.get(i).getMajor() + " " + students.get(i).getGPA()
                            + " " + students.get(i).getCredits());
                }
                else if (students.get(i).getGPA() < 3.2 && (i - 1) >= 0 && students.get(i - 1).getGPA() < 3.2)
                {
                    outFS.println(students.get(i).getFirstName() + " " + students.get(i).getLastName() + " "
                            + students.get(i).getId() + " " + students.get(i).getMajor() + " " + students.get(i).getGPA()
                            + " " + students.get(i).getCredits());
                }

            }


            outFS.close();
            fs.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(
                    "Caught FileNotFoundException, File could not be found. Please ensure your syntax is correct and that your file name and path match..");
        }
        catch (IOException ex)
        {
            System.out.println(
                    "Caught IOException, IO failed or was Interrupted during fileStream. Please try again.");
        }
    }
}
