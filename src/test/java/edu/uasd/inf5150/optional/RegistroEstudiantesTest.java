package edu.uasd.inf5150.optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegistroEstudiantesTest {

    private RegistroEstudiantes registro;

    @BeforeEach
    void prepararDatos() {
        registro = new RegistroEstudiantes();
        registro.agregar(new Estudiante("100460182", "KEURY BUENO", 92.5));
        registro.agregar(new Estudiante("100460183", "ANA MARTINEZ", 65));
        registro.agregar(new Estudiante("100460184", "LUIS PEREZ", 70));
    }

    @Test
    void buscarDevuelveOptionalConValorSiExiste() {
        Optional<Estudiante> resultado = registro.buscarPorMatricula("100460182");

        assertTrue(resultado.isPresent());
        assertEquals("KEURY BUENO", resultado.get().getNombre());
    }

    @Test
    void buscarDevuelveOptionalVacioSiNoExiste() {
        assertTrue(registro.buscarPorMatricula("000").isEmpty());
    }

    @Test
    void obtenerPromedioDevuelveElPromedioSiExiste() {
        assertEquals(Optional.of(92.5), registro.obtenerPromedio("100460182"));
    }

    @Test
    void obtenerPromedioDevuelveVacioSiNoExiste() {
        assertTrue(registro.obtenerPromedio("000").isEmpty());
    }

    @Test
    void estadoAcademicoEsAprobadoConPromedioAlto() {
        assertEquals("APROBADO", registro.estadoAcademico("100460182"));
    }

    @Test
    void estadoAcademicoEsReprobadoConPromedioBajo() {
        assertEquals("REPROBADO", registro.estadoAcademico("100460183"));
    }

    @Test
    void estadoAcademicoEsAprobadoConPromedioExactamenteSetenta() {
        assertEquals("APROBADO", registro.estadoAcademico("100460184"));
    }

    @Test
    void estadoAcademicoIndicaQueNoSeEncontroAlEstudiante() {
        assertEquals("ESTUDIANTE NO ENCONTRADO", registro.estadoAcademico("000"));
    }

    @Test
    void obtenerFichaDevuelveElEstudianteSiExiste() throws Exception {
        Estudiante estudiante = registro.obtenerFicha("100460183");

        assertEquals("ANA MARTINEZ", estudiante.getNombre());
    }

    @Test
    void obtenerFichaLanzaExcepcionSiNoExiste() {
        EstudianteNoEncontradoException excepcion = assertThrows(EstudianteNoEncontradoException.class,
                () -> registro.obtenerFicha("000"));

        assertEquals("NO EXISTE UN ESTUDIANTE CON LA MATRICULA 000", excepcion.getMessage());
    }

    @Test
    void estudianteRechazaPromedioMayorACien() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("1", "PRUEBA", 101));
    }

    @Test
    void estudianteRechazaPromedioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("1", "PRUEBA", -1));
    }
}