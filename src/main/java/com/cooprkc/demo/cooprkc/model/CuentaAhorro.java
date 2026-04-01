package com.cooprkc.demo.cooprkc.model;

public class CuentaAhorro {

    private final String numero;
    private double saldo;

    public CuentaAhorro(String numero, double saldoInicial) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de cuenta es obligatorio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
        }
        saldo += monto;
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
        }
        saldo -= monto;
    }
}
