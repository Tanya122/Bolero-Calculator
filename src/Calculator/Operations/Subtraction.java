package Calculator.Operations;

import Calculator.Enum.Operator;

public class Subtraction implements OperationHandler {

    private static Subtraction instance;
    private Subtraction(){}

    public static Subtraction getInstance() {
        if(instance == null) {
            instance = new Subtraction();
        }
        return instance;
    }

    private OperationHandler nextHandler;
    @Override
    public void setNextHandler(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public double handle(Operator operator, double b, double a) {
        if(operator.equals(Operator.SUBTRACTION)) {
            return a-b;
        }
        else {
            throw new UnsupportedOperationException("Operator '" + operator + "' is not supported by the application.");
        }
    }
}
