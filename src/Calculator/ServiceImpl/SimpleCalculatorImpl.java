package Calculator.ServiceImpl;

import Calculator.Enum.Operator;
import Calculator.Service.Calculator;
import Calculator.Operations.*;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleCalculatorImpl implements Calculator {

    private final List<Character> operations;
    private static SimpleCalculatorImpl instance;
    private SimpleCalculatorImpl(List<Character> operations){
        this.operations = operations;
    }
    public static SimpleCalculatorImpl getInstance(List<Character> operations) {
        if (instance == null) {
            instance = new SimpleCalculatorImpl(operations);
        }
        return instance;
    }

    @Override
        public double calculate(String expression) throws IllegalArgumentException, ArithmeticException {
        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Invalid input for calculation. Please enter a valid expression.");
        }
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String num = "";

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                num = num.concat(String.valueOf(ch));
            } else {
                if (num.length() > 0) {
                    numbers.push(Double.parseDouble(num));
                    num = "";
                }
                if (operations.contains(ch)) {
                    while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                        char operator = operators.pop();
                        double a = numbers.pop();
                        double b = numbers.pop();
                        numbers.push(applyOperation(operator, a, b));
                    }
                    operators.push(ch);
                }
            }
        }

        if (num.length() > 0) {
            numbers.push(Double.parseDouble(num));
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    private double applyOperation(char operator, double a, double b) {
        OperationHandler additionOperationHandler = Addition.getInstance();
        OperationHandler subtractionOperationHandler = Subtraction.getInstance();
        OperationHandler multiplicationOperationHandler = Multiplication.getInstance();
        OperationHandler divisionOperationHandler = Division.getInstance();

        divisionOperationHandler.setNextHandler(multiplicationOperationHandler);
        multiplicationOperationHandler.setNextHandler(additionOperationHandler);
        additionOperationHandler.setNextHandler(subtractionOperationHandler);

        Operator operatorEnum = Operator.fromSymbol(operator);

        return divisionOperationHandler.handle(operatorEnum, a, b);
    }

    static boolean isValidExpression(String input) {
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)(\\s*[-+*/]\\s*\\d+(\\.\\d+)?)*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
