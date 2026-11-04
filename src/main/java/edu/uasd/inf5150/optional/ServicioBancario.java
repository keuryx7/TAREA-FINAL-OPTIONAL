package edu.uasd.inf5150.optional;

import java.util.Optional;

public class ServicioBancario {

    private final RepositorioCuentas repositorio;

    public ServicioBancario(RepositorioCuentas repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("EL REPOSITORIO NO PUEDE SER NULO");
        }
        this.repositorio = repositorio;
    }

    public Optional<Double> obtenerSaldo(String numeroCuenta) {
        return repositorio.buscarPorNumero(numeroCuenta)
                .map(CuentaBancaria::getSaldo);
    }

    public String obtenerTitular(String numeroCuenta) {
        return repositorio.buscarPorNumero(numeroCuenta)
                .map(CuentaBancaria::getTitular)
                .orElse("TITULAR DESCONOCIDO");
    }

    public Optional<CuentaBancaria> buscarConSaldoMinimo(String numeroCuenta, double minimo) {
        return repositorio.buscarPorNumero(numeroCuenta)
                .filter(cuenta -> cuenta.getSaldo() >= minimo);
    }
}