import operations.*;
import verifyInputs.VerifyInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    private final List<String> characters = new ArrayList<>();
    private Operation currentOperation;
    private final VerifyInput verifyInput;

    public Calculator(VerifyInput verifyInput) {
        this.verifyInput = verifyInput;
    }
    private String finalSentence = "";
    
    public String processInput(String input) {
        
        if (verifyInput.isValidInput(input)){

            if (characters.isEmpty()){

                // Check si la première entrée est un nombre
                if (verifyInput.doubleTesting(input)){

                    characters.add(input);
                    finalSentence += " " + input;
                    return input;
                } else {
                    System.out.println("La première entrée doit être un nombre !");
                }

            } else {

                String lastInput = characters.get(characters.size() - 1);

                if (verifyInput.doubleTesting(lastInput) && verifyInput.operatorTesting(input)){

                    // Cas ou le user entre un opérateur apres un nombre
                    characters.add(input);
                    finalSentence += " " + input;
                    return input;
                } else if (verifyInput.operatorTesting(lastInput) && verifyInput.doubleTesting(input)){

                    // Cas ou le user entre un nombre apres un opérateur
                    characters.add(input);
                    finalSentence += " " + input;
                    return input;
                } else {
                    System.out.println("L'entrée n'est pas correcte, veuillez réessayer.");
                }
            }
        } else {
            System.out.println("L'entrée n'est pas correcte, veuillez réessayer.");
        }
        return null;
    }

    public void calculate() {
        Stack<Double> operands = new Stack<>();
        Stack<Operation> operators = new Stack<>();

        for (String s : characters) {
            if (verifyInput.operatorTesting(s) && s.length() < 2) {
                setOperation(s);
                while (!operators.isEmpty() && verifyInput.hasPrecedence(currentOperation, operators.peek())) {
                    operands.push(verifyInput.applyOperation(operands, operators));
                }
                operators.push(currentOperation);
            } else {
                try {
                    double operand = Double.parseDouble(s);
                    operands.push(operand);
                } catch (NumberFormatException e) {
                    System.out.println("Erreur : " + e);
                }
            }
        }
        // Appliquer les opérations restantes
        while (!operators.isEmpty()) {
            operands.push(verifyInput.applyOperation(operands, operators));
        }

        if (!operands.isEmpty()) {
            System.out.println(finalSentence + " = "  + operands.pop());
        }
    }

    private void setOperation(String operator) {
        switch (operator) {
            case "+":
                currentOperation = new Addition();
                break;
            case "-":
                currentOperation = new Subtraction();
                break;
            case "*":
                currentOperation = new Multiplication();
                break;
            case "/":
                currentOperation = new Division();
                break;
            case "%":
                currentOperation = new Modulo();
                break;
            default:
                throw new IllegalArgumentException("Opérateur non pris en charge : " + operator);
        }
    }

    public void reset(){
        this.characters.clear();
        this.finalSentence = "";
        currentOperation = null;
    }
}
