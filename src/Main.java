import verifyInputs.DefaultVerifyInput;
import verifyInputs.VerifyInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        VerifyInput verifyInput = new DefaultVerifyInput();
        Calculator calculator = new Calculator(verifyInput);
        List<String> inputHistory = new ArrayList<>();
        boolean start = true;

        while (start) {

            System.out.println("Veuillez saisir un caractère " + inputHistory + " :");
            String input = scanner.nextLine();

            if (input.equals("=")) {

                calculator.calculate();
                int restart;

                do {
                    System.out.println("Voulez-vous relancer ? 1 - Oui / 0 - Non");
                    try {
                        restart = scanner.nextInt();
                        scanner.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Veuillez entrer un nombre valide (1 ou 0).");
                        scanner.nextLine();
                        restart = -1;
                    }
                }

                while (restart != 1 && restart != 0);
                if (restart == 1) {
                    calculator.reset();
                    inputHistory.clear();
                } else {
                    start = false;
                }
            } else {
                String previousInput = calculator.processInput(input);
                if (previousInput != null) {
                    inputHistory.add(previousInput);
                }
            }
        }
        System.out.println("Arrêt de la calculatrice");
    }
}