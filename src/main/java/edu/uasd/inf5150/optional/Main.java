package edu.uasd.inf5150.optional;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);

        System.out.println("\n                TRABAJO FINAL");
        System.out.println("              KEURY BUENO 100460182");
        System.out.println("TEMA: USO AVANZADO DE OPTIONAL Y MANEJO FUNCIONAL DE ERRORES\n\n");

        RegistroEstudiantes registro = new RegistroEstudiantes();
        registro.agregar(new Estudiante("100460182", "KEURY BUENO", 92.5));
        registro.agregar(new Estudiante("100460183", "JUAN RAMIREZ", 65));
        registro.agregar(new Estudiante("100460184", "LUIS RODRIGUEZ", 70));

        while (true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1) CONSULTAR PROMEDIO");
            System.out.println("2) VER ESTADO ACADEMICO");
            System.out.println("3) VER FICHA DEL ESTUDIANTE");
            System.out.println("4) SALIR");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            int opcion = leerEntero(kb, "ESCRIBE LA OPCION: ");

            if (opcion == 4) {
                break;
            }
            if (opcion < 1 || opcion > 3) {
                System.out.println("OPCION INVALIDA\n");
                continue;
            }

            String matricula = seleccionarEstudiante(kb, registro);

            if (opcion == 1) {
                registro.obtenerPromedio(matricula).ifPresentOrElse(
                        promedio -> System.out.println(String.format("\nPROMEDIO:  %.2f", promedio)),
                        () -> System.out.println("\nEL ESTUDIANTE NO EXISTE")
                );
            } else if (opcion == 2) {
                System.out.println("\nESTADO ACADEMICO:  " + registro.estadoAcademico(matricula));
            } else {
                try {
                    Estudiante estudiante = registro.obtenerFicha(matricula);
                    System.out.println("\n" + estudiante.mostrarInformacion());
                } catch (EstudianteNoEncontradoException e) {
                    System.out.println("\nERROR:  " + e.getMessage());
                }
            }

            System.out.println();
        }

        System.out.println("\nFIN DEL PROGRAMA");
        kb.close();
    }

    private static String seleccionarEstudiante(Scanner kb, RegistroEstudiantes registro) {
        List<Estudiante> estudiantes = registro.listar();
        int opcionInexistente = estudiantes.size() + 1;

        while (true) {
            System.out.println("\nSELECCIONE EL ESTUDIANTE:\n");
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante estudiante = estudiantes.get(i);
                System.out.println((i + 1) + ") " + estudiante.getNombre() + " " + estudiante.getMatricula());
            }
            System.out.println(opcionInexistente + ") ESTUDIANTE INEXISTENTE (DEMOSTRACION)");

            int opcion = leerEntero(kb, "\nESCRIBE LA OPCION: ");

            if (opcion >= 1 && opcion <= estudiantes.size()) {
                return estudiantes.get(opcion - 1).getMatricula();
            }
            if (opcion == opcionInexistente) {
                return "000";
            }
            System.out.println("OPCION INVALIDA");
        }
    }

    private static int leerEntero(Scanner kb, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = kb.nextLine();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("ESTE RENGLON SOLO ACEPTA NUMEROS");
            }
        }
    }
}