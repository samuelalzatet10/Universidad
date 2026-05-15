package co.edu.unquindio.poo.app;

import co.edu.unquindio.poo.model.*;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Universidad universidad = new Universidad("Universidad del Quindío", null);
        GestorNotificacion gestor = new GestorNotificacion(universidad);
        universidad.setGestorNotificacion(gestor);

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(null,
                    "=== SISTEMA DE NOTIFICACIONES ===\n" +
                            "1. Agregar noticia\n" +
                            "2. Agregar notificación\n" +
                            "3. Enviar noticia\n" +
                            "4. Consultar noticias pendientes por palabra clave\n" +
                            "5. Buscar etiqueta más usada\n" +
                            "6. Mostrar todas las noticias\n" +
                            "0. Salir\n\n" +
                            "Seleccione una opción:");

            if (opcion == null) break;

            switch (opcion.trim()) {

                case "1" -> {
                    String titulo = JOptionPane.showInputDialog("Título de la noticia:");
                    if (titulo == null) break;
                    String descripcion = JOptionPane.showInputDialog("Descripción:");
                    if (descripcion == null) break;
                    String etiquetasInput = JOptionPane.showInputDialog("Etiquetas (separadas por coma):");
                    if (etiquetasInput == null) break;

                    Noticia noticia = new Noticia(titulo, descripcion, universidad, EstadoNoticia.PENDIENTE, gestor);
                    for (String etiqueta : etiquetasInput.split(",")) {
                        noticia.agregarEtiqueta(etiqueta.trim());
                    }
                    gestor.agregarNoticia(noticia);
                    JOptionPane.showMessageDialog(null, "Noticia agregada: " + titulo);
                }

                case "2" -> {
                    String tipo = JOptionPane.showInputDialog(null,
                            "Tipo de notificación:\n1. Email\n2. SMS\n3. Push");
                    if (tipo == null) break;

                    TipoNotificacion tipoNotificacion = switch (tipo.trim()) {
                        case "1" -> {
                            String correo = JOptionPane.showInputDialog("Correo electrónico:");
                            yield correo != null ? new Email(correo) : null;
                        }
                        case "2" -> {
                            String telefono = JOptionPane.showInputDialog("Número de teléfono:");
                            yield telefono != null ? new Mensaje(telefono) : null;
                        }
                        case "3" -> {
                            String id = JOptionPane.showInputDialog("ID del dispositivo:");
                            yield id != null ? new Push(id) : null;
                        }
                        default -> null;
                    };

                    if (tipoNotificacion == null) break;

                    Notificacion notificacion = new Notificacion(null, null, null, gestor, universidad);
                    notificacion.agregarTipoNotificacion(tipoNotificacion);
                    gestor.agregarNotificacion(notificacion);
                    JOptionPane.showMessageDialog(null, "Notificación agregada: " + tipoNotificacion);
                }

                case "3" -> {
                    String titulo = JOptionPane.showInputDialog("Título de la noticia a enviar:");
                    if (titulo == null) break;

                    Noticia noticia = gestor.getListaNoticias().stream()
                            .filter(n -> n.getTitulo().equalsIgnoreCase(titulo))
                            .findFirst()
                            .orElse(null);

                    if (noticia == null) {
                        JOptionPane.showMessageDialog(null, "Noticia no encontrada.");
                    } else {
                        gestor.enviarNoticia(noticia);
                        JOptionPane.showMessageDialog(null,
                                "Noticia enviada.\nNuevo estado: " + noticia.getEstadoNoticia());
                    }
                }

                case "4" -> {
                    String palabra = JOptionPane.showInputDialog("Palabra clave a buscar en el título:");
                    if (palabra == null) break;

                    List<Noticia> resultado = gestor.consultarNoticiasPendientesPorPalabraClave(palabra);

                    if (resultado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay noticias pendientes con esa palabra clave.");
                    } else {
                        StringBuilder sb = new StringBuilder("Noticias pendientes con \"" + palabra + "\":\n\n");
                        for (Noticia n : resultado) {
                            sb.append("- ").append(n.getTitulo()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                }

                case "5" -> {
                    String etiqueta = gestor.buscarEtiquetaMasUsada();
                    if (etiqueta == null) {
                        JOptionPane.showMessageDialog(null, "No hay etiquetas registradas.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Etiqueta más usada: " + etiqueta);
                    }
                }

                case "6" -> {
                    List<Noticia> noticias = gestor.getListaNoticias();
                    if (noticias.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay noticias registradas.");
                    } else {
                        StringBuilder sb = new StringBuilder("=== NOTICIAS ===\n\n");
                        for (Noticia n : noticias) {
                            sb.append("Título: ").append(n.getTitulo()).append("\n");
                            sb.append("Descripción: ").append(n.getDescripcion()).append("\n");
                            sb.append("Estado: ").append(n.getEstadoNoticia()).append("\n");
                            sb.append("Etiquetas: ").append(n.getEtiquetas()).append("\n\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                }

                case "0" -> JOptionPane.showMessageDialog(null, "Hasta luego.");

                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (!opcion.equals("0"));
    }
}
