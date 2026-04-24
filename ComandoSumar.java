package calculadora;

/** Comando concreto: suma. */
public class ComandoSumar implements Comando {

    private final Calculadora calculadora;
    private final double operando;

    public ComandoSumar(Calculadora calculadora, double operando) {
        this.calculadora = calculadora;
        this.operando = operando;
    }

    @Override
    public void ejecutar() {
        calculadora.sumar(operando);
    }

    @Override
    public void deshacer() {
        calculadora.restar(operando);
    }
}
