package co.edu.unquindio.poo.model;

public class Repuesto {
    private String nombre;
    private double precio;
    private String codigo;
    private int cantidad;

    /**
     * Constructor de Repuesto
     * @param nombre Descripción del repuesto (ej. Pastillas de freno)
     * @param precio Precio unitario
     * @param cantidad Unidades utilizadas
     */
    public Repuesto(String nombre, double precio,String codigo, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    public String getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        return  nombre + " | Cant: " + cantidad + "|Codigo: " +  codigo + " | Total: $" + calcularSubtotal();
    }
}