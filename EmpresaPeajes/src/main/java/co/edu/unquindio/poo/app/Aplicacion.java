package co.edu.unquindio.poo.app;

import co.edu.unquindio.poo.model.*;
import javax.swing.*;
import java.time.LocalDate;

public class Aplicacion {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Peajes del Quindío");
        mostrarMensaje("¡Bienvenido al sistema de " + empresa.getNombre() + "!");
        iniciarMenu(empresa);
    }

    public static void iniciarMenu(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = "=== MENÚ PRINCIPAL ===\n"
                    + "1. Registrar Conductor\n"
                    + "2. Registrar Recaudador\n"
                    + "3. Registrar Vehículo\n"
                    + "4. Registrar Peaje\n"
                    + "5. Consultar Camiones (Carga > 10 Ton y > 5 Peajes)\n"
                    + "6. Ver Conductores\n"
                    + "7. Ver Vehículos\n"
                    + "8. Ver Recaudadores\n"
                    + "0. Salir\n\n"
                    + "Seleccione una opción:";

            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1: registrarConductorApp(empresa); break;
                    case 2: registrarRecaudadorApp(empresa); break;
                    case 3: registrarVehiculoApp(empresa); break;
                    case 4: registrarPeajeApp(empresa); break;
                    case 5: consultarCamionesFiltradosApp(empresa); break;
                    case 6: verConductoresApp(empresa); break;
                    case 7: verVehiculosApp(empresa); break;
                    case 8: verRecaudadoresApp(empresa); break;
                    case 0: mostrarMensaje("¡Hasta luego!"); break;
                    default: mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private static void registrarConductorApp(Empresa empresa) {
        String nombre = ingresarDato("Nombre:");
        if (nombre == null) return;
        String apellidos = ingresarDato("Apellidos:");
        if (apellidos == null) return;
        String identificacion = ingresarDato("Identificación:");
        if (identificacion == null) return;
        String anio = ingresarDato("Año de nacimiento:");
        if (anio == null) return;

        try {
            Conductor conductor = new Conductor(nombre, apellidos, identificacion, LocalDate.of(Integer.parseInt(anio), 1, 1), new java.util.ArrayList<>(), empresa);
            empresa.agregarConductor(conductor);
            mostrarMensaje("Conductor registrado.");
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void registrarRecaudadorApp(Empresa empresa) {
        String nombre = ingresarDato("Nombre:");
        if (nombre == null) return;
        String apellidos = ingresarDato("Apellidos:");
        if (apellidos == null) return;
        String identificacion = ingresarDato("Identificación:");
        if (identificacion == null) return;
        String anio = ingresarDato("Año de nacimiento:");
        if (anio == null) return;
        String sueldo = ingresarDato("Sueldo mensual:");
        if (sueldo == null) return;

        try {
            Recaudador recaudador = new Recaudador(nombre, apellidos, identificacion, LocalDate.of(Integer.parseInt(anio), 1, 1), Double.parseDouble(sueldo));
            empresa.agregarRecaudador(recaudador);
            mostrarMensaje("Recaudador registrado.");
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void registrarVehiculoApp(Empresa empresa) {
        if (empresa.getListaConductores().isEmpty()) {
            mostrarMensaje("Primero registre conductores.");
            return;
        }

        String[] tipos = {"Carro", "Moto", "Camión"};
        int tipo = JOptionPane.showOptionDialog(null, "Tipo de vehículo:", "Seleccione",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        String placa = ingresarDato("Placa:");
        if (placa == null) return;
        String idConductor = ingresarDato("ID del conductor:");
        if (idConductor == null) return;

        Conductor conductor = empresa.buscarConductor(idConductor);
        if (conductor == null) {
            mostrarMensaje("Conductor no encontrado.");
            return;
        }

        try {
            Vehiculo vehiculo = null;
            if (tipo == 0) {
                String esElectrico = ingresarDato("¿Eléctrico? (si/no):");
                String esPublico = ingresarDato("¿Servicio público? (si/no):");
                vehiculo = new Carro(placa, 0, conductor, esElectrico != null && esElectrico.equals("si"), esPublico != null && esPublico.equals("si"));
            } else if (tipo == 1) {
                String cilindraje = ingresarDato("Cilindraje (cc):");
                vehiculo = new Moto(placa, 0, conductor, Integer.parseInt(cilindraje));
            } else if (tipo == 2) {
                String carga = ingresarDato("Carga (toneladas):");
                String ejes = ingresarDato("Número de ejes:");
                vehiculo = new Camion(placa, conductor, 0, Double.parseDouble(carga), Integer.parseInt(ejes));
            }
            if (vehiculo != null) {
                empresa.agregarVehiculo(vehiculo);
                mostrarMensaje("Vehículo registrado.");
            }
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void registrarPeajeApp(Empresa empresa) {
        if (empresa.getListaVehiculos().isEmpty()) {
            mostrarMensaje("No hay vehículos registrados.");
            return;
        }
        if (empresa.getListaRecaudadores().isEmpty()) {
            mostrarMensaje("No hay recaudadores registrados.");
            return;
        }

        String placa = ingresarDato("Placa del vehículo:");
        if (placa == null) return;

        Vehiculo vehiculo = empresa.buscarVehiculo(placa);
        if (vehiculo == null) {
            mostrarMensaje("Vehículo no encontrado.");
            return;
        }

        String[] recaudadores = empresa.getListaRecaudadores().stream()
                .map(r -> r.getNombre() + " " + r.getApellidos())
                .toArray(String[]::new);

        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione recaudador:", "Recaudador",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, recaudadores, recaudadores[0]);

        if (seleccion >= 0) {
            Recaudador recaudador = empresa.getListaRecaudadores().get(seleccion);
            RegistroPeaje registro = recaudador.cobrarPeaje(vehiculo);
            mostrarMensaje("Peaje: $" + registro.valor() + "\nPeajes pagados: " + vehiculo.getNumeroPeajes());
        }
    }

    private static void consultarCamionesFiltradosApp(Empresa empresa) {
        java.util.List<Camion> camiones = empresa.getCamionesCargaMayorYMasDeCincoPeajes();
        if (camiones.isEmpty()) {
            mostrarMensaje("No hay camiones con carga > 10 ton y > 5 peajes.");
            return;
        }

        StringBuilder reporte = new StringBuilder("CAMIONES:\n\n");
        for (Camion c : camiones) {
            reporte.append("Placa: ").append(c.getPlaca())
                    .append("\nCarga: ").append(c.getCarga()).append(" ton")
                    .append("\nPeajes: ").append(c.getNumeroPeajes())
                    .append("\n────────\n");
        }
        mostrarMensaje(reporte.toString());
    }

    private static void verConductoresApp(Empresa empresa) {
        if (empresa.getListaConductores().isEmpty()) {
            mostrarMensaje("No hay conductores registrados.");
            return;
        }
        StringBuilder reporte = new StringBuilder("CONDUCTORES:\n\n");
        for (Conductor c : empresa.getListaConductores()) {
            reporte.append(c.nombre()).append(" ").append(c.apellidos())
                    .append(" (").append(c.identificacion()).append(")\n");
        }
        mostrarMensaje(reporte.toString());
    }

    private static void verVehiculosApp(Empresa empresa) {
        if (empresa.getListaVehiculos().isEmpty()) {
            mostrarMensaje("No hay vehículos registrados.");
            return;
        }
        StringBuilder reporte = new StringBuilder("VEHÍCULOS:\n\n");
        for (Vehiculo v : empresa.getListaVehiculos()) {
            reporte.append(v.getPlaca()).append(" - ").append(v.getClass().getSimpleName())
                    .append("\n");
        }
        mostrarMensaje(reporte.toString());
    }

    private static void verRecaudadoresApp(Empresa empresa) {
        if (empresa.getListaRecaudadores().isEmpty()) {
            mostrarMensaje("No hay recaudadores registrados.");
            return;
        }
        StringBuilder reporte = new StringBuilder("RECAUDADORES:\n\n");
        for (Recaudador r : empresa.getListaRecaudadores()) {
            reporte.append(r.getNombre()).append(" ").append(r.getApellidos())
                    .append("\n");
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