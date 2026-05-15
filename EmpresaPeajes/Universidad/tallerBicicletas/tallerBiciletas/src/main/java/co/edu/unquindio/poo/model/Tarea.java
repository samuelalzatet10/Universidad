package co.edu.unquindio.poo.model;

public class Tarea {
    private String codigo;
    private String nombre;
    private double precio;

    public Tarea(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }


    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }


    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "TAREA [" + codigo + "]\n" +
                "Descripción: " + nombre + "\n" +
                "Costo Servicio: $" + precio + "\n" +
                "---------------------------";
    }
}