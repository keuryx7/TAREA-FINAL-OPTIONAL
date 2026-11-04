package edu.uasd.inf5150.optional;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioCuentasTest {

    @Test
    void buscarPorNumeroDevuelveOptionalConValorSiLaCuentaExiste() {
        RepositorioCuentas repositorio = new RepositorioCuentas();
        repositorio.agregar(new CuentaBancaria("100460182", "KEURY BUENO", 10000));

        Optional<CuentaBancaria> resultado = repositorio.buscarPorNumero("100460182");

        assertTrue(resultado.isPresent());
        assertEquals("KEURY BUENO", resultado.get().getTitular());
    }

    @Test
    void buscarPorNumeroDevuelveOptionalVacioSiLaCuentaNoExiste() {
        RepositorioCuentas repositorio = new RepositorioCuentas();

        Optional<CuentaBancaria> resultado = repositorio.buscarPorNumero("999");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void rechazaAgregarUnaCuentaNula() {
        RepositorioCuentas repositorio = new RepositorioCuentas();

        assertThrows(IllegalArgumentException.class,
                () -> repositorio.agregar(null));
    }
}