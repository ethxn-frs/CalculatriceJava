package operations;

public class Modulo implements Operation {

    @Override
    public double apply(double operand1, double operand2) {
        try {
            if (operand2 == 0) {
                throw new ArithmeticException("Le modulo par 0 n'est pas autorisée");
            }
            if ( operand1 < 0 || operand2 < 0 ) {
                throw new ArithmeticException("Le modulo avec un nombre négatif n'est pas autorisé");
            }
            return operand1 % operand2;
        } catch (ArithmeticException e) {
            System.err.println("Erreur : " + e.getMessage());
            return Double.NaN;
        }
    }
}
