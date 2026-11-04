package edu.uasd.inf5150.optional;

public class CuentaBancaria {

    private final String numeroCuenta;
    private final String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("EL NUMERO DE CUENTA NO PUEDE ESTAR VACIO");
        }
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("EL TITULAR NO PUEDE ESTAR VACIO");
        }
        if (saldo < 0) {
            throw new IllegalArgumentException("EL SALDO INICIAL NO PUEDE SER NEGATIVO");
        }

        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public String mostrarInformacion() {
        return String.format("CUENTA:  %s  ~  TITULAR:  %s  ~  SALDO:  %.2f",
                numeroCuenta, titular, saldo);
    }
}