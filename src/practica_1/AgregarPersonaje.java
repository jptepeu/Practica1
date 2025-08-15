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

public class AgregarPersonaje {
    public static int agregarPersonaje(
            String[] nombres, 
            String[] armas, 
            String[][] habilidades, 
            int[] niveles, 
            int contador) {
        
        Scanner sc = new Scanner(System.in);
        
        if (contador >= nombres.length) {
            System.out.println("No se pueden agregar más personajes.");
            return contador;
        }
        
        System.out.print("Ingrese nombre del personaje: ");
        String nombre = sc.nextLine();
        
        // Validar nombre único
        for (int i = 0; i < contador; i++) {
            if (nombres[i].equalsIgnoreCase(nombre)) {
                System.out.println("Error: El nombre ya existe.");
                return contador;
            }
        }
        
        nombres[contador] = nombre;
        
        System.out.print("Ingrese arma del personaje: ");
        armas[contador] = sc.nextLine();
        
        // Agregar habilidades (máximo 5)
        System.out.println("Ingrese hasta 5 habilidades (deje vacio para terminar):");
        for (int i = 0; i < 5; i++) {
            System.out.print("Habilidad " + (i+1) + ": ");
            String hab = sc.nextLine();
            if (hab.isEmpty()) break;
            habilidades[contador][i] = hab;
        }
        
        // Validar nivel de poder
        int nivel;
        while (true) {
            System.out.print("Ingrese nivel de poder (1-100): ");
            if (sc.hasNextInt()) {
                nivel = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                if (nivel >= 1 && nivel <= 100) break;
            } else {
                sc.nextLine(); // limpiar entrada inválida
            }
            System.out.println("Error: Nivel de poder inválido.");
        }
        niveles[contador] = nivel;
        
        System.out.println("✅ Personaje agregado exitosamente con ID: " + contador);
        return contador + 1;
    }
}

