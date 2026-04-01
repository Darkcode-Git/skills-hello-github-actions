package modelo;

/**
 * Representa una cuenta de ahorros en la Cooperativa CoopRKC.
 * Hereda de Cuenta y agrega un porcentaje de interés que se puede aplicar al saldo.
 * Demuestra herencia y polimorfismo de la POO.
 */
public class CuentaAhorros extends Cuenta {

    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        if (tasaInteres < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa.");
        }
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        if (tasaInteres < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa.");
        }
        this.tasaInteres = tasaInteres;
    }

    /**
     * Aplica el interés al saldo actual de la cuenta de ahorros.
     * El monto del interés se deposita en la cuenta como un depósito.
     */
    public void aplicarInteres() {
        double interes = getSaldo() * tasaInteres / 100.0;
        if (interes > 0) {
            depositar(interes);
            System.out.printf("  Interés aplicado a cuenta %s: +$%.2f (%.2f%%)%n",
                getNumeroCuenta(), interes, tasaInteres);
        }
    }

    @Override
    public String toString() {
        return String.format("CuentaAhorros[número=%s, saldo=$%.2f, interés=%.2f%%]",
            getNumeroCuenta(), getSaldo(), tasaInteres);
    }
}
