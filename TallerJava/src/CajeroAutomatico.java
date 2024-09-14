import java.util.Scanner;

public class CajeroAutomatico {
    private String numeroCuenta;
    private String pin;
    private double saldo;

    // Constructor para inicializar cuenta, PIN y saldo
    public CajeroAutomatico(String numeroCuenta, String pin, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
    }

    // Método para iniciar sesión
    public boolean iniciarSesion(String numeroCuenta, String pin) {
        return this.numeroCuenta.equals(numeroCuenta) && this.pin.equals(pin);
    }

    // Método para consultar el saldo
    public void consultarSaldo() {
        System.out.println("Saldo actual: $" + saldo);
    }

    // Método para depositar dinero
    public void depositarDinero(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Has depositado $" + cantidad);
        } else {
            System.out.println("Cantidad inválida.");
        }
    }

    // Método para retirar dinero
    public void retirarDinero(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Has retirado $" + cantidad);
        } else {
            System.out.println("Fondos insuficientes o cantidad inválida.");
        }
    }

    // Menú de opciones
    public static void mostrarMenu() {
        System.out.println("\n1. Consultar Saldo");
        System.out.println("2. Depositar Dinero");
        System.out.println("3. Retirar Dinero");
        System.out.println("4. Salir");
    }

    public static void main(String[] args) {
        // Crear una cuenta de ejemplo
        CajeroAutomatico cajero = new CajeroAutomatico("123456789", "1234", 5000.0);

        Scanner scanner = new Scanner(System.in);

        // Solicitar inicio de sesión
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Ingrese PIN: ");
        String pin = scanner.nextLine();

        // Verificar inicio de sesión
        if (cajero.iniciarSesion(numeroCuenta, pin)) {
            System.out.println("Inicio de sesión exitoso.");

            int opcion;
            do {
                mostrarMenu();
                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        cajero.consultarSaldo();
                        break;
                    case 2:
                        System.out.print("Ingrese la cantidad a depositar: ");
                        double deposito = scanner.nextDouble();
                        cajero.depositarDinero(deposito);
                        break;
                    case 3:
                        System.out.print("Ingrese la cantidad a retirar: ");
                        double retiro = scanner.nextDouble();
                        cajero.retirarDinero(retiro);
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 4);

        } else {
            System.out.println("Número de cuenta o PIN incorrectos.");
        }

        scanner.close();
    }
}
