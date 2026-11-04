package edu.uasd.inf5150.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioCuentas {

    private final Map<String, CuentaBancaria> cuentas = new HashMap<>();

    public void agregar(CuentaBancaria cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("LA CUENTA NO PUEDE SER NULA");
        }
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    public Optional<CuentaBancaria> buscarPorNumero(String numeroCuenta) {
        return Optional.ofNullable(cuentas.get(numeroCuenta));
    }
}