package edu.uasd.inf5150.optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class ServicioBancarioTest {

    private CuentaBancaria cuentaKeury;
    private ServicioBancario servicio;

    @BeforeEach
    void prepararDatos() {
        cuentaKeury = new CuentaBancaria("100460182", "KEURY BUENO", 10000);
        CuentaBancaria cuentaAna = new CuentaBancaria("9606652456", "ANA MARTINEZ", 5000);

        RepositorioCuentas repositorio = new RepositorioCuentas();
        repositorio.agregar(cuentaKeury);
        repositorio.agregar(cuentaAna);

        servicio = new ServicioBancario(repositorio);
    }

    @Test
    void obtenerSaldoDevuelveElSaldoSiLaCuentaExiste() {
        assertEquals(Optional.of(10000.0), servicio.obtenerSaldo("100460182"));
    }

    @Test
    void obtenerSaldoDevuelveVacioSiLaCuentaNoExiste() {
        assertTrue(servicio.obtenerSaldo("000").isEmpty());
    }

    @Test
    void obtenerTitularDevuelveElTitularSiLaCuentaExiste() {
        assertEquals("KEURY BUENO", servicio.obtenerTitular("100460182"));
    }

    @Test
    void obtenerTitularDevuelveValorPorDefectoSiLaCuentaNoExiste() {
        assertEquals("TITULAR DESCONOCIDO", servicio.obtenerTitular("000"));
    }

    @Test
    void buscarConSaldoMinimoDevuelveLaCuentaSiCumple() {
        assertTrue(servicio.buscarConSaldoMinimo("100460182", 5000).isPresent());
    }

    @Test
    void buscarConSaldoMinimoDevuelveVacioSiNoCumple() {
        assertTrue(servicio.buscarConSaldoMinimo("100460182", 20000).isEmpty());
    }

    @Test
    void buscarConSaldoMinimoAceptaSaldoIgualAlMinimo() {
        assertTrue(servicio.buscarConSaldoMinimo("100460182", 10000).isPresent());
    }

    @Test
    void retirarDescuentaElSaldoDeLaCuenta() throws Exception {
        servicio.retirar("100460182", 3000);

        assertEquals(7000, cuentaKeury.getSaldo());
    }

    @Test
    void retirarPuedeVaciarLaCuentaPorCompleto() throws Exception {
        servicio.retirar("100460182", 10000);

        assertEquals(0, cuentaKeury.getSaldo());
    }

    @Test
    void lanzaCuentaNoEncontradaExceptionSiLaCuentaNoExiste() {
        CuentaNoEncontradaException excepcion = assertThrows(CuentaNoEncontradaException.class,
                () -> servicio.retirar("000", 100));

        assertEquals("LA CUENTA 000 NO EXISTE", excepcion.getMessage());
    }

    @Test
    void lanzaFondosInsuficientesExceptionSiNoAlcanzaElSaldo() {
        assertThrows(FondosInsuficientesException.class,
                () -> servicio.retirar("100460182", 50000));
    }

    @Test
    void elSaldoNoCambiaSiElRetiroFalla() {
        assertThrows(FondosInsuficientesException.class,
                () -> servicio.retirar("100460182", 50000));

        assertEquals(10000, cuentaKeury.getSaldo());
    }

    @Test
    void lanzaIllegalArgumentExceptionConCantidadNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.retirar("100460182", -50));
    }

    @Test
    void rechazaUnRepositorioNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new ServicioBancario(null));
    }
}