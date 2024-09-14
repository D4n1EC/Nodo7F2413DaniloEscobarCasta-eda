import java.util.ArrayList;
import java.util.Scanner;

// Clase para representar un asiento
class Asiento {
    private int numero;
    private boolean reservado;

    public Asiento(int numero) {
        this.numero = numero;
        this.reservado = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean estaReservado() {
        return reservado;
    }

    public void reservar() {
        if (!reservado) {
            reservado = true;
            System.out.println("Asiento " + numero + " reservado con éxito.");
        } else {
            System.out.println("El asiento " + numero + " ya está reservado.");
        }
    }
}

// Clase para representar una película
class Pelicula {
    private String titulo;
    private int duracion; // Duración en minutos
    private ArrayList<Asiento> asientos;

    public Pelicula(String titulo, int duracion, int numeroAsientos) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.asientos = new ArrayList<>();
        for (int i = 1; i <= numeroAsientos; i++) {
            asientos.add(new Asiento(i));
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void mostrarAsientos() {
        System.out.println("\n--- Asientos disponibles para " + titulo + " ---");
        for (Asiento asiento : asientos) {
            System.out.println("Asiento " + asiento.getNumero() + ": " + (asiento.estaReservado() ? "Reservado" : "Disponible"));
        }
    }

    public Asiento seleccionarAsiento(int numero) {
        if (numero > 0 && numero <= asientos.size()) {
            return asientos.get(numero - 1);
        }
        return null;
    }
}

// Clase para representar el cine
class Cine {
    private ArrayList<Pelicula> peliculas;

    public Cine() {
        peliculas = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public void mostrarPeliculas() {
        System.out.println("\n--- Películas disponibles ---");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println((i + 1) + ". " + peliculas.get(i).getTitulo());
        }
    }

    public Pelicula seleccionarPelicula(int indice) {
        if (indice > 0 && indice <= peliculas.size()) {
            return peliculas.get(indice - 1);
        }
        return null;
    }
}

// Clase para representar al usuario y la reserva de tickets
class Usuario {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void reservarAsiento(Pelicula pelicula, int numeroAsiento) {
        Asiento asiento = pelicula.seleccionarAsiento(numeroAsiento);
        if (asiento != null) {
            asiento.reservar();
        } else {
            System.out.println("Asiento no válido.");
        }
    }

    public void procesarPago() {
        System.out.println("Pago procesado con éxito. Gracias por su compra.");
    }
}

// Clase principal del sistema de reserva de tickets
public class SistemaReservaCine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cine cine = new Cine();

        // Agregar películas de ejemplo
        cine.agregarPelicula(new Pelicula("Los Vengadores", 180, 10));
        cine.agregarPelicula(new Pelicula("El Señor de los Anillos", 240, 10));
        cine.agregarPelicula(new Pelicula("La La Land", 130, 10));

        Usuario usuario = new Usuario("Juan Pérez");

        int opcion;

        do {
            System.out.println("\n--- Sistema de Reserva de Tickets de Cine ---");
            System.out.println("1. Ver películas disponibles");
            System.out.println("2. Reservar asiento");
            System.out.println("3. Procesar pago");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Mostrar películas
                    cine.mostrarPeliculas();
                    break;

                case 2:
                    // Reservar asiento
                    cine.mostrarPeliculas();
                    System.out.print("Seleccione el número de la película: ");
                    int peliculaSeleccionada = scanner.nextInt();
                    Pelicula pelicula = cine.seleccionarPelicula(peliculaSeleccionada);

                    if (pelicula != null) {
                        pelicula.mostrarAsientos();
                        System.out.print("Seleccione el número de asiento: ");
                        int numeroAsiento = scanner.nextInt();
                        usuario.reservarAsiento(pelicula, numeroAsiento);
                    } else {
                        System.out.println("Película no válida.");
                    }
                    break;

                case 3:
                    // Procesar pago
                    usuario.procesarPago();
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}

