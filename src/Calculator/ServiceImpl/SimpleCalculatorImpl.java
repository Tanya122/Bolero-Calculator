package Calculator.ServiceImpl;

import Calculator.Enum.Operator;
import Calculator.Service.Calculator;

import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static Calculator.Enum.Operator.fromSymbol;

public class SimpleCalculatorImpl implements Calculator {
    private static SimpleCalculatorImpl instance;
    private SimpleCalculatorImpl(){
    }
    public static SimpleCalculatorImpl getInstance() {
        if (instance == null) {
            synchronized (SimpleCalculatorImpl.class){
                if (instance == null) {
                    instance = new SimpleCalculatorImpl();
                }
            }

        }
        return instance;
    }

    @Override
    public double calculate(String expression) throws IllegalArgumentException, ArithmeticException, UnsupportedOperationException {
        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Invalid input for calculation. Please enter a valid expression.");
        }
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String num = "";

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (Character.isDigit(character) || character == '.') {
                num = num.concat(String.valueOf(character));
            } else {
                if (num.length() > 0) {
                    numbers.push(Double.parseDouble(num));
                    num = "";
                }
                if ( Arrays.asList(Operator.values()).contains(fromSymbol(character))) {
                    while (!operators.isEmpty() && precedence(character) <= precedence(operators.peek())) {
                        numbers.push(applyOperation(fromSymbol(operators.pop()), numbers.pop(), numbers.pop()));
                    }
                    operators.push(character);
                } else {
                    throw new UnsupportedOperationException("Operator '" + character + "' is not supported by the application.");
                }
            }
        }

        if (num.length() > 0) {
            numbers.push(Double.parseDouble(num));
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(fromSymbol(operators.pop()), numbers.pop(), numbers.pop()));
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

    private double applyOperation(Operator operator, double a, double b) {
        switch (operator) {
            case ADDITION:
                return a + b;
            case SUBTRACTION:
                return a - b;
            case MULTIPLICATION:
                return a * b;
            case DIVISION:
                if (a == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return b / a;
            default:
                throw new UnsupportedOperationException("Operator '" + operator + "' is not supported by the application.");
        }
    }

    private boolean isValidExpression(String input) {
        Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)(\\s*[-+*/]\\s*\\d+(\\.\\d+)?)*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
