package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un socio de la Cooperativa CoopRKC.
 * Cada socio tiene un nombre, cédula y una lista de cuentas de ahorro.
 * Aplica encapsulamiento y valida que no haya números de cuenta duplicados.
 */
public class Socio {

    private final String nombre;
    private final String cedula;
    private final List<Cuenta> cuentas;

    public Socio(String nombre, String cedula) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del socio no puede estar vacío.");
        }
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula del socio no puede estar vacía.");
        }
        this.nombre = nombre;
        this.cedula = cedula;
        this.cuentas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas);
    }

    /**
     * Abre una nueva cuenta para el socio.
     * Valida que no exista ya una cuenta con el mismo número.
     *
     * @param cuenta la cuenta a agregar
     * @throws IllegalArgumentException si ya existe una cuenta con ese número
     */
    public void abrirCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        boolean yaExiste = cuentas.stream()
            .anyMatch(c -> c.getNumeroCuenta().equals(cuenta.getNumeroCuenta()));
        if (yaExiste) {
            throw new IllegalArgumentException(
                String.format("El socio %s ya tiene una cuenta con el número: %s",
                    nombre, cuenta.getNumeroCuenta())
            );
        }
        cuentas.add(cuenta);
    }

    /**
     * Retorna la suma total del saldo de todas las cuentas del socio.
     *
     * @return el saldo total del socio
     */
    public double getSaldoTotal() {
        return cuentas.stream()
            .mapToDouble(Cuenta::getSaldo)
            .sum();
    }

    @Override
    public String toString() {
        return String.format("Socio[nombre=%s, cédula=%s, cuentas=%d]",
            nombre, cedula, cuentas.size());
    }
}
