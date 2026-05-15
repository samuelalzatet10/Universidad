package co.edu.unquindio.poo.app;

import co.edu.unquindio.poo.model.*;
import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) {
        String nombre = ingresarDato("Ingrese el nombre de la empresa:");
        if (nombre == null) return;

        String codigoCentro = ingresarDato("Ingrese el código del Centro de Impresión:");
        if (codigoCentro == null) return;

        Empresa empresa = new Empresa(nombre, codigoCentro);
        mostrarMensaje("¡Bienvenido al sistema de impresión de " + empresa.getNombre() + "!");
        iniciarMenu(empresa);
    }

    // MENU PRINCIPAL

    public static void iniciarMenu(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = " MENÚ PRINCIPAL - " + empresa.getNombre() + " \n"
                    + "1. Gestionar Impresoras\n"
                    + "2. Centro de Impresión\n"
                    + "0. Salir\n\n"
                    + "Seleccione una opción:";
            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);
                switch (opcion) {
                    case 1 -> menuImpresoras(empresa);
                    case 2 -> menuCentroImpresion(empresa);
                    case 0 -> mostrarMensaje("Cerrando el sistema. ¡Hasta luego!");
                    default -> mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    // IMPRESORAS

    private static void menuImpresoras(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = " GESTIÓN DE IMPRESORAS \n"
                    + "1. Agregar Impresora\n"
                    + "2. Consultar Impresora\n"
                    + "3. Eliminar Impresora\n"
                    + "0. Volver\n\n"
                    + "Seleccione una opción:";
            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);
                switch (opcion) {
                    case 1 -> agregarImpresora(empresa);
                    case 2 -> consultarImpresora(empresa);
                    case 3 -> eliminarImpresora(empresa);
                    case 0 -> {}
                    default -> mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private static void agregarImpresora(Empresa empresa) {
        String tipoMenu = "¿Qué tipo de impresora desea agregar?\n"
                + "1. Cartucho\n"
                + "2. Láser\n"
                + "3. Otra (cualquier tipo)\n"
                + "0. Cancelar";
        try {
            String inputTipo = ingresarDato(tipoMenu);
            if (inputTipo == null) return;
            int tipo = Integer.parseInt(inputTipo);
            switch (tipo) {
                case 1 -> agregarCartucho(empresa);
                case 2 -> agregarLaser(empresa);
                case 3 -> agregarGenerica(empresa);
                case 0 -> {}
                default -> mostrarMensaje("Tipo no válido.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese un número válido.");
        }
    }

    private static void agregarGenerica(Empresa empresa) {
        try {
            String tipo = ingresarDato("Ingrese el tipo de impresora (ej: 3D, Matricial, Térmica):");
            if (tipo == null) return;
            String nombre = ingresarDato("Nombre de la impresora:");
            if (nombre == null) return;
            String codigo = ingresarDato("Código de la impresora:");
            if (codigo == null) return;

            Generica impresora = new Generica(nombre, codigo, tipo);
            boolean resultado = empresa.agregarImpresora(impresora);
            mostrarMensaje(resultado
                    ? "Impresora '" + tipo + " - " + nombre + "' agregada exitosamente."
                    : "Error: Ya existe una impresora con el código '" + codigo + "'.");
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void agregarCartucho(Empresa empresa) {
        try {
            String nombre = ingresarDato("Nombre de la impresora:");
            if (nombre == null) return;
            String codigo = ingresarDato("Código de la impresora:");
            if (codigo == null) return;
            int nivelTinta = Integer.parseInt(ingresarDato("Nivel de tinta (0-100):"));
            String color = ingresarDato("Color del cartucho:");
            if (color == null) return;

            Cartucho cartucho = new Cartucho(nombre, codigo, nivelTinta, color);
            boolean resultado = empresa.agregarImpresora(cartucho);
            mostrarMensaje(resultado
                    ? "Impresora Cartucho '" + nombre + "' agregada exitosamente."
                    : "Error: Ya existe una impresora con el código '" + codigo + "'.");
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void agregarLaser(Empresa empresa) {
        try {
            String nombre = ingresarDato("Nombre de la impresora:");
            if (nombre == null) return;
            String codigo = ingresarDato("Código de la impresora:");
            if (codigo == null) return;
            int nivelToner = Integer.parseInt(ingresarDato("Nivel de tóner (0-100):"));
            int capacidad = Integer.parseInt(ingresarDato("Capacidad de papel:"));

            Laser laser = new Laser(nombre, codigo, nivelToner, capacidad);
            boolean resultado = empresa.agregarImpresora(laser);
            mostrarMensaje(resultado
                    ? "Impresora Láser '" + nombre + "' agregada exitosamente."
                    : "Error: Ya existe una impresora con el código '" + codigo + "'.");
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private static void consultarImpresora(Empresa empresa) {
        String codigo = ingresarDato("Ingrese el código de la impresora:");
        if (codigo == null) return;
        Impresora impresora = empresa.consultarImpresora(codigo);
        mostrarMensaje(impresora != null
                ? impresora.toString()
                : "No se encontró la impresora con código '" + codigo + "'.");
    }

    private static void eliminarImpresora(Empresa empresa) {
        String codigo = ingresarDato("Código de la impresora a eliminar:");
        if (codigo == null) return;
        boolean resultado = empresa.eliminarImpresora(codigo);
        mostrarMensaje(resultado
                ? "Impresora eliminada exitosamente."
                : "Error: No se encontró la impresora con código '" + codigo + "'.");
    }

    // CENTRO DE IMPRESION

    private static void menuCentroImpresion(Empresa empresa) {
        int opcion = -1;
        do {
            String menu = " CENTRO DE IMPRESIÓN \n"
                    + "1. Conectar / Desconectar Impresora\n"
                    + "2. Ver Estado del Centro\n"
                    + "3. Enviar Solicitud de Impresión\n"
                    + "4. Ver Cola de Solicitudes\n"
                    + "5. Procesar Solicitud\n"
                    + "0. Volver\n\n"
                    + "Seleccione una opción:";
            try {
                String input = ingresarDato(menu);
                if (input == null) break;
                opcion = Integer.parseInt(input);
                switch (opcion) {
                    case 1 -> menuConectarDesconectar(empresa);
                    case 2 -> mostrarMensaje(empresa.getCentroImpresion().verEstadoImpresion());
                    case 3 -> enviarSolicitudApp(empresa);
                    case 4 -> mostrarMensaje(empresa.getCentroImpresion().verSolicitudes());
                    case 5 -> mostrarMensaje(empresa.getCentroImpresion().procesarSolicitud());
                    case 0 -> {}
                    default -> mostrarMensaje("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Error: Ingrese un número válido.");
            }
        } while (opcion != 0);
    }

    private static void menuConectarDesconectar(Empresa empresa) {
        String accionMenu = "¿Qué desea hacer?\n"
                + "1. Conectar Impresora\n"
                + "2. Desconectar Impresora\n"
                + "0. Cancelar";
        try {
            String inputAccion = ingresarDato(accionMenu);
            if (inputAccion == null) return;
            int accion = Integer.parseInt(inputAccion);
            if (accion == 0) return;
            if (accion != 1 && accion != 2) {
                mostrarMensaje("Opción no válida.");
                return;
            }
            String codigo = ingresarDato("Ingrese el código de la impresora:");
            if (codigo == null) return;
            String resultado = accion == 1
                    ? empresa.getCentroImpresion().conectarImpresora(codigo)
                    : empresa.getCentroImpresion().desconectarImpresora(codigo);
            mostrarMensaje(resultado);
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese un número válido.");
        }
    }

    private static void enviarSolicitudApp(Empresa empresa) {
        if (empresa.getCentroImpresion().getListaImpresoras().isEmpty()) {
            mostrarMensaje("Error: No hay impresoras registradas en el centro.");
            return;
        }
        try {
            String nombreDocumento = ingresarDato("Nombre del documento a imprimir:");
            if (nombreDocumento == null) return;
            String contenido = ingresarDato("Contenido del documento:");
            if (contenido == null) return;
            String nombreArea = ingresarDato("Nombre del área que envía la solicitud:");
            if (nombreArea == null) return;

            AreaEmpresa area = new AreaEmpresa(nombreArea, "AREA-" + System.currentTimeMillis());
            Documento documento = new Documento(nombreDocumento, contenido);
            documento.setAreaEmpresa(area);

            area.enviarSolicitud(empresa.getCentroImpresion(), documento);
            mostrarMensaje("Solicitud enviada exitosamente.\n"
                    + "Documento: " + nombreDocumento + "\n"
                    + "Área: " + nombreArea + "\n"
                    + "Posición en cola: " + empresa.getCentroImpresion().getListaSolicitudes().size());
        } catch (Exception e) {
            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    //Utilidades

    public static String ingresarDato(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje);
    }

    public static void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}