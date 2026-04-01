package transacciones;

import modelo.Cuenta;

/**
 * Representa una transacción de retiro sobre una cuenta.
 * Implementa la interfaz Transaccion e incluye gestión de errores
 * para cuando el saldo no es suficiente.
 */
public class Retiro implements Transaccion {

    private final Cuenta cuenta;
    private final double monto;

    public Retiro(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero.");
        }
        this.cuenta = cuenta;
        this.monto = monto;
    }

    /**
     * Ejecuta el retiro con gestión de errores.
     * Si el saldo no es suficiente, muestra un mensaje adecuado al usuario.
     */
    @Override
    public void ejecutar() {
        try {
            cuenta.retirar(monto);
            System.out.printf("  [RETIRO]   Cuenta %s: -$%.2f | Nuevo saldo: $%.2f%n",
                cuenta.getNumeroCuenta(), monto, cuenta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.printf("  [ERROR]    Retiro fallido en cuenta %s: %s%n",
                cuenta.getNumeroCuenta(), e.getMessage());
        }
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return String.format("Retiro[cuenta=%s, monto=$%.2f]",
            cuenta.getNumeroCuenta(), monto);
    }
}
