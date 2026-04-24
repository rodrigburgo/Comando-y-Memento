package calculadora;

/** Comando concreto: resta. */
public class ComandoRestar implements Comando {

    private final Calculadora calculadora;
    private final double operando;

    public ComandoRestar(Calculadora calculadora, double operando) {
        this.calculadora = calculadora;
        this.operando = operando;
    }

    @Override
    public void ejecutar() {
        calculadora.restar(operando);
    }

    @Override
    public void deshacer() {
        calculadora.sumar(operando);
    }
}
