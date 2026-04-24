package calculadora;

import java.util.Scanner;

/**
 * Rodrigo Alonso Figueroa Burgos
 * Andres David Murillo Castro
 * Juan Sebastian Gutierrez Cuadros
 */
public class Principal {

    public static void main(String[] args) {

        Calculadora calculadora        = new Calculadora();
        Historial historial            = new Historial();
        InvocadorCalculadora invocador = new InvocadorCalculadora(calculadora, historial);
        Scanner scanner                = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Calculadora — Memento y Command    ║");
        System.out.println("╚══════════════════════════════════════╝");

        boolean activo = true;

        while (activo) {
            System.out.println("\nValor actual: " + formatear(calculadora.getValor()));
            System.out.println("────────────────────────────────────");
            System.out.println(" 1. Sumar");
            System.out.println(" 2. Restar");
            System.out.println(" 3. Multiplicar");
            System.out.println(" 4. Dividir");
            System.out.println(" 5. Borrar (poner en 0)");
            System.out.println(" 6. Deshacer");
            System.out.println(" 7. Rehacer");
            System.out.println(" 8. Ver historial de pasos");
            System.out.println(" 0. Salir");
            System.out.println("────────────────────────────────────");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine().trim();

            switch (entrada) {
                case "1":
                    double sumando = pedirNumero(scanner, "Número a sumar: ");
                    invocador.ejecutarComando(new ComandoSumar(calculadora, sumando));
                    System.out.println("✔ Resultado: " + formatear(calculadora.getValor()));
                    break;

                case "2":
                    double sustraendo = pedirNumero(scanner, "Número a restar: ");
                    invocador.ejecutarComando(new ComandoRestar(calculadora, sustraendo));
                    System.out.println("✔ Resultado: " + formatear(calculadora.getValor()));
                    break;

                case "3":
                    double factor = pedirNumero(scanner, "Número a multiplicar: ");
                    invocador.ejecutarComando(new ComandoMultiplicar(calculadora, factor));
                    System.out.println("✔ Resultado: " + formatear(calculadora.getValor()));
                    break;

                case "4":
                    double divisor = pedirNumero(scanner, "Número a dividir: ");
                    try {
                        invocador.ejecutarComando(new ComandoDividir(calculadora, divisor));
                        System.out.println("✔ Resultado: " + formatear(calculadora.getValor()));
                    } catch (ArithmeticException e) {
                        System.out.println("✘ Error: " + e.getMessage());
                    }
                    break;

                case "5":
                    invocador.ejecutarComando(new ComandoBorrar(calculadora));
                    System.out.println("✔ Resultado borrado: " + formatear(calculadora.getValor()));
                    break;

                case "6":
                    if (invocador.puedeDeshacer()) {
                        invocador.deshacer();
                        System.out.println("↩ Deshecho. Valor actual: " + formatear(calculadora.getValor()));
                    } else {
                        System.out.println("✘ No hay operaciones para deshacer.");
                    }
                    break;

                case "7":
                    if (invocador.puedeRehacer()) {
                        invocador.rehacer();
                        System.out.println("↪ Rehecho. Valor actual: " + formatear(calculadora.getValor()));
                    } else {
                        System.out.println("✘ No hay operaciones para rehacer.");
                    }
                    break;

                case "8":
                    System.out.println("\n── Historial de pasos ──");
                    String[] pasos = historial.obtenerPasos();
                    if (pasos.length == 0) {
                        System.out.println("  (sin operaciones registradas)");
                    } else {
                        for (int i = 0; i < pasos.length; i++) {
                            System.out.println("  " + (i + 1) + ". " + pasos[i]);
                        }
                    }
                    break;

                case "0":
                    activo = false;
                    System.out.println("\nSesión cerrada. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("✘ Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    /** Solicita un número al usuario con validación. */
    private static double pedirNumero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("✘ Entrada inválida. Ingrese un número válido.");
            }
        }
    }

    /** Formatea el valor: muestra entero si no tiene decimales. */
    private static String formatear(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
}
