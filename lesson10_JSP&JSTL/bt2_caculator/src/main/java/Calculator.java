public class Calculator {
    public double calculate(double operand1, double operand2, String operator) throws Exception {
        switch (operator) {
            case "Addition":
                return operand1 + operand2;
            case "Subtraction":
                return operand1 - operand2;
            case "Multiplication":
                return operand1 * operand2;
            case "Division":
                if (operand2 == 0) {
                    throw new Exception("Cannot divide by zero");
                }
                return operand1 / operand2;
            default:
                throw new Exception("Invalid operator");
        }
    }
}