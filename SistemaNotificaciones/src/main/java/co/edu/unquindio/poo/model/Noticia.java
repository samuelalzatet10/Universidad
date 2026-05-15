package co.edu.unquindio.poo.model;

import java.util.*;

public class Noticia {
    private String titulo;
    private String descripcion;
    private Universidad ownedByUniversidad;
    private EstadoNoticia estadoNoticia;
    private List<Notificacion>listaNotificaciones;
    private GestorNotificacion gestorNotificacion;
    private List<String> etiquetas;

    public Noticia(String titulo, String descripcion, Universidad ownedByUniversidad, EstadoNoticia estadoNoticia, GestorNotificacion gestorNotificacion){
        this.titulo= titulo;
        this.descripcion= descripcion;
        this.ownedByUniversidad= ownedByUniversidad;
        this.estadoNoticia= estadoNoticia.PENDIENTE;
        this.listaNotificaciones= new ArrayList<>();
        this.gestorNotificacion= gestorNotificacion;
        this.etiquetas= new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Universidad getOwnedByUniversidad() {
        return ownedByUniversidad;
    }
    public void setOwnedByUniversidad(Universidad ownedByUniversidad) {
        this.ownedByUniversidad = ownedByUniversidad;
    }
    public EstadoNoticia getEstadoNoticia(){
        return estadoNoticia;
    }
    public void setEstadoNoticia(EstadoNoticia estadoNoticia) {
        this.estadoNoticia = estadoNoticia;
    }
    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }
    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }
    public GestorNotificacion getGestorNotificacion() {
        return gestorNotificacion;
    }
    public void setGestorNotificacion(GestorNotificacion gestorNotificacion) {
        this.gestorNotificacion = gestorNotificacion;
    }
    public List<String> getEtiquetas() {
        return etiquetas;
    }
    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ownedByUniversidad=" + ownedByUniversidad +
                ", estadoNoticia=" + estadoNoticia +
                ", listaNotificaciones=" + listaNotificaciones +
                ", gestorNotificacion=" + gestorNotificacion +
                ", etiquetas=" + etiquetas +
                '}';
    }
    public void agregarEtiqueta(String etiqueta) {
        etiquetas.add(etiqueta);
    }

    public boolean eliminarEtiqueta(String etiqueta) {
        return etiquetas.remove(etiqueta);
    }
}
