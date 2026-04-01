package modelo;

/**
 * Singleton que administra el acceso al sistema principal de la cooperativa.
 */
public final class SistemaCooperativa {

    private static SistemaCooperativa instancia;
    private Cooperativa cooperativa;

    private SistemaCooperativa() {
        // Constructor privado para Singleton
    }

    public static synchronized SistemaCooperativa getInstancia() {
        if (instancia == null) {
            instancia = new SistemaCooperativa();
        }
        return instancia;
    }

    public void inicializar(String nombreCooperativa) {
        if (cooperativa == null) {
            cooperativa = new Cooperativa(nombreCooperativa);
        }
    }

    public Cooperativa getCooperativa() {
        if (cooperativa == null) {
            throw new IllegalStateException("El sistema no ha sido inicializado.");
        }
        return cooperativa;
    }
}
