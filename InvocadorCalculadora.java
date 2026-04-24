package calculadora;

/**
 * InvocadorCalculadora — Invocador (patrón Command + Memento).
 * Orquesta la ejecución, el deshacer y el rehacer sin conocer
 * la lógica de ninguna operación concreta.
 */
public class InvocadorCalculadora {

    private final Calculadora calculadora;
    private final Historial historial;

    public InvocadorCalculadora(Calculadora calculadora, Historial historial) {
        this.calculadora = calculadora;
        this.historial   = historial;
    }

    public void ejecutarComando(Comando comando) {
        double valorAntes = calculadora.getValor();
        historial.guardar(calculadora.guardarEstado(), describir(comando, valorAntes));
        comando.ejecutar();
    }

    public void deshacer() {
        MementoCalculadora anterior = historial.deshacer();
        calculadora.restaurarEstado(anterior);
    }

    public void rehacer() {
        MementoCalculadora siguiente = historial.rehacer();
        calculadora.restaurarEstado(siguiente);
    }

    public boolean puedeDeshacer() {
        return historial.puedeDeshacer();
    }

    public boolean puedeRehacer() {
        return historial.puedeRehacer();
    }

    /** Genera una descripción legible del comando antes de ejecutarse. */
    private String describir(Comando comando, double valorAntes) {
        String tipo = comando.getClass().getSimpleName();
        switch (tipo) {
            case "ComandoSumar":
                return "Valor " + valorAntes + "  →  sumar";
            case "ComandoRestar":
                return "Valor " + valorAntes + "  →  restar";
            case "ComandoMultiplicar":
                return "Valor " + valorAntes + "  →  multiplicar";
            case "ComandoDividir":
                return "Valor " + valorAntes + "  →  dividir";
            case "ComandoBorrar":
                return "Valor " + valorAntes + "  →  borrar";
            default:
                return "Valor " + valorAntes + "  →  " + tipo;
        }
    }
}
