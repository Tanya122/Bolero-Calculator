package Calculator.Operations;

import Calculator.Enum.Operator;

public class Division implements OperationHandler {

    private static Division instance;
    private Division(){}

    public static Division getInstance() {
        if(instance == null) {
            instance = new Division();
        }
        return instance;
    }
    private OperationHandler nextHandler;
    @Override
    public void setNextHandler(OperationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    @Override
    public double handle(Operator operator, double a, double b) {
        if(operator.equals(Operator.DIVISION)) {
            if (a == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return b/a;
        }
        else {
            return nextHandler.handle(operator, a, b);
        }
    }
}
