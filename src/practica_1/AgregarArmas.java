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

public class AgregarArmas {
    public static void modificarPersonaje(
            String[] nombres, 
            String[] armas, 
            String[][] habilidades, 
            int[] niveles, 
            int contador) {
        
        if (contador == 0) {
            System.out.println("No hay personajes para modificar.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese ID del personaje a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        
        if (id < 0 || id >= contador) {
            System.out.println("Error: ID no valido.");
            return;
        }
        
        System.out.println("Modificando personaje: " + nombres[id]);
        
        System.out.print("Nueva arma: ");
        armas[id] = sc.nextLine();
        
        // Modificar habilidades
        System.out.println("Ingrese hasta 5 habilidades (deje vacio para terminar):");
        for (int i = 0; i < 5; i++) {
            System.out.print("Habilidad " + (i+1) + ": ");
            String hab = sc.nextLine();
            if (hab.isEmpty()) break;
            habilidades[id][i] = hab;
        }
        
        // Cambiar nivel de poder
        int nivel;
        while (true) {
            System.out.print("Ingrese nuevo nivel de poder (1-100): ");
            if (sc.hasNextInt()) {
                nivel = sc.nextInt();
                sc.nextLine();
                if (nivel >= 1 && nivel <= 100) break;
            } else {
                sc.nextLine();
            }
            System.out.println("Error: Nivel de poder inválido.");
        }
        niveles[id] = nivel;
        
        System.out.println("Personaje modificado correctamente.");
    }
}

