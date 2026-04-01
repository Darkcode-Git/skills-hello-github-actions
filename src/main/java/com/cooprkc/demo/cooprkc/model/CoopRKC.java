package com.cooprkc.demo.cooprkc.model;

import java.util.ArrayList;
import java.util.List;

public class CoopRKC {

    private final String nombre;
    private final List<Socio> socios;

    public CoopRKC(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la cooperativa es obligatorio.");
        }
        this.nombre = nombre;
        this.socios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarSocio(Socio socio) {
        if (socio == null) {
            throw new IllegalArgumentException("No se puede registrar un socio nulo.");
        }
        boolean existe = socios.stream().anyMatch(s -> s.getIdentificacion().equals(socio.getIdentificacion()));
        if (existe) {
            throw new IllegalArgumentException("Ya existe un socio con la identificación indicada.");
        }
        socios.add(socio);
    }

    public List<Socio> getSocios() {
        return new ArrayList<>(socios);
    }
}
