package laboratories.demo03;
public final class Calculator extends Calculating {
    private boolean gotFirstNumber;
    private boolean gotOperator;
    private boolean gotSecondNumber;
    private int firstInt;
    private int secondInt;
    public Calculator() {
        firstNumber = "";
        secondNumber = "";
        firstInt = 0;
        secondInt = 0;
        gotFirstNumber = false;
        gotOperator = false;
        gotSecondNumber = false;
    }

    @Override
    public void clear() {
        firstNumber = "";
        secondNumber = "";
        firstInt = 0;
        secondInt = 0;
        gotFirstNumber = false;
        gotOperator = false;
        gotSecondNumber = false;
    }

    @Override
    public int getResult() throws CalculatorException {
        if (!gotSecondNumber) {
            throw new CalculatorException("Not enough data");
        }
        switch (operator) {
            case "+":
                return firstInt + secondInt;
            case "-":
                return firstInt - secondInt;
            case "*":
                return firstInt * secondInt;
            case "/":
                return firstInt / secondInt;
        }
        throw new CalculatorException();
    }

    @Override
    public String pressKey(char key) throws CalculatorException {
        if (key >= '0' && key <= '9') {
            if (!gotOperator) {
                firstInt = firstInt * 10 + key - '0';
                firstNumber = String.valueOf(firstInt);
                gotFirstNumber = true;
                return firstNumber;
            } else {
                secondInt = secondInt * 10 + key - '0';
                secondNumber = String.valueOf(secondInt);
                gotSecondNumber = true;
                return secondNumber;
            }
        } else if (gotFirstNumber && !gotSecondNumber) {
            switch (key) {
                case '+':
                    gotOperator = true;
                    operator = "+";
                    return "+";
                case '-':
                    gotOperator = true;
                    operator = "-";
                    return "-";
                case '*':
                    gotOperator = true;
                    operator = "*";
                    return "*";
                case '/':
                    gotOperator = true;
                    operator = "/";
                    return "/";
                default:
                    throw new CalculatorException("Illegal symbol " + key);
            }
        } else {
            throw new CalculatorException("Illegal symbol " + key);
        }    
    }
}
