package Calculator.Service;

public interface Calculator {
    double calculate(String expression) throws IllegalArgumentException, ArithmeticException, UnsupportedOperationException;
}
