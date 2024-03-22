package Calculator.Operations;

import Calculator.Enum.Operator;

public class Multiplication implements OperationHandler {

    private static Multiplication instance;
    private Multiplication() {}

    public static Multiplication getInstance() {
        if(instance == null)
        {
            instance = new Multiplication();
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
        if(operator.equals(Operator.MULTIPLICATION)) {
            return a*b;
        }
        else {
            return nextHandler.handle(operator, a, b);
        }
    }
}
