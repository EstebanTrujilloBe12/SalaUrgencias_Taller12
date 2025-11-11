import java.util.*;
public class SalaUrgencias {

    // Paciente como clase interna estática para evitar errores con contextos estáticos
    static class Paciente implements Comparable<Paciente> {
        private static int contadorLlegada = 0;

        String nombre;
        int prioridad; // 1 = rojo, 2 = amarillo, 3 = verde
        int ordenLlegada;

        public Paciente(String nombre, int prioridad) {
            this.nombre = nombre;
            this.prioridad = prioridad;
            this.ordenLlegada = contadorLlegada++;
        }

        @Override
        public int compareTo(Paciente otro) {
            if (this.prioridad != otro.prioridad)
                return Integer.compare(this.prioridad, otro.prioridad);
            return Integer.compare(this.ordenLlegada, otro.ordenLlegada);
        }

        @Override
        public String toString() {
            return nombre + " (prioridad " + prioridad + ")";
        }
    }

    private PriorityQueue<Paciente> cola;
    private Stack<Paciente> historial;
    private List<Paciente> atendidos;

    public SalaUrgencias() {
        cola = new PriorityQueue<>();
        historial = new Stack<>();
        atendidos = new ArrayList<>();
    }

    public void registrar(String nombre, int prioridad) {
        Paciente p = new Paciente(nombre, prioridad);
        cola.add(p);
        System.out.println("Registrado: " + nombre + " con prioridad " + prioridad);
    }

    public void verSiguiente() {
        if (cola.isEmpty()) {
            System.out.println("No hay pacientes en espera.");
        } else {
            System.out.println("Siguiente: " + cola.peek());
        }
    }

    public void atender() {
        if (cola.isEmpty()) {
            System.out.println("No hay pacientes para atender.");
            return;
        }
        Paciente p = cola.poll();
        atendidos.add(p);
        historial.push(p);
        System.out.println("Atendiendo a: " + p);
    }

    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("No hay atenciones para deshacer.");
            return;
        }
        Paciente p = historial.pop();
        atendidos.remove(p);
        cola.add(p);
        System.out.println("Se deshizo la atención de: " + p.nombre);
    }

    public void contadores() {
        int rojo = 0, amarillo = 0, verde = 0;
        for (Paciente p : cola) {
            if (p.prioridad == 1) rojo++;
            else if (p.prioridad == 2) amarillo++;
            else verde++;
        }
        System.out.println("En espera → Rojo: " + rojo + ", Amarillo: " + amarillo + ", Verde: " + verde);
    }

    public void reporte() {
        System.out.println("\n--- Reporte ---");
        System.out.println("Atendidos:");
        for (Paciente p : atendidos) {
            System.out.println("- " + p);
        }
        System.out.println("\nEn espera:");
        List<Paciente> enEspera = new ArrayList<>(cola);
        Collections.sort(enEspera);
        for (Paciente p : enEspera) {
            System.out.println("- " + p);
        }
        System.out.println("---------------");
    }

    // método main para probar (opcional)
    public static void main(String[] args) {
        SalaUrgencias urgencias = new SalaUrgencias();

        urgencias.registrar("Ana", 2);
        urgencias.registrar("Luis", 1);
        urgencias.registrar("María", 3);
        urgencias.registrar("Pedro", 1);

        urgencias.verSiguiente();
        urgencias.atender();
        urgencias.contadores();
        urgencias.reporte();
        urgencias.deshacer();
        urgencias.reporte();
    }
}