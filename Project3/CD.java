import java.util.ArrayList;

public class CD extends Product {

    private String artist;

    private ArrayList<String> tracks = new ArrayList<>(); //the list of tracks this object contains.

    public CD(String name, double price, String artist, int stock)
    {   
        super(name, price, stock); //call parent constructor
        this.artist = artist;
    }

    public CD(String name, double price, String artist, ArrayList<String> tracks, int stock) 
    {
        super(name, price, stock); // call parent constructor
        this.artist = artist;
        this.tracks = tracks; //set our tracks to be equal to the input arrayList.
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<String> tracks) {
        this.tracks = tracks;
    }

    public String getArtist()
    {
        return this.artist;
    }

    @Override //I know we haven't learned overloading yet but I wanted to get a head start.
    public String toString() 
    {
        if (tracks.size() != 0) //make sure we have tracks before printing out an empty track list.
        return this.getName() + ", is $" + this.getPrice() + ", \nTrackList: \n" + tracks.toString() + ", Stock: "
                + stock;
        else
        return this.getName() + ", is $" + this.getPrice() + ", Stock: " + stock;
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
