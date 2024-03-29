import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MethodsExercises {
    /*
    Create a class named MethodsExercises. Inside of your class, write code to create the
    specified methods. Test your code by creating a main method and calling each of the methods you've created.

    Basic Arithmetic

    Create four separate methods. Each will perform an arithmetic operation:

    Addition
    Subtraction
    Multiplication
    Division
    Each function needs to take two parameters/arguments so that the operation can be performed.

    Test your methods and verify the output.

    Add a modulus method that finds the modulus of two numbers.

    Food for thought: What happens if we try to divide by zero? What should happen?
     */

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(addition(1, 2));
        System.out.println(subtraction(4, 2));
        System.out.println(subtraction(3, 9));
        System.out.println(multiplication(7, 4));
        System.out.println(division(1, 2));
        System.out.println(modulus(25, 6));

        System.out.println(getInteger(1, 10));
        System.out.println(getInteger(-5, 9));


        factorialUserInputLoop();
        runDiceSim();
    }

    private static int addition(int a, int b) {
        return a + b;
    }

    private static int subtraction(int a, int b) {
        if (a < b) return subtraction(b, a);
        return a - b;
    }

    /* Bonus

    Create your multiplication method without the * operator (Hint: a loop might be helpful).
    Do the above with recursion.
     */
    private static int multiplication(int a, int b) {
        // original solution
//        return a * b;

        // no * operator
//        int sum = 0;
//        for (int i = b; i > 0; i--) {
//            sum += a;
//        }
//        return sum;

        // recursion
        // we want the sum of a added b times

        // this first conditional is technically unnecessary but by making this check
        // we will try to make the 'b' number smaller than 'a' so that we call this function recursively
        // fewer times
        if (a < b)
            return multiplication(b, a);

        // so long as b != 0, we recursively build a sum to return from a added to itself
        if (b != 0)
            return (a + multiplication(a, b - 1));

        // when 'b' bottoms out (whether by initial assignment to 0 or because we've called it recursively as b - 1
        // down to zero), we return zero to the 'a' summation chain and do not call this method again.
        else
            return 0;
    }

    private static int division(int a, int b) {
        return a / b;
    }

    private static int modulus(int a, int b) {
        return a % b;
    }

    /* Create a method that validates that user input is in a certain range

    The method signature should look like this:
    public static int getInteger(int min, int max);

    and is used like this:
    System.out.print("Enter a number between 1 and 10: ");
    int userInput = getInteger(1, 10);

    If the input is invalid, prompt the user again.

    Hint: recursion might be helpful here!
     */
    private static int getInteger(int min, int max) {
        System.out.printf("Enter a number from %d to %d\n", min, max);
        int userInt = sc.nextInt();
        if (userInt < min || userInt > max)
            return getInteger(min, max);
        else
            return  userInt;
    }

    /* Calculate the factorial of a number.

    Prompt the user to enter an integer from 1 to 10.
    Display the factorial of the number entered by the user.
    Ask if the user wants to continue.
    Use a for loop to calculate the factorial.
    Assume that the user will enter an integer, but verify it’s between 1 and 10.
    Use the long type to store the factorial.
    Continue only if the user agrees to.
    A factorial is a number multiplied by each of the numbers before it.
    Factorials are denoted by the exclamation point (n!). Ex:


    1! = 1               = 1
    2! = 1 x 2           = 2
    3! = 1 x 2 x 3       = 6
    4! = 1 x 2 x 3 x 4   = 24

     */


    /*
    Test the application and find the integer for the highest factorial that can be
    accurately calculated by this application, then modify the prompt so that it prompts the user for
    a number "from 1 to {the highest integer that returns accurate factorial calculation}".
    Don’t forget to change your verification too!

    Use recursion to implement the factorial.
     */


    private static void testLongMaxSafeFactorial() {
        // this method was utilized to test max safe value for storing factorial products in the long data type
        // my results suggest the max value for n is 20
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            long factorialProduct = calcFactorial(i);
            System.out.println(factorialProduct);
            if (factorialProduct <= 0) {
                System.out.println(i - 1);
                break;
            }
        }
    }

    private static long calcFactorial(int n, int i, long product) {
        // factorial is the product of all integers (i), from 1 to n
        if (i <= n) {
            return calcFactorial(n, i + 1, product * i);
        } else {
            return product;
        }

    }

    // overloaded method to allow simple initialization :)
    private static long calcFactorial(int n) {
        return calcFactorial(n, 2, 1);
    }

    // kinda unnecessary lol but it was fun trying this out
    // my time with C has made me much less nervous about declaring strings with semi-fixed sizes
    private static int calcFactorialStringLength(int n) {
        // assume the first part of the resultStr is only single digit, we'll correct in conditional
        int result = 6;
        if (n < 9)
            // all values 1-9 take up 4 characters
            result += n * 4;
        else
            // n % 10 when n > 9 gets us the tens column space. adding 40 gives us the 1-9 space
            // adding 1 more gives us the tens column space in the first part
            result += ((n % 10) * 5) + 41;
        // only the very highest values of n require > 16 digits to display, so we kinda undershoot hoping most cases
        // will fit well within
        result += 16;
        return result;
    }

    private static String formatFactorial(int n) {
        // using StringBuilder here for more performant loop concatenation
        StringBuilder resultStr = new StringBuilder(calcFactorialStringLength(n));
        // set up the initial declaration for factorial string (eg. 5! = 1...);
        resultStr.append(n).append("! = 1");
        for (int i = 2; i <= n; i++) {
            // append factorial steps (eg. ... x 2...)
            resultStr.append(" x ").append(i);
        }
        // cap off the with the factorial product!
        return resultStr.append(" = ").append(calcFactorial(n)).toString();
    }

    private static void factorialUserInputLoop() {
        // factorial method with user input on factorial identity and control flow
        do {
            String factorial = formatFactorial(getInteger(1, 20));
            System.out.println(factorial);
            System.out.println("Do you want to see another factorial? (y/n)");
            if (sc.next().equalsIgnoreCase("n")) {
                break;
            }
        } while (true);
    }

    /* Create an application that simulates dice rolling.

    Ask the user to enter the number of sides for a pair of dice.
    Prompt the user to roll the dice.
    "Roll" two n-sided dice, display the results of each, and then ask the user if he/she wants to roll the dice again.
    Use static methods to implement the method(s) that generate the random numbers.
    Use the .random method of the java.lang.Math class to generate random numbers.
     */

    private static void runDiceSim() {
        do {

            // take user input on dice type, build up a set of dice options
            ArrayList<DieCollection> dieSet = new ArrayList<DieCollection>(buildDieSet());
            // simulate dice roll
             rollForDiceSim(dieSet);
            // allow exit
            System.out.println("Do you want to start over with new dice? (y/n)");
            if (sc.next().equalsIgnoreCase("n")) {
                break;
            }
        } while (true);
    }

    private static ArrayList<DieCollection> buildDieSet(ArrayList<DieCollection> dieSet) {
        System.out.println("Enter the number of sides for the dice you wish to roll.");
        int diceSides = getInteger(2, 48);

        System.out.println("How many of these dice do you wish to roll?");
        int diceCount = getInteger(1, 100);

        dieSet.add(new DieCollection(diceSides, diceCount));

        // display current set of groups of dies entered by user
        System.out.println("Current set of dice:");
        for (DieCollection dieCol : dieSet) {
            System.out.println(dieCol.toString());
        }

        System.out.println("Do you want to enter more dice? (y/n)");
        if (sc.next().equalsIgnoreCase("n")) {
            return dieSet;
        } else {
            return buildDieSet(dieSet);
        }
    }

    private static ArrayList<DieCollection> buildDieSet() {
        return buildDieSet(new ArrayList<DieCollection>());
    }

    private static void rollForDiceSim(ArrayList<DieCollection> dieSet) {
        for (DieCollection dieCol : dieSet) {
            System.out.println(dieCol.toString());
            System.out.println(Arrays.toString(dieCol.roll()));
        }
    }
}

// represents a set of some non-zero integer quantity of dice with shared face values. For example, 19x 6-sided die
class DieCollection {
    private final int faceValue;
    private final int collectionCount;

    public DieCollection(int value) {
        faceValue = value;
        collectionCount = 1;
    }

    public DieCollection(int value, int count) {
        faceValue = value;
        collectionCount = count;
    }

    public int[] roll() {
        int[] rollResults = new int[collectionCount];
        for (int i = 1; i <= collectionCount; i++) {
            rollResults[i - 1] = (int) (Math.random() * (faceValue)) + 1;
        }
        return rollResults;
    }

    public String toString() {
        return String.format("%dx %d-sided die", collectionCount, faceValue);
    }
}
