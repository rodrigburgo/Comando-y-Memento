package calculadora;

/**
 * MementoCalculadora — patrón Memento.
 * Encapsula el estado de Calculadora.
 * Constructor y getter con acceso de paquete: solo Calculadora puede usarlos.
 */
public class MementoCalculadora {

    private final double valorGuardado;

    MementoCalculadora(double valorGuardado) {
        this.valorGuardado = valorGuardado;
    }

    double getValorGuardado() {
        return valorGuardado;
    }
}
