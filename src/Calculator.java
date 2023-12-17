import operations.*;
import verifyInputs.VerifyInput;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final List<String> characters = new ArrayList<>();
    private Operation currentOperation;
    private VerifyInput verifyInput;

    public Calculator(VerifyInput verifyInput) {
        this.verifyInput = verifyInput;
    }

    public String processInput(String input) {
        
        if (verifyInput.isValidInput(input)){

            if (characters.isEmpty()){

                // Check si la première entrée est un nombre
                if (verifyInput.doubleTesting(input)){

                    characters.add(input);
                    return input;
                } else {
                    System.out.println("La première entrée doit être un nombre !");
                }

            } else {

                String lastInput = characters.get(characters.size() - 1);

                if (verifyInput.doubleTesting(lastInput) && verifyInput.operatorTesting(input)){

                    // Cas ou le user entre un opérateur apres un nombre
                    characters.add(input);
                    return input;
                } else if (verifyInput.operatorTesting(lastInput) && verifyInput.doubleTesting(input)){

                    // Cas ou le user entre un nombre apres un opérateur
                    characters.add(input);
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

        double result = 0;
        double operand = 0;

        for ( String s : characters){

            if (verifyInput.operatorTesting(s) && s.length() < 2){
                setOperation(s);
            } else {
                try {
                    operand = operand * 10 + Double.parseDouble(s);
                    if (currentOperation != null){
                        result = currentOperation.apply(result, operand);

                    } else {
                        result = operand;
                    }
                    operand = 0;

                } catch (Exception e){
                    System.err.println("Erreur : " + e);
                }
            }
        }

        System.out.println(" Résultat : " + result);
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
        currentOperation = null;
    }
}
