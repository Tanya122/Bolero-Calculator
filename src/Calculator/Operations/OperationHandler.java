package Calculator.Operations;

import Calculator.Enum.Operator;

public interface OperationHandler {
    void setNextHandler(OperationHandler nextHandler);
    double handle(Operator operator, double a, double b);

}
