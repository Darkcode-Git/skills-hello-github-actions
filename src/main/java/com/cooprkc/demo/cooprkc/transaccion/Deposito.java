package com.cooprkc.demo.cooprkc.transaccion;

import com.cooprkc.demo.cooprkc.model.CuentaAhorro;

public class Deposito implements Transaccion {

    private final CuentaAhorro cuenta;
    private final double monto;

    public Deposito(CuentaAhorro cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        cuenta.depositar(monto);
    }
}
