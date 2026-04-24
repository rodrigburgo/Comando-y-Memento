package calculadora;

/**
 * Comando concreto: borrar resultado actual.
 * Guarda el valorPrevio para restaurarlo en deshacer().
 */
public class ComandoBorrar implements Comando {

    private final Calculadora calculadora;
    private double valorPrevio;

    public ComandoBorrar(Calculadora calculadora) {
        this.calculadora = calculadora;
    }

    @Override
    public void ejecutar() {
        valorPrevio = calculadora.getValor();
        calculadora.borrar();
    }

    @Override
    public void deshacer() {
        calculadora.setValor(valorPrevio);
    }
}
