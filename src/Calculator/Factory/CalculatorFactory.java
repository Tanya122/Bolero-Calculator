package Calculator.Factory;

import Calculator.Enum.CalculatorType;
import Calculator.Service.Calculator;
import Calculator.ServiceImpl.SimpleCalculatorImpl;

import java.util.List;

public class CalculatorFactory {

    public static Calculator getInstance(CalculatorType type, List<Character> operations) {
        switch (type) {
            case SIMPLE:
                return SimpleCalculatorImpl.getInstance(operations);
            default:
                throw new IllegalArgumentException("Unsupported calculator type: " + type);
        }
    }
}
