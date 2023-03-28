import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        boolean sentinel = false;
        Scanner scnr = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<User>(60);

        while (sentinel == false)
        {
            System.out.println("Class outside? Y/N");
            String inputVote = LDUtil.GetNextOfType(scnr, String.class);
            while (!inputVote.toLowerCase().equals("y") && !inputVote.toLowerCase().equals("n"))
            {
                System.out.println("Please enter the correct value (y/n)");
                inputVote = LDUtil.GetNextOfType(scnr, String.class);
            }
            System.out.println("Please enter your name: ");
            String inputName = LDUtil.GetNextOfType(scnr, String.class);
            if (inputVote.toLowerCase().contains("y"))
                users.add(new User(inputName, true));
            else if (inputVote.toLowerCase().contains("n"))
            {
                users.add(new User(inputName, false));
            }
            System.out.println("Vote added, would you like to calculate the vote? (Y/N)");
            String userInput = LDUtil.GetNextOfType(scnr, String.class);
            while (!userInput.toLowerCase().equals("y") && !userInput.toLowerCase().equals("n")) {
                System.out.println("Please enter the correct value (y/n)");
                userInput = LDUtil.GetNextOfType(scnr, String.class);
            }
            if (userInput.toLowerCase().contains("y"))
            {
                sentinel = true;
            }
        }

        int yTotal = 0;
        int nTotal = 0;
        for (User u : users)
        {
            if (u.didVoteYes)
            {
                yTotal++;
            }
            else
            {
                nTotal++;
            }
        }
        System.out.println("Total Yes : " + yTotal + " : " +  LDUtil.RoundPoints((((double) yTotal)
                / ((double) users.size())) * 100, 2) + "%");
        System.out.println("Total No : " + nTotal + " : " +  LDUtil.RoundPoints((((double) nTotal)
                / ((double) users.size())) * 100, 2) + "%");
    }
}