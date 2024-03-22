package Calculator;

import Calculator.Enum.CalculatorType;
import Calculator.Factory.CalculatorFactory;
import Calculator.Service.Calculator;

import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character[] simpleOperationsArray = {'+', '-', '*', '/'};
        Calculator calculator = CalculatorFactory.getInstance(CalculatorType.SIMPLE, Arrays.asList(simpleOperationsArray));


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
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic Error: " + e.getMessage());
            }
        }
        System.out.println("Exiting the calculator...");
    }


}


