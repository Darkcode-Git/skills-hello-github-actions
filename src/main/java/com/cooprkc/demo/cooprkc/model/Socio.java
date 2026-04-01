package com.cooprkc.demo.cooprkc.model;

import java.util.ArrayList;
import java.util.List;

public class Socio extends Persona {

    private final List<CuentaAhorro> cuentas;

    public Socio(String identificacion, String nombre) {
        super(identificacion, nombre);
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaAhorro cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        cuentas.add(cuenta);
    }

    public List<CuentaAhorro> getCuentas() {
        return new ArrayList<>(cuentas);
    }
}
