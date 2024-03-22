package Calculator.Enum;

public enum Operator {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    private final char op;

    Operator(char op) {
        this.op = op;
    }

    public char getOperatorSymbol() {
        return op;
    }

    public static Operator fromSymbol(char op) {
        for (Operator operator : Operator.values()) {
            if (operator.getOperatorSymbol() == op) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + op);
    }
}
