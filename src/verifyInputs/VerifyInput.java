package verifyInputs;

import operations.Operation;

import java.util.Stack;

public interface VerifyInput {


    Boolean isValidInput(String input);
    Boolean doubleTesting(String input);
    Boolean operatorTesting(String input);
    Boolean hasPrecedence(Operation op1, Operation op2);
    Double applyOperation(Stack<Double> operands, Stack<Operation> operators);


}
