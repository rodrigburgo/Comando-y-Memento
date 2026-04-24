package calculadora;

/**
 * Interfaz Comando — patrón Command.
 * Toda operación de la calculadora implementa este contrato.
 */
public interface Comando {
    void ejecutar();
    void deshacer();
}
