package calculadora;

/**
 * Calculadora — Originador y Receptor.
 * Mantiene el valorActual y expone operaciones aritméticas.
 * Nunca imprime nada; solo expone su estado mediante getValor().
 */
public class Calculadora {

    private double valorActual;

    public Calculadora() {
        this.valorActual = 0;
    }

    public double getValor() {
        return valorActual;
    }

    public void setValor(double valor) {
        this.valorActual = valor;
    }

    public void sumar(double operando) {
        valorActual += operando;
    }

    public void restar(double operando) {
        valorActual -= operando;
    }

    public void multiplicar(double operando) {
        valorActual *= operando;
    }

    public void dividir(double operando) {
        if (operando == 0) {
            throw new ArithmeticException("División por cero no permitida.");
        }
        valorActual /= operando;
    }

    public void borrar() {
        valorActual = 0;
    }

    /** Patrón Memento: guarda el estado actual en un memento. */
    public MementoCalculadora guardarEstado() {
        return new MementoCalculadora(valorActual);
    }

    /** Patrón Memento: restaura el estado desde un memento. */
    public void restaurarEstado(MementoCalculadora memento) {
        this.valorActual = memento.getValorGuardado();
    }
}
