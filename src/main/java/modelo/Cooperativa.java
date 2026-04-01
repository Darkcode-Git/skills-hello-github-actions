package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa la Cooperativa CoopRKC.
 * Mantiene el registro de socios y proporciona métodos funcionales
 * para analizar la información de la cooperativa.
 */
public class Cooperativa {

    private final String nombre;
    private final List<Socio> socios;

    public Cooperativa(String nombre) {
        this.nombre = nombre;
        this.socios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Socio> getSocios() {
        return new ArrayList<>(socios);
    }

    /**
     * Registra un nuevo socio en la cooperativa.
     * Valida que no exista ya un socio con la misma cédula.
     *
     * @param socio el socio a registrar
     * @throws IllegalArgumentException si ya existe un socio con esa cédula
     */
    public void registrarSocio(Socio socio) {
        if (socio == null) {
            throw new IllegalArgumentException("El socio no puede ser nulo.");
        }
        boolean yaExiste = socios.stream()
            .anyMatch(s -> s.getCedula().equals(socio.getCedula()));
        if (yaExiste) {
            throw new IllegalArgumentException(
                String.format("Ya existe un socio con la cédula: %s", socio.getCedula())
            );
        }
        socios.add(socio);
    }

    /**
     * Lista los nombres de todos los socios registrados usando map + forEach.
     */
    public void listarSocios() {
        System.out.println("\n=== Socios registrados en " + nombre + " ===");
        socios.stream()
            .map(s -> String.format("  - %s (Cédula: %s)", s.getNombre(), s.getCedula()))
            .forEach(System.out::println);
    }

    /**
     * Filtra y muestra las cuentas con saldo mayor al valor especificado.
     * Usa programación funcional con filter y forEach.
     *
     * @param saldoMinimo el saldo mínimo para filtrar
     */
    public void listarCuentasConSaldoMayorA(double saldoMinimo) {
        System.out.printf("%n=== Cuentas con saldo mayor a $%.2f ===%n", saldoMinimo);
        socios.stream()
            .flatMap(socio -> socio.getCuentas().stream()
                .filter(cuenta -> cuenta.getSaldo() > saldoMinimo)
                .map(cuenta -> String.format("  Socio: %-20s | Cuenta: %-10s | Saldo: $%.2f",
                    socio.getNombre(), cuenta.getNumeroCuenta(), cuenta.getSaldo())))
            .forEach(System.out::println);
    }

    /**
     * Calcula el total del dinero en la cooperativa usando reduce.
     *
     * @return la suma total de todos los saldos de todas las cuentas
     */
    public double calcularTotalDinero() {
        return socios.stream()
            .flatMap(socio -> socio.getCuentas().stream())
            .mapToDouble(Cuenta::getSaldo)
            .reduce(0.0, Double::sum);
    }

    /**
     * Retorna la lista de cuentas de ahorro de todos los socios.
     *
     * @return lista de CuentaAhorros
     */
    public List<CuentaAhorros> getCuentasAhorros() {
        return socios.stream()
            .flatMap(socio -> socio.getCuentas().stream())
            .filter(cuenta -> cuenta instanceof CuentaAhorros)
            .map(cuenta -> (CuentaAhorros) cuenta)
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Cooperativa[nombre=%s, socios=%d]", nombre, socios.size());
    }
}
