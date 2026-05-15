package co.edu.unquindio.poo.model;

import java.util.*;
import java.util.stream.Collectors;

public class GestorNotificacion {
    private Universidad ownedByUniversidad;
    private List<Noticia> listaNoticias;
    private List<Notificacion> listaNotificaciones;

    public GestorNotificacion(Universidad ownedByUniversidad) {
        this.ownedByUniversidad = ownedByUniversidad;
        this.listaNoticias = new ArrayList<>();
        this.listaNotificaciones = new ArrayList<>();
    }

    public Universidad getOwnedByUniversidad() {
        return ownedByUniversidad;
    }

    public void setOwnedByUniversidad(Universidad ownedByUniversidad) {
        this.ownedByUniversidad = ownedByUniversidad;
    }

    public List<Noticia> getListaNoticias() {
        return listaNoticias;
    }

    public void setListaNoticias(List<Noticia> listaNoticias) {
        this.listaNoticias = listaNoticias;
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    @Override
    public String toString() {
        return "GestorNotificacion{" +
                "ownedByUniversidad=" + ownedByUniversidad +
                ", listaNoticias=" + listaNoticias +
                ", listaNotificaciones=" + listaNotificaciones +
                '}';
    }

    public void agregarNoticia(Noticia noticia) {
        listaNoticias.add(noticia);
    }

    public void agregarNotificacion(Notificacion notificacion) {
        listaNotificaciones.add(notificacion);
    }
    public void enviarNoticia(Noticia noticia) {
        for (Notificacion notificacion : listaNotificaciones) {
            for (TipoNotificacion tipo : notificacion.getListaTipoNotificacion()) {
                EstadoNoticia estado = tipo.enviarNotificacion();
                noticia.setEstadoNoticia(estado);
            }
        }
    }
    public List<Noticia> consultarNoticiasPendientesPorPalabraClave(String palabraClave) {
        return listaNoticias.stream()
                .filter(n -> n.getEstadoNoticia() == EstadoNoticia.PENDIENTE)
                .filter(n -> n.getTitulo().toLowerCase().contains(palabraClave.toLowerCase()))
                .collect(Collectors.toList());
    }
    public String buscarEtiquetaMasUsada() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Noticia noticia : listaNoticias) {
            for (String etiqueta : noticia.getEtiquetas()) {
                conteo.put(etiqueta, conteo.getOrDefault(etiqueta, 0) + 1);
            }
        }
        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
