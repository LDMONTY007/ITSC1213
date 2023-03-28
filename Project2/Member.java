import java.util.ArrayList;

public class Member {
    public static int nextId = 1; //The id we assign to a new Member on instantiation.

    private int id; //id of this member
    private boolean isPremium; //is this a premium member
    private String name; //name of this member
    private double moneySpent; //how much money they have spent (from completed purchases)
    ArrayList<Product> currentProducts = new ArrayList<>(50); //initialize this array to 50 objects so we can avoid lag.
    private double currentTotal; //the current total of the current products list's prices.


    public Member(String name)
    {
        this.id = Member.nextId; //assign the ID
        Member.nextId++; //increment next ID
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public String getName() {
        return name;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    /**
     * Calculates our current total and gives us it's value.
     * @return currentTotal as a double
     */
    public double getCurrentTotal()
    {
        calculateShoppingCartTotal();
        return currentTotal;
    }

    /**
     * Calculates the total of our current products and assigns it to our "currentTotal" variable.
     */
    public void calculateShoppingCartTotal()
    {
        currentTotal = 0;
        for (Product p : currentProducts) {
            currentTotal += p.getPrice();
        }
    }

}
