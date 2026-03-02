package co.edu.unquindio.poo.model;

public class Mecanico {
    private String nombre;
    private String identificacion;

    public Mecanico(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    @Override
    public String toString() {
        return "Nombre: "  + nombre + ", ID: " + identificacion;
    }
}

