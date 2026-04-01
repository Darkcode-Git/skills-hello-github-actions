package transacciones;

import modelo.Cuenta;

/**
 * Factory Method para crear objetos de tipo Transaccion.
 */
public final class TransaccionFactory {

    private TransaccionFactory() {
    }

    public static Transaccion crearTransaccion(String tipo, Cuenta cuenta, double monto) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar el tipo de transacción.");
        }

        switch (tipo.trim().toUpperCase()) {
            case "DEPOSITO":
                return new Deposito(cuenta, monto);
            case "RETIRO":
                return new Retiro(cuenta, monto);
            default:
                throw new IllegalArgumentException("Tipo de transacción no válido: " + tipo);
        }
    }
}
