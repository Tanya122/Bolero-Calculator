package Calculator;

import Calculator.Service.Calculator;
import Calculator.ServiceImpl.SimpleCalculatorImpl;

import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = SimpleCalculatorImpl.getInstance();


        System.out.println("Enter the expression to evaluate. Type 'exit' to quit.");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                double result = calculator.calculate(input);
                System.out.println("Result: " + result);
            } catch ( IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.out.println("Operator Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic Error: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Exiting the calculator...");
    }


}


