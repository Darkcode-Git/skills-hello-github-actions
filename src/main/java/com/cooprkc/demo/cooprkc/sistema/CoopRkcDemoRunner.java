package com.cooprkc.demo.cooprkc.sistema;

import com.cooprkc.demo.cooprkc.model.CoopRKC;
import com.cooprkc.demo.cooprkc.model.CuentaAhorro;
import com.cooprkc.demo.cooprkc.model.Socio;
import com.cooprkc.demo.cooprkc.transaccion.Transaccion;
import com.cooprkc.demo.cooprkc.transaccion.TransaccionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoopRkcDemoRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        SistemaCoopRKC sistema = SistemaCoopRKC.getInstancia();
        sistema.inicializar("CoopRKC");
        CoopRKC coop = sistema.getCooperativa();

        Socio s1 = new Socio("1001", "Ana");
        Socio s2 = new Socio("1002", "Luis");

        CuentaAhorro c1 = new CuentaAhorro("AH-001", 500000);
        CuentaAhorro c2 = new CuentaAhorro("AH-002", 150000);
        CuentaAhorro c3 = new CuentaAhorro("AH-003", 800000);

        s1.agregarCuenta(c1);
        s1.agregarCuenta(c2);
        s2.agregarCuenta(c3);

        try {
            coop.registrarSocio(s1);
            coop.registrarSocio(s2);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        List<Transaccion> transacciones = Arrays.asList(
            TransaccionFactory.crear("DEPOSITO", c1, 200000),
            TransaccionFactory.crear("RETIRO", c2, 50000)
        );
        transacciones.forEach(Transaccion::ejecutar);

        List<String> sociosConSaldoAlto = coop.getSocios().stream()
            .filter(socio -> socio.getCuentas().stream().mapToDouble(CuentaAhorro::getSaldo).sum() > 400000)
            .map(Socio::getNombre)
            .collect(Collectors.toList());
        sociosConSaldoAlto.forEach(nombre -> System.out.println("Socio con saldo alto: " + nombre));

        String entradaMonto = "abc";
        try {
            double monto = Double.parseDouble(entradaMonto);
            TransaccionFactory.crear("RETIRO", c1, monto).ejecutar();
            System.out.println("Retiro realizado. Saldo actual cuenta AH-001: " + c1.getSaldo());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Debe ingresar un valor numérico válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } finally {
            System.out.println("Validación finalizada sin detener la aplicación.");
        }
    }
}
