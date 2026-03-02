package co.edu.unquindio.poo.model;

import java.util.ArrayList;

public class Servicio {
    private String problemaServicio, fechaIngreso, codigoServicio, estado;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private ArrayList<Tarea> listaTareas;
    private ArrayList<Repuesto> listaRepuestos;

    public Servicio(String problemaServicio, String fechaIngreso, String codigoServicio, String estado, Bicicleta b) {
        this.problemaServicio = problemaServicio;
        this.fechaIngreso = fechaIngreso;
        this.codigoServicio = codigoServicio;
        this.estado = estado;
        this.bicicleta = b;
        this.listaTareas = new ArrayList<Tarea>();
        this.listaRepuestos = new ArrayList<Repuesto>();
    }

    public void asignarMecanico(Mecanico m) {
        this.mecanico = m;
    }

    public void agregarTarea(Tarea t) {
        this.listaTareas.add(t);
    }

    public void agregarRepuesto(Repuesto r) {
        this.listaRepuestos.add(r);
    }

    public void cambiarEstado(String estado) {
        this.estado = estado;
    }

    public double costoTotal() {
        double total = 0;
        for (Tarea t : listaTareas) total += t.getCostoManoObra();
        for(Repuesto r : listaRepuestos) total += r.subtotal();
        return total;
    }
    public String getEstado() {
        return estado;
    }
    public String getCodigoServicio() {
        return codigoServicio;
    }
    public Bicicleta getBicicleta() {
        return bicicleta;
    }
    public Mecanico getMecanico() {
        return mecanico;
    }
    @Override
    public String toString() {
        return "Orden: " + codigoServicio + "Estado: " + estado + "Total: $" + String.format("%,.2f", costoTotal());
    }

}
