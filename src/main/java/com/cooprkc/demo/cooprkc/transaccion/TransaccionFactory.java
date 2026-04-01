package com.cooprkc.demo.cooprkc.transaccion;

import com.cooprkc.demo.cooprkc.model.CuentaAhorro;

public final class TransaccionFactory {

    private TransaccionFactory() {
    }

    public static Transaccion crear(String tipo, CuentaAhorro cuenta, double monto) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el tipo de transacción.");
        }
        switch (tipo.trim().toUpperCase()) {
            case "DEPOSITO":
                return new Deposito(cuenta, monto);
            case "RETIRO":
                return new Retiro(cuenta, monto);
            default:
                throw new IllegalArgumentException("Tipo de transacción no soportado: " + tipo);
        }
    }
}
