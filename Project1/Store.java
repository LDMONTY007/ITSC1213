import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will act as my test harness for this project.
 */
public class Store {

    public static ArrayList<Member> members = new ArrayList<>()
    {
        {
        add(new Member("LD"));
        add(new Member("John"));
        add(new Member("Laurel"));
        add(new Member("Johunga"));
        add(new Member("Juno"));
        }
    };//initialize this with some members by default so we can test this properly.
    public static ArrayList<Product> products = new ArrayList<>()
    {
        {
            add(new CD("2112", 14.99));
            add(new CD("Moving Pictures", 12.99));
            add(new Book("Alice in Wonderland", 10.99, "Lewis Carroll"));
            add(new Book("Lord of the Rings", 24.99, "J.R.R. Tolkien"));
        }
    }; //allocate 50 slots so we don't start lagging when adding more products.
    
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean shouldExit = false; //sentinel for our loop

        Member currentMember = members.get(0);

        while (!shouldExit) //loop unless told otherwise.
        {
            System.out.println("Current Member: " + currentMember.getName());
            System.out.println("************MENU************"); //our menu GUI
            System.out.println("1. Make Purchase");
            System.out.println("2. Upgrade to Premium Member");
            System.out.println("3. Checkout");
            System.out.println("4. Switch to different Member");
            System.out.println("5. Create new member");
            System.out.println("6. Print member info");
            System.out.println("0. Exit.");
            System.out.println("****************************");

            int selection = LDUtil.GetNextOfType(scnr, int.class); //if the user doesn't input the datatype we want prompt them for the correct type.

            switch (selection) { //Logic for our menu selection switching.
                case 0: //0. Exit
                {
                    shouldExit = true;
                    //Print our data.
                    break;
                }
                case 1: //1. Make Purchase
                {
                    System.out.println("********MAKE**PURCHASE*******");
                    printProductMenu(); //print our menu so the user knows the id's they can select
                    System.out.println("What would you like to purchase " + currentMember.getName() + "?"); //prompt user
                    int input = LDUtil.GetNextOfType(scnr, int.class, 1, products.size()); //get user input while restricting to the size of the list and error checking.
                    for (Product p : products) { //loop thorugh products and add that product to the current members array. I.E. add the product to the members cart.
                        if (p.getId() == input)
                        {
                            currentMember.currentProducts.add(p);
                        }
                    }
                    System.out.println("Your current total is: $" + LDUtil.RoundPoints(
                            currentMember.getCurrentTotal(), 2)); //print total without extra zeros.
                    System.out.println("****************************");        
                    break;
                }
                case 2: //2. Upgrade to Premium Member
                {
                    System.out.println("****UPGRADE**TO**PREMIUM****");
                    System.out.println("Upgrading to premium member...");
                    if (!currentMember.isPremium()) //if they aren't a premium member execute the code to make them one.
                    {
                        currentMember.setPremium(true); //set their premium bool to true
                        System.out.println("Done!"); //inform them they have been upgraded to a premium member.
                        currentMember.currentProducts.add(new Product("Premium Membership fee", 15.49)); //add the premium fee.
                    }
                    else //inform the user they are already a premium member
                    {
                        System.out.println("This member is already a premium member!");
                    }
                    System.out.println("****************************");
                    break;
                }
                case 3: //3. Checkout
                {
                    System.out.println("**********CHECKOUT**********");
                    currentMember.setMoneySpent(currentMember.getMoneySpent() + currentMember.getCurrentTotal()); //add to our members total amount spent
                    System.out.println(currentMember.currentProducts.toString()); //print everything in their cart.
                    System.out.println("Total amount payed: $" + LDUtil.RoundPoints(currentMember.getCurrentTotal(), 2)); //print their total
                    currentMember.currentProducts.clear(); //clear their cart.
                    currentMember.getCurrentTotal(); //this will set the current total back to zero as there are no products.
                    System.out.println("****************************");
                    break;
                }
                case 4: //4. Switch to different Member
                {
                    System.out.println("*******SWITCH**MEMBER*******");
                    System.out.println("Please enter the member id");
                    printMemberMenu(); //print our members
                    int input = LDUtil.GetNextOfType(scnr, int.class, 0, members.size()); //get the users input restricted to the amount of members we have.
                    currentMember = getMember(input); //assign new member
                    System.out.println("Successfully switched member to " + currentMember.getName()); //inform user
                    System.out.println("****************************");
                    break;
                }
                case 5: //5. Create new Member
                {
                    System.out.println("*******CREATE**MEMBER*******");
                    createNewMember();
                    System.out.println("****************************");
                    break;
                }
                case 6: //6. print member info
                {
                    System.out.println("*********USER**INFO*********");
                    System.out.println(currentMember.getName()); //print name
                    if (currentMember.currentProducts.size() > 0) //check we have products before printing a list.
                    System.out.println(currentMember.currentProducts.toString() + " = $" + LDUtil.RoundPoints(currentMember.getCurrentTotal(), 2)); //print products and current total
                    System.out.println("Total Spent: " + LDUtil.RoundPoints(currentMember.getMoneySpent(), 2)); //print the total amount spent so far.
                    if (currentMember.isPremium()) //check if they are premium and if they are print this data.
                    System.out.println("Premium Member");
                    System.out.println("****************************");
                }
                default: //If they enter something incorrect.
                {
                    System.out.println("***********ERROR************");
                    System.out.println("Please Choose a valid option within the menu");
                    System.out.println("****************************");
                    break;
                }
            }
        }

        scnr.close();
    }

    /**
     * Gets a member object from our store's list of members given an id
     * @param id the id to search for
     * @return the Member object.
     */
    public static Member getMember(int id)
    {
        for (Member m : members) {
            if (m.getId() == id) //if our id matches the member's id return this member.
            {
                return m;
            }
        }
        return createNewMember(); //if we don't have this member create them.
    }

    /**
     * Prints all of our members in a list format with their corresponding id so we can use it as a menu.
     */
    public static void printMemberMenu()
    {
        for (Member m : members)
        {
            System.out.println(m.getId() + ". " + m.getName());
        }
    }

    /**
     * Creates a new member and adds it to our members list.
     * @return the new member object.
     */
    public static Member createNewMember()
    {
        Scanner scnr = new Scanner(System.in); 
        System.out.print("Please enter the new Member's name: ");
        String name = scnr.next(); //get members new name
        scnr.close(); //close scanner to prevent memory issues.
        Member m = new Member(name); //instantiate member
        members.add(m); //add member to our list of members
        return m; //return member
    }

    /**
     * prints products in list format with corresponding ids so that they can be selected in a menu.
     */
    public static void printProductMenu()
    {
        System.out.println("************MENU************");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.toString());
        }
        System.out.println("****************************");
    }
}
