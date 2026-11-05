package edu.uasd.inf5150.optional;

public class Estudiante {

    private final String matricula;
    private final String nombre;
    private final double promedio;

    public Estudiante(String matricula, String nombre, double promedio) {
        if (promedio < 0 || promedio > 100) {
            throw new IllegalArgumentException("EL PROMEDIO DEBE ESTAR ENTRE 0 Y 100");
        }

        this.matricula = matricula;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public String mostrarInformacion() {
        return String.format("MATRICULA:  %s  ~  NOMBRE:  %s  ~  PROMEDIO:  %.2f",
                matricula, nombre, promedio);
    }
}