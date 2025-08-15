/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica_1;

/**
 *
 * @author tepeu
 */
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nombres = new String[50];
        String[] armas = new String[50];
        String[][] habilidades = new String[50][5];
        int[] niveles = new int[50];

        // Peleas
        String[] historial = new String[100];
        int contadorPeleas = 0;

        int contador = 0; // cantidad de personajes
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Personajes ---");
            System.out.println("1. Agregar Personaje");
            System.out.println("2. Modificar Personaje");
            System.out.println("3. Eliminar Personaje");
            System.out.println("4. Ver Datos de un Personaje");
            System.out.println("5. Ver Listado de Personajes");
            System.out.println("6. Realizar Pelea");
            System.out.println("7. Ver Historial de Peleas");
            System.out.println("8. Ver Datos del Estudiante");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    contador = AgregarPersonaje.agregarPersonaje(
                            nombres, armas, habilidades, niveles, contador);
                    break;
                case 2:
                    AgregarArmas.modificarPersonaje(
                            nombres, armas, habilidades, niveles, contador);
                    break;
                case 3:
                    contador = eliminarPersonaje(nombres, armas, habilidades, niveles, contador);
                    break;
                case 4:
                    verDatosPersonaje(nombres, armas, habilidades, niveles, contador);
                    break;
                case 5:
                    verListadoPersonajes(nombres, niveles, contador);
                    break;
                case 6:
                    contadorPeleas = realizarPelea(nombres, niveles, historial, contador, contadorPeleas);
                    break;
                case 7:
                    verHistorial(historial, contadorPeleas);
                    break;
                case 8:
                    System.out.println("Estudiante: Juan Pablo Tepeu");
                    System.out.println("Carnet: 202308216");
                    System.out.println("Curso: Introduccion a la Programacion y Computacion 1");
                    System.out.println("Seccion: A");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    public static int eliminarPersonaje(String[] nombres, String[] armas, String[][] habilidades, int[] niveles, int contador) {
        Scanner sc = new Scanner(System.in);
        if (contador == 0) {
            System.out.println("No hay personajes para eliminar.");
            return contador;
        }
        System.out.print("Ingrese ID del personaje a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id < 0 || id >= contador) {
            System.out.println("ID invalido.");
            return contador;
        }

        for (int i = id; i < contador - 1; i++) {
            nombres[i] = nombres[i + 1];
            armas[i] = armas[i + 1];
            niveles[i] = niveles[i + 1];
            habilidades[i] = habilidades[i + 1];
        }
        nombres[contador - 1] = null;
        armas[contador - 1] = null;
        niveles[contador - 1] = 0;
        habilidades[contador - 1] = new String[5];

        System.out.println("✅ Personaje eliminado.");
        return contador - 1;
    }

    public static void verDatosPersonaje(String[] nombres, String[] armas, String[][] habilidades, int[] niveles, int contador) {
        Scanner sc = new Scanner(System.in);
        if (contador == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.print("Ingrese ID del personaje: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (id < 0 || id >= contador) {
            System.out.println("ID invalido.");
            return;
        }
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombres[id]);
        System.out.println("Arma: " + armas[id]);
        System.out.println("Habilidades:");
        for (int i = 0; i < 5; i++) {
            if (habilidades[id][i] != null) {
                System.out.println("- " + habilidades[id][i]);
            }
        }
        System.out.println("Nivel de poder: " + niveles[id]);
    }

    public static void verListadoPersonajes(String[] nombres, int[] niveles, int contador) {
        if (contador == 0) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        System.out.println("ID\tNombre\tNivel");
        for (int i = 0; i < contador; i++) {
            System.out.println(i + "\t" + nombres[i] + "\t" + niveles[i]);
        }
    }

    public static int realizarPelea(String[] nombres, int[] niveles, String[] historial, int contador, int contadorPeleas) {
        Scanner sc = new Scanner(System.in);
        if (contador < 2) {
            System.out.println("Se necesitan al menos 2 personajes para pelear.");
            return contadorPeleas;
        }
        System.out.print("Ingrese ID del primer personaje: ");
        int id1 = sc.nextInt();
        System.out.print("Ingrese ID del segundo personaje: ");
        int id2 = sc.nextInt();
        sc.nextLine();

        if (id1 < 0 || id1 >= contador || id2 < 0 || id2 >= contador || id1 == id2) {
            System.out.println("IDs invalidos.");
            return contadorPeleas;
        }

        String ganador = (niveles[id1] >= niveles[id2]) ? nombres[id1] : nombres[id2];

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String registro = nombres[id1] + " vs " + nombres[id2] + " - Ganador: " + ganador + " - " + fecha.format(formato);

        historial[contadorPeleas] = registro;
        System.out.println("✅ Pelea registrada: " + registro);

        return contadorPeleas + 1;
    }

    public static void verHistorial(String[] historial, int contadorPeleas) {
        if (contadorPeleas == 0) {
            System.out.println("No hay peleas registradas.");
            return;
        }
        System.out.println("Historial de peleas:");
        for (int i = 0; i < contadorPeleas; i++) {
            System.out.println((i + 1) + ". " + historial[i]);
        }
    }
}
    
