package Misc;

public class Workshop1 {
    public static void main(String[] args) {
        Car c = new Car("F150", 2015, "Red", "P");
        System.out.println(c.getColor());
        c.setColor("Black");
        System.out.println(c.getColor());
        System.out.println(c.getGear());
        c.setGear("D");
        System.out.println(c.getGear());
    }
}
