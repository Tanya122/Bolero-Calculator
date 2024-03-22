package Calculator.Operations;

import Calculator.Enum.Operator;

public class Addition implements OperationHandler {

    private static Addition instance;
    private Addition(){}

    public static Addition getInstance() {
        if(instance == null) {
            instance = new Addition();
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
        if(operator.equals(Operator.ADDITION)) {
            return a+b;
        }
        else {
            return nextHandler.handle(operator, a, b);
        }
    }
}
