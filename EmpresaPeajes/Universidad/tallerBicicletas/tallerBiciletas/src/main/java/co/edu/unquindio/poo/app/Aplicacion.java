package co.edu.unquindio.poo.app;

import co.edu.unquindio.poo.model.*;

import javax.swing.*;
import java.time.LocalDate;

public class Aplicacion {
    public static void main(String[] args) {

        String nombre = ingresarDato("Ingrese el nombre del taller de bicicletas:");
        if (nombre == null) return; // Si el usuario presiona "Cancelar", sale del programa

        String direccion = ingresarDato("Ingrese la dirección del taller:");

        Taller taller = new Taller(nombre, direccion);
        mostrarMensaje("¡Bienvenido al sistema de " + taller.getNombre() + "!");

        // 2. Iniciar el menú principal
        iniciarMenu(taller);
    }

    // ==========================================================
    // MÉTODO DEL MENÚ PRINCIPAL
    // ==========================================================

    public static void iniciarMenu(Taller taller) {
        int opcion = -1;
        do {
            String menu = "=== MENÚ PRINCIPAL: " + taller.getNombre() + " ===\n"
                    + "1. Registrar Cliente\n"
                    + "2. Registrar Mecánico\n"
                    + "3. Registrar Bicicleta\n"
                    + "4. Crear Orden de Servicio\n"
                    + "5. Ver Órdenes y Totales\n"
                    + "0. Salir\n\n"
                    + "Seleccione una opción:";

            try {
                String input = ingresarDato(menu);
                if (input == null) break; // Si cancela, sale del menú
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1: registrarClienteApp(taller); break;
                    case 2: registrarMecanicoApp(taller); break;
                    case 3: registrarBicicletaApp(taller); break;
                    case 4: crearOrdenApp(taller); break;
                    case 5: mostrarOrdenesApp(taller); break;
                    case 0: mostrarMensaje("Cerrando el sistema. ¡Hasta luego!"); break;
                    default: mostrarMensaje("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Por favor, ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    // ==========================================================
    // MÉTODOS DE GESTIÓN (Módulos independientes)
    // ==========================================================

    private static void registrarClienteApp(Taller taller) {
        String nombre = ingresarDato("Ingrese el nombre del cliente:");
        String identificacion = ingresarDato("Ingrese la identificación del cliente:");
        String direccion = ingresarDato("Ingrese la dirección:");
        String telefono = ingresarDato("Ingrese el teléfono:");

        // Pedimos el año para hacer la fecha dinámica de forma sencilla
        int anioNacimiento = 2000;
        try {
            anioNacimiento = Integer.parseInt(ingresarDato("Ingrese el año de nacimiento (ej. 1990):"));
        } catch (Exception e) {
            mostrarMensaje("Año inválido. Se asignará el año 2000 por defecto.");
        }

        int numBicis = Integer.parseInt(ingresarDato("¿Cuántas bicicletas tiene?"));

        String resultado = taller.registrarCliente(nombre, identificacion, direccion, telefono, LocalDate.of(anioNacimiento, 1, 1), numBicis);
        mostrarMensaje(resultado);
    }

    private static void registrarMecanicoApp(Taller taller) {
        String nombre = ingresarDato("Ingrese el nombre del mecánico:");
        String identificacion = ingresarDato("Ingrese la identificación del mecánico:");
        String telefono = ingresarDato("Ingrese el teléfono:");

        String resultado = taller.registrarMecanico(nombre, identificacion, telefono);
        mostrarMensaje(resultado);
    }

    private static void registrarBicicletaApp(Taller taller) {
        String idBici = ingresarDato("Ingrese el ID (Serial) de la bicicleta:");
        String marca = ingresarDato("Ingrese la marca:");
        String modelo = ingresarDato("Ingrese el modelo:");

        Bicicleta nuevaBici = new Bicicleta(idBici, marca, modelo);
        taller.getListaBiciceltas().add(nuevaBici);

        mostrarMensaje("Bicicleta " + marca + " registrada exitosamente en el taller.");
    }

    private static void crearOrdenApp(Taller taller) {
        String codigo = ingresarDato("Ingrese el código para la Orden (ej. ORD-01):");
        String problema = ingresarDato("Describa el problema de la bicicleta:");
        String idBici = ingresarDato("Ingrese el ID de la Bicicleta a reparar:");
        String idMec = ingresarDato("Ingrese el ID del Mecánico encargado:");

        String resultado = taller.registrarOrden(codigo, problema, idBici, idMec);
        mostrarMensaje(resultado);
    }

    private static void mostrarOrdenesApp(Taller taller) {
        if (taller.getListaOrdenesServicio().isEmpty()) {
            mostrarMensaje("No hay órdenes de servicio registradas aún.");
            return;
        }

        StringBuilder reporte = new StringBuilder("=== REPORTE DE ÓRDENES ===\n\n");
        for (OrdenServicio os : taller.getListaOrdenesServicio()) {
            reporte.append(os.toString()).append("\n\n");
        }
        mostrarMensaje(reporte.toString());
    }

    public static String ingresarDato(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}