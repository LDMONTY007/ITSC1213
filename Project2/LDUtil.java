import java.util.*;
import java.lang.reflect.*;

import javax.lang.model.type.ArrayType;

/**
 * A collection of useful methods for working in java.
 * Mostly created from things I used in other assignments.
 */
public class LDUtil {
    /**
     * Returns true if a number is in the range, by default inclusive.
     * 
     * @param n           number to be checked
     * @param min         minimum of range
     * @param max         maximum of range
     * @param outsideMode changes to exclusive if true.
     * @return true if n is in the given range, otherwise false.
     */
    public static boolean InRange(int n, int min, int max, boolean outsideMode) {
        if (n >= min && n <= max && !outsideMode) {
            return true;
        } else if (outsideMode && (n <= min || n >= max)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates a discounted value given the percent as an integer (i.e. 5% is 5)
     * 
     * @param value           value that the discount is applied to
     * @param discountPercent percent discount as a whole number (1% = 1)
     * @return the discounted values
     */
    public static double CalcDiscount(double value, int discountPercent) {
        double disc = value - (value * ((double) discountPercent / 100));
        return disc;
    }

    /**
     * Rounds a value up to the given amount of decimal points
     * 
     * @param value  value to be rounded
     * @param points rounding point
     * @return value rounded at the given point.
     */
    public static double RoundPoints(double value, int points) {
        double spaces = Math.pow(10, points); // we raise 10 to our points power because we have to make our value 1.151
                                              // become 115.1 so when we round it becomes 115
        return Math.round(value * spaces) / spaces; // multiply by a multiple of 10 to move the decimal place, round to
                                                    // the whole number, then divide by the same multiple of 10 to
                                                    // remake our original value but rounded.
    }

    /**
     * Gets the ordinal (first, second, third, eighteenth) string based on a number
     * (1-20)
     * 
     * @param num the number referring to the ordinal number
     * @return the string representation of an ordinal number
     */
    public static String GetOrdinalNumber(int num) {
        switch (num) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            case 5:
                return "fifth";
            case 6:
                return "sixth";
            case 7:
                return "seventh";
            case 8:
                return "eighth";
            case 9:
                return "ninth";
            case 10:
                return "tenth";
            case 11:
                return "eleventh";
            case 12:
                return "twelfth";
            case 13:
                return "thirteenth";
            case 14:
                return "fourteenth";
            case 15:
                return "fifteenth";
            case 16:
                return "sixteenth";
            case 17:
                return "seventeenth";
            case 18:
                return "eighteenth";
            case 19:
                return "nineteenth";
            case 20:
                return "twentieth";
            default:
                return "last";
        }
    }

    /**
     * gets the day of the week based on day number.
     * 
     * @param dayNum input to get the day.
     * @return the day of the week as string.
     */
    public static String DayOfWeek(int dayNum) {
        if (dayNum == 1) {
            return "Sunday";
        } else if (dayNum == 2) {
            return "Monday";
        } else if (dayNum == 3) {
            return "Tuesday";
        } else if (dayNum == 4) {
            return "Wednesday";
        } else if (dayNum == 5) {
            return "Thursday";
        } else if (dayNum == 6) {
            return "Friday";
        } else if (dayNum == 7) {
            return "Saturday";
        } else {
            return "Invalid";
        }
    }

    /**
     * Gets the next user input of a specific type, if the user inputs the wrong
     * type it will prompt the correct type to be entered.
     * 
     * @param <T>    The type of object being returned (automatically set in an
     *               assignment statement)
     * @param scnr   Current scanner object
     * @param tClass The type's class
     * @return the next input of the specified type.
     */
    public static <T> T GetNextOfType(Scanner scnr, Class<T> tClass) {
        if (String.class.isAssignableFrom(tClass)) {
            Object temp = scnr.nextLine(); // make it a basic object so we can cast to it.
            return (T) temp; // cast to T
        } else if (int.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextInt()) {
                System.out.println("Please enter an integer value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextInt();
            scnr.nextLine(); // so we can clear the line within this method.
            return (T) temp;
        } else if (double.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextDouble()) {
                System.out.println("Please enter a double value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextDouble();
            scnr.nextLine(); // clear newline
            return (T) temp;
        } else if (long.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextLong()) {
                System.out.println("Please enter a long value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextLong();
            return (T) temp;
        } else {
            Object temp = scnr.nextLine();
            return (T) temp;
        }
    }

    

    /**
     * Gets the next user input of a specific type in a range (inclusive), if the
     * user inputs the wrong type it will prompt the correct type to be entered.
     * 
     * @param <T>    The type of object being returned (automatically set in an
     *               assignment statement)
     * @param scnr   Current scanner object
     * @param tClass The type's class
     * @param min    the minumum value to be returned for casting, asks user for new
     *               value if input is outside this range.
     * @param max    the maximum value to be returned for casting, asks user for new
     *               value if input is outside this range.
     * @return the next input of the specified type.
     */
    public static <T> T GetNextOfType(Scanner scnr, Class<T> tClass, int min, int max) {
        if (String.class.isAssignableFrom(tClass)) {
            Object temp = scnr.nextLine(); // make it a basic object so we can cast to it.
            return (T) temp; // cast to T
        } else if (int.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextInt()) {
                System.out.println("Please enter an integer value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextInt();
            scnr.nextLine();
            while ((int) temp < min || (int) temp > max) {
                System.out.println("Please enter a value within the range (" + min + ", " + max + ")");
                temp = scnr.nextInt();
                scnr.nextLine();
            }
            return (T) temp;
        } else if (double.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextDouble()) {
                System.out.println("Please enter a double value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextDouble();
            scnr.nextLine(); // clear newline
            while ((double) temp < min || (double) temp > max) {
                System.out.println("Please enter a value within the range (" + min + ", " + max + ")");
                temp = scnr.nextDouble();
                scnr.nextLine();
            }
            return (T) temp;
        } else if (long.class.isAssignableFrom(tClass)) {
            while (!scnr.hasNextLong()) {
                System.out.println("Please enter a long value.");
                scnr.nextLine(); // clear line so we don't have an infinite loop
            }
            Object temp = scnr.nextLong();
            scnr.nextLine();
            while ((long) temp < min || (long) temp > max) {
                System.out.println("Please enter a value within the range (" + min + ", " + max + ")");
                temp = scnr.nextLong();
                scnr.nextLine();
            }
            return (T) temp;
        } else {
            Object temp = scnr.nextLine();
            return (T) temp;
        }
    }

    /**
     * Consolidates the array by moving array objects so that the objects are
     * in adjacent spaces, starting at index 0, with no empty space
     * between any two objects, leaving all null indexes at the end of the array..
     * Postcondition: The order of the horses is the same as before
     * the consolidation.
     * 
     * @param spaces array of any type.
     * @return sorted array
     */
    public static <T> T[] consolidate(T[] spaces) {
        // #region old shit
        // for (int i = 0; i < spaces.length; i++) { //loop through entire array
        // if (spaces[i] == null) { //check that there is a null value
        // for (int j = i + 1; j < spaces.length; j++) { //loop through and move
        // everything to the back 1 in the array
        // spaces[j - 1] = spaces[j];
        // }
        // spaces[spaces.length - 1] = null;
        // // for (T t : spaces) {
        // // System.out.println("Iteration : " + i);
        // // System.out.println(t);
        // // }
        // }
        // }
        // #endregion
        T[] tempSpaces = spaces.clone(); // remember setting it equal to spaces will make it a reference
        for (int i = 0; i < tempSpaces.length; i++) { // empty array
            tempSpaces[i] = null;
        }
        int tempCount = 0;
        for (int i = 0; i < spaces.length; i++) { // loop through entire array
            if (spaces[i] != null) {
                tempSpaces[tempCount] = spaces[i];
                tempCount++;
            }

        }
        spaces = tempSpaces;
        return spaces;
    }

    public static <T> boolean contains(T[] arr, T var) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == var) {
                return true;
            }
        }
        return false;
    }

    // #region essentially useless, mostly made for code workouts that are
    // hyperspecific

    public static int countEvens(int[] nums) {
        int count = 0;
        for (int i : nums) {
            if (i % 2 == 0)
                count++;
        }
        return count;
    }

    /**
     * Given an integer num, return the sum of the multiples of num between 1 and
     * 100. For example, if num is 20, the returned value should be the sum of 20,
     * 40, 60, 80, and 100, which is 300. If num is not positive, return 0.
     * 
     * @param num
     * @return
     */
    public static int sumMultiples(int num) {
        if (num < 0)
            return 0;
        int multiple = 100 / num;
        int curMultiple = multiple;
        int total = 0;
        while (curMultiple > 0) {
            total += curMultiple * num;
            curMultiple--;
        }
        return total;
    }

    /**
     * Given two integers low and high representing a range, return the sum of the
     * integers in that range. For example, if low is 12 and high is 18, the
     * returned values should be the sum of 12, 13, 14, 15, 16, 17, and 18, which is
     * 105. If low is greater than high, return 0.
     * 
     * @param low
     * @param high
     * @return
     */
    public static int sumRange(int low, int high) {
        int current = low;
        int total = low;
        while (current < high) {
            current++;
            total += current;
        }
        if (low > high) {
            return 0;
        }
        return total;
    }

    /**
     * We'll say that a "triple" in a string is a char appearing three times in a
     * row. Return the number of triples in the given string. The triples may
     * overlap.
     * 
     * @param str
     * @return
     */
    public static int countTriple(String str) {
        char current = '`';
        int count = 0;
        int triples = 0;
        for (char i : str.toCharArray()) {
            if (current == i) {
                count++;
                if (count == 3) {
                    triples++;
                }
                if (count > 3) {
                    triples++;
                }
            } else if (current != i) {
                current = i;
                count = 1;
            }
            System.out.println(current + ":" + i + ":" + count + ":" + triples);

        }

        return triples;
    }

    /**
     * Given two strings, base and remove, return a version of the base string where
     * all instances of the remove string have been removed (not case sensitive).
     * You may assume that the remove string is length 1 or more. Remove only
     * non-overlapping instances, so with "xxx" removing "xx" leaves "x".
     * 
     * @param base
     * @param remove
     * @return
     */
    public static String withoutString(String base, String remove) {
        String out = base;
        while (out.toLowerCase().indexOf(remove.toLowerCase()) != -1) {
            out = out.replace(" " + remove + " ", "  ");
            out = out.replace(" " + remove.toUpperCase() + " ", "  ");
            out = out.replace(" " + remove.toLowerCase() + " ", "  ");
            out = out.replace(remove + " ", " ");
            out = out.replace(remove.toUpperCase() + " ", " ");
            out = out.replace(remove.toLowerCase() + " ", " ");
            out = out.replace(" " + remove.toUpperCase(), " ");
            out = out.replace(" " + remove.toLowerCase(), " ");
            out = out.replace(remove, "");
            out = out.replace(remove.toUpperCase(), "");
            out = out.replace(remove.toLowerCase(), "");
        }
        return out;
    }

    /**
     * Given a string, return a string where for every char in the original, there
     * are two chars.
     * 
     * @param str input string
     * @return modified string
     */
    public static String doubleChar(String str) {
        String out = new String();
        for (char i : str.toCharArray()) {
            out = out + i + i;
        }
        return out;
    }

    /**
     * Checks if the input date is a magic date (if year equals date multiplied by
     * month)
     * 
     * @param date
     * @param month
     * @param year
     * @return true if magicDate otherwise false.
     */
    public static boolean magicDate(int date, int month, int year) {
        if ((date < 1 || date > 31) || (month < 1 || month > 12)) {
            return false;
        } else if (year == (date * month)) {
            return true;
        } else {
            return false;
        }
    }

    // #endregion

    // #region turtle only

    /**
     * Draws a polygon (using a turtle) inscribed to a square given side count and
     * length.
     * 
     * @param t          turtle
     * @param sides      side amount for the polygon
     * @param sideLength length of each side.
     */
    // public static void drawPolygon(Turtle t, int sides, int sideLength) // Draws
    // a polygon restricted to a square of size sideLength.
    // {
    // if (sides != 4) {
    // System.out.println(sides);
    // t.penUp();
    // t.setHeading(0);
    // t.backward((sideLength - ((int) ((sideLength * Math.sin(Math.PI / (double)
    // (sides)))))) / 2);
    // t.setHeading(90);
    // t.forward(t.getPenWidth());
    // t.setHeading(0);
    // t.penDown();
    // }
    // for (int i = 0; i < sides; i++) {
    // t.turn(360 / sides);
    // double angle = Math.toRadians(360 / sides);
    // // t.moveTo(sides*(int)Math.cos((Math.PI/3) * i),
    // // sides*(int)Math.sin((Math.PI/3) * i));
    // // t.moveTo(t.getXPos() + (sideLength*(int)Math.cos(angle + Math.PI/3 *
    // // i)), t.getYPos() + (sideLength*(int)Math.sin(angle + Math.PI/3 * i)));
    // // t.forward((int)t.getDistance(t.getXPos() +
    // // (sideLength*(int)Math.cos(angle + Math.PI/3 * (i+1))), t.getYPos() +
    // // (sideLength*(int)Math.sin(angle + Math.PI/3 * (i+1)))));
    // if (sides != 4) {
    // t.forward((int) ((sideLength * Math.sin(Math.PI / (double) (sides)))));
    // } else {
    // t.forward(sideLength);
    // }
    // // t.forward((sideLength * 4) / sides);
    // // t.forward((sideLength * 4) / sides);
    // }
    // if (sides != 4) {
    // t.penUp();
    // t.setHeading(0);
    // t.forward((sideLength - ((int) ((sideLength * Math.sin(Math.PI / (double)
    // (sides)))))) / 2);
    // t.setHeading(90);
    // t.backward(t.getPenWidth());
    // t.setHeading(0);
    // t.penDown();
    // }
    // }

    // #endregion
}