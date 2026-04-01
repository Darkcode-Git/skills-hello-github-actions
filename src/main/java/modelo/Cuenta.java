package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una cuenta bancaria básica en la Cooperativa CoopRKC.
 * Aplica encapsulamiento para proteger el saldo y valida operaciones.
 */
public class Cuenta {

    private final String numeroCuenta;
    private double saldo;
    private final List<String> historial;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta no puede estar vacío.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
        historial.add(String.format("Cuenta %s creada con saldo inicial: $%.2f", numeroCuenta, saldoInicial));
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<String> getHistorial() {
        return new ArrayList<>(historial);
    }

    /**
     * Realiza un depósito en la cuenta.
     *
     * @param monto el monto a depositar (debe ser positivo)
     */
    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a cero.");
        }
        saldo += monto;
        historial.add(String.format("Depósito: +$%.2f | Saldo actual: $%.2f", monto, saldo));
    }

    /**
     * Realiza un retiro de la cuenta.
     *
     * @param monto el monto a retirar (debe ser positivo y no superar el saldo)
     * @throws IllegalArgumentException si el monto es inválido o no hay saldo suficiente
     */
    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a cero.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException(
                String.format("Saldo insuficiente. Saldo disponible: $%.2f, Monto solicitado: $%.2f", saldo, monto)
            );
        }
        saldo -= monto;
        historial.add(String.format("Retiro: -$%.2f | Saldo actual: $%.2f", monto, saldo));
    }

    @Override
    public String toString() {
        return String.format("Cuenta[número=%s, saldo=$%.2f]", numeroCuenta, saldo);
    }
}
