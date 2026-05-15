package co.edu.unquindio.poo.app;
import co.edu.unquindio.poo.model.*;

import javax.swing.JOptionPane;

public class Aplicacion {

    public static void main(String[] args) {
        String nombreEmpresa = ingresarDato("Ingrese el nombre de la empresa:");
        if (nombreEmpresa == null) return;

        Empresa empresa = new Empresa(nombreEmpresa);
        mostrarMensaje("¡Bienvenido al sistema de nómina de " + empresa.getNombre() + "!");

        iniciarMenu(empresa);
    }

    public static void iniciarMenu(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = "=== MENÚ: " + empresa.getNombre() + " ===\n"
                    + "1. Agregar Empleado de Planta\n"
                    + "2. Agregar Empleado de Ventas\n"
                    + "3. Agregar Empleado Temporal\n"
                    + "4. Listar Empleados\n"
                    + "5. Buscar empleado por documento\n"
                    + "6. Mostrar empleado con mayor salario neto\n"
                    + "7. Calcular nómina total\n"
                    + "8. Mostrar resúmenes de pago\n"
                    + "9. Salir\n\n"
                    + "Seleccione una opción:";
            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> agregarEmpleadoPlantaApp(empresa);
                    case 2 -> agregarEmpleadoVentasApp(empresa);
                    case 3 -> agregarEmpleadoTemporalApp(empresa);
                    case 4 -> mostrarMensaje(empresa.listarEmpleadosTipoPlanta());
                    case 5 -> buscarEmpleadoApp(empresa);
                    case 6 -> mostrarMensaje(empresa.mostrarEmpleadoMayorSalario());
                    case 7 -> mostrarMensaje(empresa.calcularNominaTotal());
                    case 8 -> mostrarResumenesApp(empresa);
                    case 0 -> mostrarMensaje("Cerrando el sistema. ¡Hasta luego!");
                    default -> mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private static String[] pedirDatosBase() {
        String nombre = ingresarDato("Nombre del empleado:");
        if (nombre == null) return null;
        String documento = ingresarDato("Documento:");
        if (documento == null) return null;
        String edad = ingresarDato("Edad:");
        if (edad == null) return null;
        String categoria = ingresarDato("Categoría (1=JUNIOR, 2=SEMI_SENIOR, 3=SENIOR):");
        if (categoria == null) return null;
        String descuentoPension = ingresarDato("% Descuento pensión:");
        if (descuentoPension == null) return null;
        String descuentoSalud = ingresarDato("% Descuento salud:");
        if (descuentoSalud == null) return null;
        String salarioBase = ingresarDato("Salario base:");
        if (salarioBase == null) return null;

        return new String[]{nombre, documento, edad, categoria, descuentoPension, descuentoSalud, salarioBase};
    }

    private static void agregarEmpleadoPlantaApp(Empresa empresa) {
        try {
            String[] b = pedirDatosBase();
            if (b == null) return;

            String cargo = ingresarDato("Cargo:");
            int horas = Integer.parseInt(ingresarDato("Horas extra:"));
            float vHoras = Float.parseFloat(ingresarDato("Valor hora extra:"));
            float aux = Float.parseFloat(ingresarDato("Auxilio transporte:"));

            EmpleadoPlanta ep = new EmpleadoPlanta(b[0], b[1], Integer.parseInt(b[2]), parsearCategoria(b[3]),
                    Float.parseFloat(b[4]), Float.parseFloat(b[5]), Float.parseFloat(b[6]), cargo, horas, vHoras, aux);

            mostrarMensaje(empresa.registrarEmpleado(ep));
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void agregarEmpleadoVentasApp(Empresa empresa) {
        try {
            String[] b = pedirDatosBase();
            if (b == null) return;

            float ventas = Float.parseFloat(ingresarDato("Total ventas:"));
            float comision = Float.parseFloat(ingresarDato("% Comisión:"));

            EmpleadoVentas ev = new EmpleadoVentas(b[0], b[1], Integer.parseInt(b[2]), parsearCategoria(b[3]),
                    Float.parseFloat(b[4]), Float.parseFloat(b[5]), Float.parseFloat(b[6]), ventas, comision);

            mostrarMensaje(empresa.registrarEmpleado(ev));
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void agregarEmpleadoTemporalApp(Empresa empresa) {
        try {
            String[] b = pedirDatosBase();
            if (b == null) return;

            int dias = Integer.parseInt(ingresarDato("Días trabajados:"));
            float vDia = Float.parseFloat(ingresarDato("Valor día:"));

            EmpleadoTemporal et = new EmpleadoTemporal(b[0], b[1], Integer.parseInt(b[2]), parsearCategoria(b[3]),
                    Float.parseFloat(b[4]), Float.parseFloat(b[5]), Float.parseFloat(b[6]), dias, vDia);

            mostrarMensaje(empresa.registrarEmpleado(et));
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void buscarEmpleadoApp(Empresa empresa) {
        String doc = ingresarDato("Documento a buscar:");
        if (doc == null) return;
        Empleado e = empresa.buscarEmpleadoPorDocumento(doc);
        mostrarMensaje(e != null ? e.mostrarInformacion() : "No encontrado.");
    }

    private static void mostrarResumenesApp(Empresa empresa) {
        String res = "--- RESÚMENES DE PAGO ---\n";
        for (ResumenPago r : empresa.obtenerResumenesPagos()) {
            res += r.toString() + "\n";
        }
        mostrarMensaje(res.length() > 26 ? res : "No hay empleados.");
    }

    private static CategoriaEmpleado parsearCategoria(String opcion) {
        return switch (opcion.trim()) {
            case "2" -> CategoriaEmpleado.SEMI_SENIOR;
            case "3" -> CategoriaEmpleado.SENIOR;
            default  -> CategoriaEmpleado.JUNIOR;
        };
    }

    public static String ingresarDato(String m) { return JOptionPane.showInputDialog(m); }
    public static void mostrarMensaje(String m) { JOptionPane.showMessageDialog(null, m); }
}