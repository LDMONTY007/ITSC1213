package Misc;

import java.awt.*;

//Part A
//Package : java.awt
//I need to import java.awt.*, and I need to declare a variable of type "Rectangle" using it's constructor. 
//It has 7 constructors
//r = new Rectangle();  This is missing a datatype of Rectangle
//r.translate(10,“Hello”); this needs to be an int, int not an int, String.
//

//Part B
public class RectangleTest {
    public static void main(String[] args) {
        Rectangle box1 = new Rectangle(10, 10, 40, 30);
        System.out.println("box1: " + box1); 
        
    }
}
