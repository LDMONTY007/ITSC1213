

public class TestHarness
{
    public static void main(String[] args) {
        StringAnalyzer wa = new StringAnalyzer();

        System.out.println("Testing firstRepeatedCharacter method");
        String s = "aardvark";
        char result = wa.firstRepeatedCharacter(s);
        System.out.println("Test 1 firstRepeatedCharacter: " + validate('a', result));

        s = "roommate";
        result = wa.firstRepeatedCharacter(s);
        System.out.println("Test 2 firstRepeatedCharacter: " + validate ('o', result));

        s = "mate";
        result = wa.firstRepeatedCharacter(s);
        System.out.println("Test 3 firstRepeatedCharacter: " + validate('0', result));
        System.out.println("----------------------");
        
        System.out.println("--------PART-B--------");
        
        s = "four"; //test that there are no repeated characters
        result = wa.firstMultipleCharacter(s);
        System.out.println("Test 1 firstMultipleCharacter: " + validate('0', result));
        
        s = "tthe"; //test that there are multiple repeated characters at the beginning of the string.
        result = wa.firstMultipleCharacter(s);
        System.out.println("Test 2 firstMultipleCharacter: " + validate('t', result));
        
        s = "hello"; // test that there are multiple duplicate characters appearing somewhere in the middle of the string.
        result = wa.firstMultipleCharacter(s);
        System.out.println("Test 3 firstMultipleCharacter: " + validate('l', result));

        s = "futball"; // test that there are multiple duplicate characters appearing at the end of the string.
        result = wa.firstMultipleCharacter(s);
        System.out.println("Test 4 firstMultipleCharacter: " + validate('l', result));
        
        System.out.println("--------PART-C--------");

        s = "mississippiii";
        int intResult = wa.countRepeatedCharacters(s);
        System.out.println("Test 1 countRepeatedCharacters: " + validate(4, intResult));

        s = "test";
        intResult = wa.countRepeatedCharacters(s);
        System.out.println("Test 2 countRepeatedCharacters: " + validate(0, intResult));

        s = "aabbcdaaaabbb";
        intResult = wa.countRepeatedCharacters(s);
        System.out.println("Test 3 countRepeatedCharacters: " + validate(4, intResult));

        s = "“aaaaabbcdaaaabbb";
        intResult = wa.countRepeatedCharacters(s);
        System.out.println("Test 4 countRepeatedCharacters: " + validate(4, intResult));

        System.out.println("----------------------");
    }

    private static String validate(char expected, char result)
    {
        if (result != expected)
        {
            return ("The result " + result + " does not match expected:" + expected + " --->> Failed");
        }
        else
        {
            return ("The result " + result + " match expected: " + expected + " --->> OK");
        }
    }

    private static String validate(int expected, int result)
    {
        if (result != expected)
        {
            return ("The result " + result + " does not match expected: " + expected + " --->> Failed");
        }
        else
        {
            return ("The result " + result + " match expected: " + expected + " --->> OK");
        }
    }
}