package com.cooprkc.demo.cooprkc.sistema;

import com.cooprkc.demo.cooprkc.model.CoopRKC;

public final class SistemaCoopRKC {

    private static volatile SistemaCoopRKC instancia;
    private CoopRKC cooperativa;

    private SistemaCoopRKC() {
    }

    public static SistemaCoopRKC getInstancia() {
        if (instancia == null) {
            synchronized (SistemaCoopRKC.class) {
                if (instancia == null) {
                    instancia = new SistemaCoopRKC();
                }
            }
        }
        return instancia;
    }

    public void inicializar(String nombre) {
        if (cooperativa == null) {
            cooperativa = new CoopRKC(nombre);
        }
    }

    public CoopRKC getCooperativa() {
        if (cooperativa == null) {
            throw new IllegalStateException("El sistema no ha sido inicializado.");
        }
        return cooperativa;
    }
}
