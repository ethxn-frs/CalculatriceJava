package verifyInputs;

import operations.Operation;

public interface VerifyInput {


    Boolean isValidInput(String input);

    Boolean doubleTesting(String input);

    Boolean operatorTesting(String input);

    Boolean hasPrecedence(Operation op1, Operation op2);


}
