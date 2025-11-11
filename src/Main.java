import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SalaUrgencias urgencias = new SalaUrgencias();
        int opcion;

        do {
            System.out.println("\n=== SALA DE URGENCIAS ===");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Ver siguiente paciente");
            System.out.println("3. Atender paciente");
            System.out.println("4. Deshacer última atención");
            System.out.println("5. Mostrar contadores");
            System.out.println("6. Mostrar reporte");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del paciente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Prioridad (1=Rojo, 2=Amarillo, 3=Verde): ");
                    int prioridad = sc.nextInt();
                    urgencias.registrar(nombre, prioridad);
                    break;

                case 2:
                    urgencias.verSiguiente();
                    break;

                case 3:
                    urgencias.atender();
                    break;

                case 4:
                    urgencias.deshacer();
                    break;

                case 5:
                    urgencias.contadores();
                    break;

                case 6:
                    urgencias.reporte();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}