import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This is my store class. 
 */
public class Store implements BookStoreSpecification {

    public String saveFileName = "UpdatedProductInventory";

    public  ArrayList<Member> members = new ArrayList<>()
    {
        {
            add(new Member("LD"));
            add(new Member("John"));
            add(new Member("Laurel"));
            add(new Member("Johunga"));
            add(new Member("Juno"));
        }
    };//initialize this with some members by default so we can test this properly.
    public  ArrayList<Product> products = new ArrayList<>(100);
    // {
    //     {
    //         add(new CD("2112", 14.99));
    //         add(new CD("Moving Pictures", 12.99));
    //         add(new Book("Alice in Wonderland", 10.99, "Lewis Carroll"));
    //         add(new Book("Lord of the Rings", 24.99, "J.R.R. Tolkien"));
    //     }
    // }; 

    public String header = ""; //The header of our inputFile.

    public ArrayList<Product> purchasedProducts = new ArrayList<>(100);
    
    public int newMemberCount = 0;

    public void Menu() throws InterruptedException 
    {
        //Parse the product inventory
        parseProductInventory();

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
            System.out.println("7. Compare Products");
            System.out.println("8. Restock Product and Display total");
            System.out.println("0. Exit.");
            System.out.println("****************************");

            int selection = LDUtil.GetNextOfType(scnr, int.class, 0, 8); //if the user doesn't input the datatype we want prompt them for the correct type.

            switch (selection) { //Logic for our menu selection switching.
                case 0: //0. Exit
                {
                    shouldExit = true;
                    System.out.println("Would you like to name the file? (y/n)");
                    String input = LDUtil.GetNextOfType(scnr, String.class);
                    if (input.equalsIgnoreCase("y"))
                    {
                        System.out.println("Please enter the filename: ");
                        input = LDUtil.GetNextOfType(scnr, String.class);

                        while (input.contains("\\") || input.contains("/") || input.contains("-") || input
                                .contains(".") || input.contains("*") || input.contains("?") || input.contains(":") || input
                                        .contains("\"") || input.contains("<") || input.contains(">"))
                        {
                            System.out.println("Please enter a filename that doesn't contain any special characters:");
                            input = LDUtil.GetNextOfType(scnr, String.class);
                        }
                        saveFileName = input;
                    }
                    generateEndOfDay(); //generate the end of day.
                    generateUpdatedInventory(); //generate new updated inventory.
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
                    Thread.sleep(750); //delay loading menu so user can read the output.
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
                        currentMember.currentProducts.add(new PremiumMembership("Premium Membership fee", 15.49)); //add the premium fee.
                    }
                    else //inform the user they are already a premium member
                    {
                        System.out.println("This member is already a premium member!");
                    }
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
                }
                case 3: //3. Checkout
                {
                    System.out.println("**********CHECKOUT**********");
                    currentMember.setMoneySpent(currentMember.getMoneySpent() + currentMember.getCurrentTotal()); //add to our members total amount spent
                    System.out.println(currentMember.currentProducts.toString()); //print everything in their cart.
                    System.out.println("Total amount payed: $" + LDUtil.RoundPoints(currentMember.getCurrentTotal(), 2)); //print their total
                    purchasedProducts.addAll(currentMember.currentProducts); //add all of their purchased products to the purchased products array.
                    currentMember.currentProducts.clear(); //clear their cart.
                    currentMember.getCurrentTotal(); //this will set the current total back to zero as there are no products.
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
                }
                case 4: //4. Switch to different Member
                {
                    System.out.println("*******SWITCH**MEMBER*******");
                    System.out.println("Please enter the member id");
                    printMemberMenu(); //print our members
                    int input = LDUtil.GetNextOfType(scnr, int.class, 1, members.size()); //get the users input restricted to the amount of members we have.
                    currentMember = getMember(input); //assign new member
                    System.out.println("Successfully switched member to " + currentMember.getName()); //inform user
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
                }
                case 5: //5. Create new Member
                {
                    System.out.println("*******CREATE**MEMBER*******");
                    createNewMember(scnr);
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
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
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
                }
                case 7://7. Compare Products
                {
                    System.out.println("******COMPARE**PRODUCT******");
                    printProductMenu(); //print our product menu
                    System.out.println("Please enter the first product id:"); //get first product id
                    Product firstProd = getProduct(LDUtil.GetNextOfType(scnr, int.class, 1, products.size()));
                    System.out.println("Please enter the product to compare to: "); //get second product id
                    Product secondProd = getProduct(LDUtil.GetNextOfType(scnr, int.class, 1, products.size()));
                    if (firstProd.compareTo(secondProd) > 0) //if compare to is greater than zero then say it is greater
                    {
                        System.out.println(firstProd.getName() + " is greater than " + secondProd.getName() + " (Output : " + firstProd
                                .compareTo(secondProd) + ")");
                    }
                    else if (firstProd.compareTo(secondProd) < 0) //if compare to is less than zero say it is less than zero
                    {
                        System.out.println(firstProd.getName() + " is less than " + secondProd.getName()
                                + " (Output : " + firstProd
                                        .compareTo(secondProd)
                                + ")");
                    }
                    else
                    {
                        System.out.println("These products are equal!"); //if they return 0 it means they are equal
                    }
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
                }
                case 8: //8. Restock Product and Display total
                {
                    System.out.println("******RESTOCK**PRODUCT******");
                    printProductMenu();
                    System.out.println("Which product would you like to restock? (enter id)"); //get product to restock
                    Product p = getProduct(LDUtil.GetNextOfType(scnr, int.class, 1, products.size())); //store product
                    System.out.println("How many would you like to add?"); //prompt user
                    int num = LDUtil.GetNextOfType(scnr, int.class);//get the next integer the user inputs
                    restockProduct(p.getId(), num); //call our restock product method.
                    System.out.println(p.toString()); //print the restocked product so the user can view it.
                    System.out.println("Inventory value: $" + LDUtil.RoundPoints(inventoryValue(), 2)); //print inventory value.
                    System.out.println("****************************");
                    Thread.sleep(750); //delay loading menu so user can read the output.
                    break;
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

        scnr.close(); //close scanner to prevent memory leaks and such. 
    }

    /**
     * Gets a product object from our store's list of products given an id
     * 
     * @param id the id to search for
     * @return the Product object.
     */
    public Product getProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) // if our id matches the products's id return this product.
            {
                return p;
            }
        }
        return null; // if we don't have this make an error.
    }

    /**
     * Gets a member object from our store's list of members given an id
     * @param id the id to search for
     * @return the Member object.
     */
    public  Member getMember(int id)
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
    public  void printMemberMenu()
    {
        for (Member m : members)
        {
            System.out.println(m.getId() + ", " + m.getName());
        }
    }

    /**
     * Creates a new member and adds it to our members list.
     * @return the new member object.
     */
    public  Member createNewMember()
    {
        Scanner sc = new Scanner(System.in); 
        System.out.print("Please enter the new Member's name: ");
        String name = sc.next(); //get members new name
        sc.close(); //close scanner to prevent memory issues.
        Member m = new Member(name); //instantiate member
        members.add(m); //add member to our list of members
        newMemberCount++;
        return m; //return member
    }

    /**
     * Creates a new member and adds it to our members list.
     * 
     * @return the new member object.
     */
    public Member createNewMember(Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the new Member's name: ");
        String name = sc.next(); // get members new name
        //sc.close(); // close scanner to prevent memory issues.
        Member m = new Member(name); // instantiate member
        members.add(m); // add member to our list of members
        newMemberCount++;
        return m; // return member
    }

    /**
     * prints products in list format with corresponding ids so that they can be selected in a menu.
     */
    public void printProductMenu()
    {
        System.out.println("************MENU************");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.toString()); //print the product including the ID so the user knows which ID corresponds to which object.
        }
        System.out.println("****************************");
    }

    @Override
    public int restockProduct(int productId, int amount) {
        Product p = getProduct(productId); //find our product by ID
        if (p == null)
        {
            return 0; //return zero if we fail.
        }
        p.setStock(p.getStock() + amount);
        return 1; //return one if we suceed.
    }

    @Override
    public double inventoryValue() {
        double value = 0; //value to return
        for (Product p : products) {
            value += (p.getStock() * p.getPrice()); //add the total stock value to the inventory value.
        }
        return value;
    }

    /**
     * Parses the product inventory file and adds the data to our products ArrayList.
     */
    public void parseProductInventory()
    {
        int lineCount = 1; //set to 1 because we skip the first line.

        File dataFile = new File("ProductInventory.csv");
        try 
        {
            Scanner scan = new Scanner(dataFile);
            header = scan.nextLine(); //skip the header and add it to the header string for later. 2 birds with one stone as it is imperative we skip it.
            while (scan.hasNext())
            {
                lineCount++;
                String[] line = scan.nextLine().split(","); //get the line as an array of strings sepereated by the "," delimeter.
                switch (line[1]) {
                    case "book": //create new Book
                        products.add(new Book(line[2], Double.parseDouble(line[5]), line[3], Integer.parseInt(line[4]))); //we do not currently have a constructor that allows you to override the ID. Implement this later.
                        break;
                    case "dvd": //create new DVD
                        products.add(new DVD(line[2], Double.parseDouble(line[5]), line[3], Integer.parseInt(line[4]))); //add dvd
                        break;
                    case "cd": //create new CD
                        products.add(new CD(line[2], Double.parseDouble(line[5]), line[3], Integer.parseInt(line[4])));
                        break;
                    case "PremiumMembership":

                        break;
                    default:
                        System.out.println("ERROR FINDING DATATYPE IN " + dataFile.getName() + " FILE, ON LINE " + lineCount);
                        break;
                }
            }

            scan.close();//close scanner.
        } 
        catch (FileNotFoundException e) //don't catch any exception, we want specifically file not found because we could cause errors and not know what they are and want to stop execution for them.
        {
            System.out.println("An Error Occured when trying to find the ProductInventory File!");
            System.out.println(e.getMessage());
        }
    }

    public void generateEndOfDay()
    {
        try 
        {
            FileOutputStream fStream = new FileOutputStream("EndOfDay.txt");
            PrintWriter fileWriter = new PrintWriter(fStream);
            
            //Print the amount of new members added
            fileWriter.println("   Members Added: " + newMemberCount);

            //Print the products purchased to our file
            fileWriter.println("---Items-Purchased-today:---");
            //fileWriter.println("----------------------------");
            for (Product p : purchasedProducts) {
                fileWriter.println(p.toString());
            }

            //print the total amount of money earned
            fileWriter.println("----Total-Money-earned:-----");
            
            double total = 0.0;

            for (Product p : purchasedProducts)
            {
                total += p.getPrice();
            }
            fileWriter.println("Total: $" + LDUtil.RoundPoints(total, 2));
            fileWriter.println("----------------------------");

            fileWriter.close();
            fStream.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An Error Occured when trying to create the EndOfDay File!");
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("An Error Occured when trying to create the EndOfDay File!");
            System.out.println(e.getMessage());
        }
    }

    public void generateUpdatedInventory()
    {
        try
        {
            FileOutputStream fStream = new FileOutputStream(saveFileName + ".csv");
            PrintWriter fileWriter = new PrintWriter(fStream);
            fileWriter.println(header); //print the header on the first line.
            for (Product p : products)
            {
                if (p instanceof Book)
                {
                    fileWriter.println(p.getId() + "," + p.getClass().getName().toLowerCase() + "," + p.getName() + "," + ((Book)(p)).getAuthor() + "," + p.getStock() + "," + p.getPrice());
                }
                else if (p instanceof DVD)
                {
                    fileWriter.println(p.getId() + "," + p.getClass().getName().toLowerCase() + "," + p.getName() + "," + ((DVD)(p)).getDirector() + "," + p.getStock() + "," + p.getPrice());
                }
                else if (p instanceof CD)
                {
                    fileWriter.println(p.getId() + "," + p.getClass().getName().toLowerCase() + "," + p.getName() + "," + ((CD)(p)).getArtist() + "," + p.getStock() + "," + p.getPrice());
                }
                
            }

            fileWriter.close();
            fStream.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occured when trying to create the UpdatedProductInventory File!");
            System.out.println(e.getMessage());
        }
    }
}
