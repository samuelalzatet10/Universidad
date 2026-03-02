package co.edu.unquindio.poo.model;

public class Tarea {
    private String descripcionTarea;
    private double costoManoObra;

    public Tarea(String descripcionTarea, double costoManoObra) {
        this.descripcionTarea = descripcionTarea;
        this.costoManoObra = costoManoObra;
    }
    public String getDescripcionTarea() {
        return descripcionTarea;
    }
    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }
    public double getCostoManoObra() {
        return costoManoObra;
    }
    public void setCostoManoObra(double costoManoObra) {
        this.costoManoObra = costoManoObra;
    }
    @Override
    public String toString() {
        return "Tarea: " + descripcionTarea + ", Costo: $" + String.format("%,.2f", costoManoObra);
    }
}
