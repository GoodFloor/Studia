package laboratories.demo03;
abstract class Calculating {

    String firstNumber;
    String secondNumber;
    String operator;
    
    /**
     * Clear fields firstNumber, secondNumber and operator.
     */
    public abstract void clear();
    
    /**
     * @return Result of mathematical operation (given by operator) of 
     * numbers firstNumber and secondNumber
     * throws CalculatorException if numbers or operator are not given
     */
    public abstract int getResult() throws CalculatorException;
    
    /**
     * Method that simulate Calculator button pressed. Calculator support only
     * digits 0-9, and operators + - * / 
     * For all other entries it throws CalculatorException. If operator is given 
     * before first number it throws CalculatorException. Operator can be entered 
     * or changed only before at least one digit of second number was entered. 
     * After one digit of second number is entered, attempt to change operator 
     * throws CalculatorException. 
     * @param key represents the key that was pressed on calculator
     * @return current content of screen that represent currently pressed buttons 
     * for digits or operator, but not at the same time (e.g. 45 or -)
     */
    public abstract String pressKey(char key) throws CalculatorException;
    
}
