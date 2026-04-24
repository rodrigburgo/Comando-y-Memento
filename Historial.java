package calculadora;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Historial — Guardián del patrón Memento.
 * Gestiona pilaDeshacer y pilaRehacer sin conocer
 * el contenido interno de MementoCalculadora.
 * Mantiene además una traza legible de pasos para el usuario.
 */
public class Historial {

    private final Deque<MementoCalculadora> pilaDeshacer = new ArrayDeque<>();
    private final Deque<MementoCalculadora> pilaRehacer  = new ArrayDeque<>();
    private final List<String> trazaPasos                = new ArrayList<>();

    public void guardar(MementoCalculadora memento, String descripcionPaso) {
        pilaDeshacer.push(memento);
        pilaRehacer.clear();
        trazaPasos.add(descripcionPaso);
    }

    public MementoCalculadora deshacer() {
        if (!puedeDeshacer()) {
            throw new IllegalStateException("No hay operaciones para deshacer.");
        }
        MementoCalculadora actual = pilaDeshacer.pop();
        pilaRehacer.push(actual);
        return pilaDeshacer.isEmpty() ? new MementoCalculadora(0) : pilaDeshacer.peek();
    }

    public MementoCalculadora rehacer() {
        if (!puedeRehacer()) {
            throw new IllegalStateException("No hay operaciones para rehacer.");
        }
        MementoCalculadora memento = pilaRehacer.pop();
        pilaDeshacer.push(memento);
        return memento;
    }

    public boolean puedeDeshacer() {
        return !pilaDeshacer.isEmpty();
    }

    public boolean puedeRehacer() {
        return !pilaRehacer.isEmpty();
    }

    /** Devuelve la traza de pasos registrados como arreglo de Strings. */
    public String[] obtenerPasos() {
        return trazaPasos.toArray(new String[0]);
    }
}
