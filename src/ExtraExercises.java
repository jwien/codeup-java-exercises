import java.sql.Array;
import java.util.Scanner;

public class ExtraExercises {
    public static void main(String args[]) {
        /* Write a program in Java to read 10 numbers from keyboard and find their sum and average

        Expected Output :
        The sum of 10 numbers is : 55
        The Average is : 5.500000
         */
        int[] numbers = new int[10];
        System.out.println("You will be prompted for ten integers. After all are received, you will be provided with their sum and average.");
        for (short i = 0; i < 10; i++) {
            System.out.printf("Number %d - ", i + 1);
            numbers[i] = getInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        System.out.printf("Sum of numbers: %d\n", getIntArraySum(numbers));
        System.out.printf("Average of numbers: %d\n", getAverage(numbers));

        /* Write a program in Java to display the cube of the number upto given an integer. Read the number using the Scanner class.

        Expected Output :
        Number is : 1 and cube of the 1 is :1
        Number is : 2 and cube of the 2 is :8
        Number is : 3 and cube of the 3 is :27
        Number is : 4 and cube of the 4 is :64
        Number is : 5 and cube of the 5 is :125
         */

        System.out.println();
    }
    private static Scanner sc = new Scanner(System.in);

    private static int getInteger(int min, int max) {
        System.out.printf("Enter a number:\n");
        int userInt = sc.nextInt();
        if (userInt < min || userInt > max)
            return getInteger(min, max);
        else
            return  userInt;
    }

    private static long getIntArraySum(int[] numbers) {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    private static long getAverage(int[] numbers) {
        return getIntArraySum(numbers) / numbers.length;
    }
}
