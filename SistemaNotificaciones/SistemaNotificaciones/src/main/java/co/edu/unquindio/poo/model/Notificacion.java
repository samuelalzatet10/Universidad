package co.edu.unquindio.poo.model;

import java.util.*;
import java.time.LocalDate;

public class Notificacion {
    private String estado;
    private LocalDate fechaEnvio;
    private Noticia noticia;
    private GestorNotificacion gestorNotificacion;
    private Universidad ownedByUniversidad;
    private List<TipoNotificacion>listaTipoNotificacion;

    public Notificacion(String estado, LocalDate fechaEnvio, Noticia noticia, GestorNotificacion gestorNotificacion, Universidad ownedByUniversidad){
        this.estado= "pendiente";
        this.fechaEnvio= null;
        this.noticia= noticia;
        this.gestorNotificacion= gestorNotificacion;
        this.ownedByUniversidad= ownedByUniversidad;
        this.listaTipoNotificacion= new ArrayList<>();
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }
    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    public Noticia getNoticia(){
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
    public GestorNotificacion getGestorNotificacion() {
        return gestorNotificacion;
    }
    public void setGestorNotificacion(GestorNotificacion gestorNotificacion) {
        this.gestorNotificacion = gestorNotificacion;
    }
    public Universidad getOwnedByUniversidad() {
        return ownedByUniversidad;
    }
    public void setOwnedByUniversidad(Universidad ownedByUniversidad) {
        this.ownedByUniversidad = ownedByUniversidad;
    }
    public List<TipoNotificacion> getListaTipoNotificacion() {
        return listaTipoNotificacion;
    }
    public void setListaTipoNotificacion(List<TipoNotificacion> listaTipoNotificacion) {
        this.listaTipoNotificacion = listaTipoNotificacion;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "estado='" + estado + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", noticia=" + noticia +
                ", gestorNotificacion=" + gestorNotificacion +
                ", ownedByUniversidad=" + ownedByUniversidad +
                ", listaTipoNotificacion=" + listaTipoNotificacion +
                '}';
    }
    public void agregarTipoNotificacion(TipoNotificacion tipo) {
        listaTipoNotificacion.add(tipo);
    }

    public boolean eliminarTipoNotificacion(TipoNotificacion tipo) {
        return listaTipoNotificacion.remove(tipo);
    }

}
