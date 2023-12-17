package verifyInputs;

import operations.*;
import verifyInputs.VerifyInput;

import java.util.Stack;

public class DefaultVerifyInput implements VerifyInput {


    @Override
    public Boolean isValidInput(String input) {

        if (input.isEmpty() || input.isBlank()){
            return false;
        }
        if (!doubleTesting(input) && !operatorTesting(input)){
            return false;
        }
        return true;
    }
    @Override
    public Boolean doubleTesting(String input){

        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Boolean operatorTesting(String input){

        return "+-*/%".contains(input);
    }

    @Override
    public Boolean hasPrecedence(Operation op1, Operation op2) {
        return (op2 instanceof Multiplication || op2 instanceof Division) &&
                (op1 instanceof Addition || op1 instanceof Subtraction);
    }

    @Override
    public Double applyOperation(Stack<Double> operands, Stack<Operation> operators) {
        Operation operator = operators.pop();
        double op2 = operands.pop();
        double op1 = operands.isEmpty() ? 0 : operands.pop();
        double result = operator.apply(op1, op2);
        return result;
    }

}
