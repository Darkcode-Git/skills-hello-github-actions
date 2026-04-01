package transacciones;

import modelo.Cuenta;

/**
 * Representa una transacción de depósito sobre una cuenta.
 * Implementa la interfaz Transaccion.
 */
public class Deposito implements Transaccion {

    private final Cuenta cuenta;
    private final double monto;

    public Deposito(Cuenta cuenta, double monto) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero.");
        }
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        cuenta.depositar(monto);
        System.out.printf("  [DEPÓSITO] Cuenta %s: +$%.2f | Nuevo saldo: $%.2f%n",
            cuenta.getNumeroCuenta(), monto, cuenta.getSaldo());
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return String.format("Deposito[cuenta=%s, monto=$%.2f]",
            cuenta.getNumeroCuenta(), monto);
    }
}
