package co.edu.unquindio.poo.model;

public class Repuesto {
    private String nombre;
    private String codigo;
    private int cantidad;
    private double costoUnitario;

    public Repuesto(String nombre, String codigo, int cantidad, double costoUnitario) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }
    public double subtotal(){
        return cantidad * costoUnitario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getCostoUnitario() {
        return costoUnitario;
    }
    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }
    @Override
    public String toString() {
        return "Repuesto: " + nombre + ", Cantidad: " + cantidad + ", Subtotal: $" +  String.format("%,.2f", subtotal());
    }
}
