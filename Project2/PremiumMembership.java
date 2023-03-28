

public class PremiumMembership extends Product {
    //I had to create this class because previously the "Product" class was used to make this object,
    //but as it is now abstract I have to use this class. (due to my prior code)
    public PremiumMembership(String n, double p)
    {
        super(n, p);
    }

    /**
     * 
     * @return negative value if this product's price is less than the compared
     *         product, positive if this products price is greater than the compared
     *         product, 0 if they are equal.
     */
    public int compareTo(Product o) {
        if (o.getPrice() == this.getPrice()) {
            return 0;
        } else if (this.getPrice() > o.getPrice()) {
            return 1;
        } else if (this.getPrice() < o.getPrice()) {
            return -1;
        }
        return 0; // 0 if they are equal.
    }
}
