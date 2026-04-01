package app;

import modelo.Cooperativa;
import modelo.Cuenta;
import modelo.CuentaAhorros;
import modelo.Socio;
import modelo.SistemaCooperativa;
import transacciones.Deposito;
import transacciones.Retiro;
import transacciones.Transaccion;
import transacciones.TransaccionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que demuestra el funcionamiento del sistema
 * de la Cooperativa CoopRKC.
 *
 * Aplica:
 * - POO: encapsulamiento, herencia, polimorfismo, abstracción
 * - Programación funcional: streams, lambdas, filter, map, reduce
 * - Gestión de errores: try-catch en retiros y validaciones
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA COOPERATIVA CoopRKC              ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ── 1. Crear la cooperativa usando Singleton ─────────────────────
        SistemaCooperativa sistema = SistemaCooperativa.getInstancia();
        sistema.inicializar("CoopRKC");
        Cooperativa cooperativa = sistema.getCooperativa();

        // ── 2. Registrar socios ─────────────────────────────────────────
        Socio socio1 = new Socio("Ana García", "1001234567");
        Socio socio2 = new Socio("Luis Martínez", "1007654321");
        Socio socio3 = new Socio("María Rodríguez", "1009876543");
        Socio socio4 = new Socio("Carlos Pérez", "1002468135");

        cooperativa.registrarSocio(socio1);
        cooperativa.registrarSocio(socio2);
        cooperativa.registrarSocio(socio3);
        cooperativa.registrarSocio(socio4);

        // ── 3. Abrir cuentas de ahorro ──────────────────────────────────
        CuentaAhorros ca1 = new CuentaAhorros("CA-001", 800000.00, 3.5);
        CuentaAhorros ca2 = new CuentaAhorros("CA-002", 250000.00, 3.5);
        CuentaAhorros ca3 = new CuentaAhorros("CA-003", 1200000.00, 4.0);
        CuentaAhorros ca4 = new CuentaAhorros("CA-004", 450000.00, 3.0);
        CuentaAhorros ca5 = new CuentaAhorros("CA-005", 600000.00, 3.5);

        socio1.abrirCuenta(ca1);
        socio1.abrirCuenta(ca2);
        socio2.abrirCuenta(ca3);
        socio3.abrirCuenta(ca4);
        socio4.abrirCuenta(ca5);

        // ── 4. Demostrar validación de cuenta duplicada ─────────────────
        System.out.println("\n=== Validación: Cuenta duplicada ===");
        try {
            CuentaAhorros duplicada = new CuentaAhorros("CA-001", 100000.00, 2.0);
            socio1.abrirCuenta(duplicada);
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }

        // ── 5. Demostrar validación de cédula duplicada ─────────────────
        System.out.println("\n=== Validación: Socio con cédula duplicada ===");
        try {
            Socio socioDuplicado = new Socio("Ana Duplicada", "1001234567");
            cooperativa.registrarSocio(socioDuplicado);
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }

        // ── 6. Realizar operaciones de depósito y retiro ─────────────────
        System.out.println("\n=== Operaciones de depósito y retiro ===");

        // Usando polimorfismo + Factory Method
        List<Transaccion> transacciones = Arrays.asList(
            TransaccionFactory.crearTransaccion("DEPOSITO", ca1, 300000.00),
            TransaccionFactory.crearTransaccion("DEPOSITO", ca3, 500000.00),
            TransaccionFactory.crearTransaccion("RETIRO", ca2, 100000.00),
            TransaccionFactory.crearTransaccion("RETIRO", ca4, 200000.00),
            // Retiro con saldo insuficiente – gestión de errores
            TransaccionFactory.crearTransaccion("RETIRO", ca2, 999999.00)
        );

        // Programación funcional: ejecutar todas las transacciones con forEach
        transacciones.forEach(Transaccion::ejecutar);

        // ── 7. Aplicar interés a las cuentas de ahorro ──────────────────
        System.out.println("\n=== Aplicando interés a cuentas de ahorro ===");
        cooperativa.getCuentasAhorros().forEach(CuentaAhorros::aplicarInteres);

        // ── 8. Listar socios (map + forEach) ────────────────────────────
        cooperativa.listarSocios();

        // ── 9. Filtrar cuentas con saldo > $500.000 ─────────────────────
        cooperativa.listarCuentasConSaldoMayorA(500000.00);

        // ── 10. Calcular total del dinero en la cooperativa (reduce) ─────
        double totalDinero = cooperativa.calcularTotalDinero();
        System.out.printf("%n=== Total del dinero en la cooperativa ===%n");
        System.out.printf("  Total: $%.2f%n", totalDinero);

        // ── 11. Resumen de saldos por socio ─────────────────────────────
        System.out.println("\n=== Resumen de saldos por socio ===");
        cooperativa.getSocios().stream()
            .map(s -> String.format("  %-20s | Saldo total: $%.2f",
                s.getNombre(), s.getSaldoTotal()))
            .forEach(System.out::println);

        // ── 12. Validación de entrada con try-catch-finally ─────────────
        System.out.println("\n=== Validación de entrada de usuario ===");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Ingrese un monto para validación (ejemplo 150000): ");
            String entrada = scanner.nextLine();
            double monto = Double.parseDouble(entrada);
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor que cero.");
            }
            System.out.printf("  Monto válido ingresado: $%.2f%n", monto);
        } catch (NumberFormatException e) {
            System.out.println("  [ERROR] Entrada inválida: debe ingresar un número.");
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        } finally {
            System.out.println("  Validación finalizada. El programa continúa sin detenerse.");
        }

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║     FIN DE LA DEMOSTRACIÓN CoopRKC           ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
