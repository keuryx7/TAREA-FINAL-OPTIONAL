package edu.uasd.inf5150.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistroEstudiantes {

    private final List<Estudiante> estudiantes = new ArrayList<>();

    public void agregar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> listar() {
        return List.copyOf(estudiantes);
    }

    public Optional<Estudiante> buscarPorMatricula(String matricula) {
        return estudiantes.stream()
                .filter(estudiante -> estudiante.getMatricula().equals(matricula))
                .findFirst();
    }

    public Optional<Double> obtenerPromedio(String matricula) {
        return buscarPorMatricula(matricula).map(Estudiante::getPromedio);
    }

    public String estadoAcademico(String matricula) {
        return buscarPorMatricula(matricula)
                .map(estudiante -> estudiante.getPromedio() >= 70 ? "APROBADO" : "REPROBADO")
                .orElse("ESTUDIANTE NO ENCONTRADO");
    }

    public Estudiante obtenerFicha(String matricula) throws EstudianteNoEncontradoException {
        return buscarPorMatricula(matricula)
                .orElseThrow(() -> new EstudianteNoEncontradoException(
                        "NO EXISTE UN ESTUDIANTE CON LA MATRICULA " + matricula));
    }
}