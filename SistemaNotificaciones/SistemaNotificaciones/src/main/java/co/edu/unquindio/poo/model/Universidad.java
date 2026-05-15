package co.edu.unquindio.poo.model;

import java.util.List;
import java.util.ArrayList;

public class Universidad {
    private String nombre;
    private GestorNotificacion gestorNotificacion;
    private List<Noticia>listaNoticias;
    private List<Notificacion> listaNotificaciones;

    public Universidad(String nombre, GestorNotificacion gestorNotificacion) {
        this.nombre = nombre;
        this.gestorNotificacion= gestorNotificacion;
        this.listaNoticias= new ArrayList<>();
        this.listaNotificaciones= new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GestorNotificacion getGestorNotificacion() {
        return gestorNotificacion;
    }

    public void setGestorNotificacion(GestorNotificacion gestorNotificacion) {
        this.gestorNotificacion = gestorNotificacion;
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
        return "Universidad{" +
                "nombre='" + nombre + '\'' +
                ", gestorNotificacion=" + gestorNotificacion +
                ", listaNoticias=" + listaNoticias +
                ", listaNotificaciones=" + listaNotificaciones +
                '}';
    }
    // CRUD NOTICIA
    public void agregarNoticia(Noticia noticia) {
        listaNoticias.add(noticia);
    }

    public Noticia buscarNoticia(String titulo) {
        for (Noticia n : listaNoticias) {
            if (n.getTitulo().equalsIgnoreCase(titulo)) return n;
        }
        return null;
    }

    public boolean actualizarNoticia(String titulo, Noticia nueva) {
        for (int i = 0; i < listaNoticias.size(); i++) {
            if (listaNoticias.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                listaNoticias.set(i, nueva);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarNoticia(String titulo) {
        return listaNoticias.removeIf(n -> n.getTitulo().equalsIgnoreCase(titulo));
    }
    // CRUD NOTIFICACION
    public void agregarNotificacion(Notificacion notificacion) {
        listaNotificaciones.add(notificacion);
    }

    public Notificacion buscarNotificacion(String estado) {
        for (Notificacion n : listaNotificaciones) {
            if (n.getEstado().equalsIgnoreCase(estado)) return n;
        }
        return null;
    }

    public boolean actualizarNotificacion(int indice, Notificacion nueva) {
        if (indice >= 0 && indice < listaNotificaciones.size()) {
            listaNotificaciones.set(indice, nueva);
            return true;
        }
        return false;
    }

    public boolean eliminarNotificacion(int indice) {
        if (indice >= 0 && indice < listaNotificaciones.size()) {
            listaNotificaciones.remove(indice);
            return true;
        }
        return false;
    }
}
