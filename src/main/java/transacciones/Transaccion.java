package transacciones;

/**
 * Interfaz que define el contrato para todas las transacciones
 * que se pueden realizar en el sistema de la Cooperativa CoopRKC.
 */
public interface Transaccion {

    /**
     * Ejecuta la transacción sobre la cuenta correspondiente.
     */
    void ejecutar();

    /**
     * Retorna el monto de la transacción.
     *
     * @return el monto asociado a la transacción
     */
    double getMonto();
}
