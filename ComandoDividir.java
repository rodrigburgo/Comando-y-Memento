package calculadora;

/** Comando concreto: división. */
public class ComandoDividir implements Comando {

    private final Calculadora calculadora;
    private final double operando;

    public ComandoDividir(Calculadora calculadora, double operando) {
        this.calculadora = calculadora;
        this.operando = operando;
    }

    @Override
    public void ejecutar() {
        calculadora.dividir(operando);
    }

    @Override
    public void deshacer() {
        calculadora.multiplicar(operando);
    }
}
