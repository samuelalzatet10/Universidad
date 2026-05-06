package co.edu.unquindio.poo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrdenServicio {
    // Enum interno para el estado
    public enum EstadoOrden {
        RECIBIDA, EN_PROCESO, TERMINADA, ENTREGADA
    }

    private String codigo;
    private String problema;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private EstadoOrden estado;

    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private ArrayList<Tarea> listaTareas;
    private ArrayList<Repuesto> listaRepuestos;

    public OrdenServicio(String codigo, String problema, LocalDateTime fechaIngreso, Bicicleta bicicleta, Mecanico mecanico) {
        this.codigo = codigo;
        this.problema = problema;
        this.fechaIngreso = fechaIngreso;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;
        this.estado = EstadoOrden.RECIBIDA; // Estado inicial por defecto
        this.listaTareas = new ArrayList<>();
        this.listaRepuestos = new ArrayList<>();
    }

    /**
     * Método para calcular el costo total (No es atributo)
     */
    public double calcularCostoTotal() {
        double total = 0;
        for (Tarea t : listaTareas) {
            total += t.getPrecio();
        }
        for (Repuesto r : listaRepuestos) {
            total += r.calcularSubtotal();
        }
        return total;
    }

    // --- GETTERS Y SETTERS ---
    public String getCodigo() { return codigo; }
    public String getProblema() { return problema; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public EstadoOrden getEstado() { return estado; }
    public Bicicleta getBicicleta() { return bicicleta; }
    public Mecanico getMecanico() { return mecanico; }

    public void setEstado(EstadoOrden estado) { this.estado = estado; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public void setProblema(String problema) { this.problema = problema; }

    public ArrayList<Tarea> getListaTareas() { return listaTareas; }
    public ArrayList<Repuesto> getListaRepuestos() { return listaRepuestos; }


    @Override
    public String toString() {
        return "-------------------------------------------\n" +
                "       ORDEN DE SERVICIO: " + codigo + "\n" +
                "-------------------------------------------\n" +
                "Estado: " + estado + "\n" +
                "Fecha Ingreso: " + fechaIngreso + "\n" +
                "Bicicleta: " + bicicleta.getMarca() + " (" + bicicleta.getId() + ")\n" +
                "Mecánico: " + mecanico.getNombre() + "\n" +
                "Problema: " + problema + "\n" +
                "-------------------------------------------\n" +
                "TOTAL A PAGAR: $" + calcularCostoTotal() + "\n" +
                "-------------------------------------------";
    }
}

