package Project1;
import java.util.*;
public class Calculator {
    static double num1, num2;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Calculator Program");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = s.nextInt();
            if (choice == 5) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }
            System.out.println("Value:");
            double num1 = s.nextDouble();
            System.out.println("Value:");
            double num2 = s.nextDouble();
            double result = 0;
            switch (choice) {
                case 1:
                    result = CalcLogic.add(num1, num2);
                    System.out.println("Result=" + result);
                    break;
                case 2:
                    result = CalcLogic.sub(num1, num2);
                    System.out.println("Result: " + result);
                    break;
                case 3:
                    result = CalcLogic.multiply(num1, num2);
                    System.out.println("Result: " + result);
                case 4:
                    result = CalcLogic.div(num1, num2);
                    System.out.println("Result:" + result);
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
}
