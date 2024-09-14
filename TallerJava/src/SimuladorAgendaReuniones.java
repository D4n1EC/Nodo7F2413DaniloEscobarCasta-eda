import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Clase para representar a un participante
class Participante {
    private String nombre;

    public Participante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

// Clase para representar una reunión
class Reunion {
    private String titulo;
    private LocalDateTime fechaHoraInicio;
    private int duracionMinutos;
    private ArrayList<Participante> participantes;

    public Reunion(String titulo, LocalDateTime fechaHoraInicio, int duracionMinutos) {
        this.titulo = titulo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.duracionMinutos = duracionMinutos;
        this.participantes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraInicio.plusMinutes(duracionMinutos);
    }

    public void agregarParticipante(Participante participante) {
        participantes.add(participante);
        System.out.println(participante.getNombre() + " ha sido agregado a la reunión " + titulo);
    }

    public void mostrarDetalles() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("\nTítulo: " + titulo);
        System.out.println("Fecha y hora de inicio: " + fechaHoraInicio.format(formato));
        System.out.println("Duración: " + duracionMinutos + " minutos");
        System.out.println("Participantes: ");
        for (Participante p : participantes) {
            System.out.println("- " + p.getNombre());
        }
    }
}

// Clase para gestionar la agenda de reuniones
class Agenda {
    private ArrayList<Reunion> reuniones;

    public Agenda() {
        reuniones = new ArrayList<>();
    }

    // Método para crear una reunión
    public void crearReunion(Reunion reunion) {
        if (!haySolapamiento(reunion)) {
            reuniones.add(reunion);
            System.out.println("Reunión '" + reunion.getTitulo() + "' creada con éxito.");
        } else {
            System.out.println("Error: La reunión solapa con otra ya existente.");
        }
    }

    // Método para eliminar una reunión
    public void eliminarReunion(String titulo) {
        Reunion reunionAEliminar = buscarReunion(titulo);
        if (reunionAEliminar != null) {
            reuniones.remove(reunionAEliminar);
            System.out.println("Reunión '" + titulo + "' eliminada con éxito.");
        } else {
            System.out.println("No se encontró la reunión con el título especificado.");
        }
    }

    // Método para agregar un participante a una reunión
    public void agregarParticipanteAReunion(String titulo, Participante participante) {
        Reunion reunion = buscarReunion(titulo);
        if (reunion != null) {
            reunion.agregarParticipante(participante);
        } else {
            System.out.println("No se encontró la reunión con el título especificado.");
        }
    }

    // Mostrar todas las reuniones
    public void mostrarReuniones() {
        if (reuniones.isEmpty()) {
            System.out.println("No hay reuniones programadas.");
        } else {
            for (Reunion r : reuniones) {
                r.mostrarDetalles();
            }
        }
    }

    // Método para verificar si una reunión solapa con otra
    private boolean haySolapamiento(Reunion nuevaReunion) {
        for (Reunion r : reuniones) {
            if (nuevaReunion.getFechaHoraInicio().isBefore(r.getFechaHoraFin()) &&
                    nuevaReunion.getFechaHoraFin().isAfter(r.getFechaHoraInicio())) {
                return true;
            }
        }
        return false;
    }

    // Buscar una reunión por título
    private Reunion buscarReunion(String titulo) {
        for (Reunion r : reuniones) {
            if (r.getTitulo().equalsIgnoreCase(titulo)) {
                return r;
            }
        }
        return null;
    }

    // Método para simular el envío de recordatorios
    public void enviarRecordatorios() {
        LocalDateTime ahora = LocalDateTime.now();
        for (Reunion r : reuniones) {
            if (r.getFechaHoraInicio().isAfter(ahora) && r.getFechaHoraInicio().isBefore(ahora.plusMinutes(60))) {
                System.out.println("Recordatorio: La reunión '" + r.getTitulo() + "' comenzará en menos de una hora.");
            }
        }
    }
}

// Clase principal para gestionar el sistema de la agenda de reuniones
public class SimuladorAgendaReuniones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        int opcion;

        do {
            System.out.println("\n--- Simulador de Agenda de Reuniones ---");
            System.out.println("1. Crear reunión");
            System.out.println("2. Eliminar reunión");
            System.out.println("3. Agregar participante a una reunión");
            System.out.println("4. Mostrar todas las reuniones");
            System.out.println("5. Enviar recordatorios");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Crear una reunión
                    System.out.print("Ingrese el título de la reunión: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese la fecha y hora de inicio (dd-MM-yyyy HH:mm): ");
                    String fechaHora = scanner.nextLine();
                    System.out.print("Ingrese la duración en minutos: ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    LocalDateTime fechaHoraInicio = LocalDateTime.parse(fechaHora, formato);
                    Reunion nuevaReunion = new Reunion(titulo, fechaHoraInicio, duracion);
                    agenda.crearReunion(nuevaReunion);
                    break;

                case 2:
                    // Eliminar una reunión
                    System.out.print("Ingrese el título de la reunión a eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    agenda.eliminarReunion(tituloEliminar);
                    break;

                case 3:
                    // Agregar participante
                    System.out.print("Ingrese el título de la reunión: ");
                    String tituloReunion = scanner.nextLine();
                    System.out.print("Ingrese el nombre del participante: ");
                    String nombreParticipante = scanner.nextLine();
                    Participante participante = new Participante(nombreParticipante);
                    agenda.agregarParticipanteAReunion(tituloReunion, participante);
                    break;

                case 4:
                    // Mostrar todas las reuniones
                    agenda.mostrarReuniones();
                    break;

                case 5:
                    // Enviar recordatorios
                    agenda.enviarRecordatorios();
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}

