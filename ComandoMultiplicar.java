package calculadora;

/** Comando concreto: multiplicación. */
public class ComandoMultiplicar implements Comando {

    private final Calculadora calculadora;
    private final double operando;

    public ComandoMultiplicar(Calculadora calculadora, double operando) {
        this.calculadora = calculadora;
        this.operando = operando;
    }

    @Override
    public void ejecutar() {
        calculadora.multiplicar(operando);
    }

    @Override
    public void deshacer() {
        if (operando == 0) {
            throw new ArithmeticException("No se puede deshacer multiplicación por cero.");
        }
        calculadora.dividir(operando);
    }
}
