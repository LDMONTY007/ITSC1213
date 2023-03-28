package Misc;

public class Car
{
    public String model;
    public int year;
    private String color;
    private String gear;

    /**
     * Car class created for the workshop. 
     * @param m Model of the car
     * @param y Model Year
     * @param c Color of the car
     * @param g The current gear of the car
     */
    public Car(String m, int y, String c, String g)
    {
        model = m;
        year = y;
        color = c;
        gear = g;
    }

    public String getGear()
    {
        return gear;
    }

    public void setGear(String gear) 
    {
        this.gear = gear;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}