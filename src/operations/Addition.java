package operations;

public class Addition implements Operation {

    @Override
    public double apply(double op1, double op2){
        return op1 + op2;
    }
}
