import java.util.ArrayList;

public class CD extends Product {
    
    private ArrayList<String> tracks = new ArrayList<>(); //the list of tracks this object contains.

    public CD(String name, double price)
    {   
        super(name, price); //call parent constructor
    }

    public CD(String name, double price, ArrayList<String> tracks) 
    {
        super(name, price); // call parent constructor
        this.tracks = tracks; //set our tracks to be equal to the input arrayList.
    }

    public ArrayList<String> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<String> tracks) {
        this.tracks = tracks;
    }

    @Override //I know we haven't learned overloading yet but I wanted to get a head start.
    public String toString() 
    {
        if (tracks.size() != 0) //make sure we have tracks before printing out an empty track list.
        return this.getName() + ", is $" + this.getPrice() + ", \nTrackList: \n" + tracks.toString();
        else
        return this.getName() + ", is $" + this.getPrice();
    }
}
