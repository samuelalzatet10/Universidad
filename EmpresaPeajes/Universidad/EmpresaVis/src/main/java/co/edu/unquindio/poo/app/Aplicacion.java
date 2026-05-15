package co.edu.unquindio.poo.app;

import co.edu.unquindio.poo.model.*;
import javax.swing.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Aplicacion {

    public static void main(String[] args) {

        String nombreEmpresa = ingresarDato("Ingrese el nombre de la empresa:");
        if (nombreEmpresa == null) return;

        Empresa empresa = new Empresa(nombreEmpresa);
        mostrarMensaje("Bienvenido al sistema de gestión de " + empresa.getNombre());

        iniciarMenu(empresa);
    }

    public static void iniciarMenu(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = "=== MENÚ PRINCIPAL: " + empresa.getNombre() + " ===\n"
                    + "1. Registrar Empleado\n"
                    + "2. Actualizar Empleado\n"
                    + "3. Eliminar Empleado\n"
                    + "4. Consultar Empleados con Retraso\n"
                    + "0. Salir\n\n"
                    + "Seleccione una opción:";

            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1: registrarEmpleadoApp(empresa); break;
                    case 2: actualizarEmpleadoApp(empresa); break;
                    case 3: eliminarEmpleadoApp(empresa); break;
                    case 4: consultarRetrasosApp(empresa); break;
                    case 0: mostrarMensaje("Saliendo del sistema..."); break;
                    default: mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }
    private static void registrarEmpleadoApp(Empresa empresa) {
        String nombre = ingresarDato("Nombre del empleado:");
        String cedula = ingresarDato("Cédula:");

        int hora = Integer.parseInt(ingresarDato("Hora de entrada (0-23):"));
        int minuto = Integer.parseInt(ingresarDato("Minuto de entrada (0-59):"));

        LocalTime entrada = LocalTime.of(hora, minuto);
        LocalTime salida = LocalTime.of(17, 0);

        String resultado = empresa.registrarEmpleado(nombre, cedula, entrada, salida);
        mostrarMensaje(resultado);
    }

    private static void actualizarEmpleadoApp(Empresa empresa) {
        String cedula = ingresarDato("Ingrese la cédula del empleado a actualizar:");
        String nuevoNombre = ingresarDato("Nuevo nombre:");
        int hora = Integer.parseInt(ingresarDato("Nueva hora de entrada:"));
        int min = Integer.parseInt(ingresarDato("Nuevo minuto de entrada:"));

        String resultado = empresa.actualizarEmpleado(nuevoNombre, cedula, LocalTime.of(hora, min), LocalTime.of(17, 0));
        mostrarMensaje(resultado);
    }

    private static void eliminarEmpleadoApp(Empresa empresa) {
        String cedula = ingresarDato("Ingrese la cédula del empleado a eliminar:");
        String resultado = empresa.eliminarEmpleado(cedula);
        mostrarMensaje(resultado);
    }

    private static void consultarRetrasosApp(Empresa empresa) {
        int h = Integer.parseInt(ingresarDato("Ingrese la hora límite de entrada (Ej: 8):"));
        int m = Integer.parseInt(ingresarDato("Ingrese el minuto límite (Ej: 0):"));

        LocalTime horaLimite = LocalTime.of(h, m);
        ArrayList<Empleado> retrasados = empresa.consultarEmpleadosTarde(horaLimite);

        if (retrasados.isEmpty()) {
            mostrarMensaje("No hay empleados con retraso.");
        } else {
            StringBuilder reporte = new StringBuilder("=== EMPLEADOS CON RETRASO ===\n");
            for (Empleado e : retrasados) {
                reporte.append("- ").append(e.getNombre())
                        .append(" (Entró: ").append(e.getHoraEntrada()).append(")\n");
            }
            mostrarMensaje(reporte.toString());
        }
    }
    public static String ingresarDato(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }
    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}