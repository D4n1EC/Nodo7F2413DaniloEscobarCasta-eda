import java.util.ArrayList;
import java.util.Scanner;

// Clase para representar una oferta de empleo
class OfertaEmpleo {
    private String id;
    private String industria;
    private String ubicacion;
    private String tipoContrato;
    private String descripcion;

    public OfertaEmpleo(String id, String industria, String ubicacion, String tipoContrato, String descripcion) {
        this.id = id;
        this.industria = industria;
        this.ubicacion = ubicacion;
        this.tipoContrato = tipoContrato;
        this.descripcion = descripcion;
    }

    // Métodos para obtener los detalles de la oferta
    public String getId() {
        return id;
    }

    public String getIndustria() {
        return industria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Mostrar la información de la oferta
    public void mostrarDetalles() {
        System.out.println("\nID de oferta: " + id);
        System.out.println("Industria: " + industria);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Tipo de contrato: " + tipoContrato);
        System.out.println("Descripción: " + descripcion);
    }
}

// Clase para representar un empleador que registra ofertas
class Empleador {
    private String nombre;

    public Empleador(String nombre) {
        this.nombre = nombre;
    }

    // Método para registrar una oferta de empleo
    public void registrarOferta(ArrayList<OfertaEmpleo> ofertas, String id, String industria, String ubicacion, String tipoContrato, String descripcion) {
        OfertaEmpleo nuevaOferta = new OfertaEmpleo(id, industria, ubicacion, tipoContrato, descripcion);
        ofertas.add(nuevaOferta);
        System.out.println("Oferta registrada con éxito.");
    }
}

// Clase para representar a un candidato que aplica a ofertas
class Candidato {
    private String nombre;
    private String resumenCalificaciones;

    public Candidato(String nombre, String resumenCalificaciones) {
        this.nombre = nombre;
        this.resumenCalificaciones = resumenCalificaciones;
    }

    // Método para aplicar a una oferta de empleo
    public void aplicarAOferta(OfertaEmpleo oferta) {
        System.out.println("\n" + nombre + " ha aplicado a la oferta " + oferta.getId() + " con el siguiente resumen:");
        System.out.println("Resumen de calificaciones: " + resumenCalificaciones);
    }
}

// Clase principal para manejar el simulador
public class SimuladorBolsaTrabajo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<OfertaEmpleo> ofertas = new ArrayList<>();

        // Crear empleador y candidato de ejemplo
        Empleador empleador = new Empleador("Empresa ABC");
        Candidato candidato = new Candidato("Juan Pérez", "Graduado en Ingeniería, 5 años de experiencia en desarrollo de software.");

        int opcion;

        do {
            System.out.println("\n--- Simulador de Bolsa de Trabajo ---");
            System.out.println("1. Registrar oferta de empleo (Empleador)");
            System.out.println("2. Buscar ofertas de empleo");
            System.out.println("3. Aplicar a una oferta (Candidato)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Registro de nueva oferta de empleo
                    System.out.print("Ingrese ID de la oferta: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese industria: ");
                    String industria = scanner.nextLine();
                    System.out.print("Ingrese ubicación: ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Ingrese tipo de contrato: ");
                    String tipoContrato = scanner.nextLine();
                    System.out.print("Ingrese descripción de la oferta: ");
                    String descripcion = scanner.nextLine();

                    empleador.registrarOferta(ofertas, id, industria, ubicacion, tipoContrato, descripcion);
                    break;

                case 2:
                    // Búsqueda de ofertas
                    System.out.print("Ingrese industria para buscar: ");
                    String filtroIndustria = scanner.nextLine();
                    System.out.print("Ingrese ubicación para buscar: ");
                    String filtroUbicacion = scanner.nextLine();
                    System.out.print("Ingrese tipo de contrato para buscar: ");
                    String filtroTipoContrato = scanner.nextLine();

                    System.out.println("\n--- Resultados de búsqueda ---");
                    for (OfertaEmpleo oferta : ofertas) {
                        if (oferta.getIndustria().equalsIgnoreCase(filtroIndustria) &&
                                oferta.getUbicacion().equalsIgnoreCase(filtroUbicacion) &&
                                oferta.getTipoContrato().equalsIgnoreCase(filtroTipoContrato)) {
                            oferta.mostrarDetalles();
                        }
                    }
                    break;

                case 3:
                    // Aplicar a una oferta
                    System.out.print("Ingrese el ID de la oferta a la que desea aplicar: ");
                    String idOferta = scanner.nextLine();

                    OfertaEmpleo ofertaSeleccionada = null;
                    for (OfertaEmpleo oferta : ofertas) {
                        if (oferta.getId().equals(idOferta)) {
                            ofertaSeleccionada = oferta;
                            break;
                        }
                    }

                    if (ofertaSeleccionada != null) {
                        candidato.aplicarAOferta(ofertaSeleccionada);
                    } else {
                        System.out.println("No se encontró una oferta con ese ID.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}

