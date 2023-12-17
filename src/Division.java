public class Division implements Operation{

    @Override
    public double apply(double operand1, double operand2) {
        try {
            if (operand2 == 0) {
                throw new ArithmeticException("La division par 0 n'est pas autorisée.");
            }
            return operand1 / operand2;
        } catch (ArithmeticException e) {
            System.err.println("Erreur : " + e.getMessage());
            return Double.NaN;
        }
    }
}
