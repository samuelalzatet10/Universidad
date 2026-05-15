package co.edu.unquindio.poo.model;

public class Laser implements Impresora {

    private String codigo;
    private String nombre;
    private int nivelToner;
    private int capacidad;


    public Laser(String nombre, String codigo, int nivelToner, int capacidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nivelToner = nivelToner;
        this.capacidad = capacidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelToner() {
        return nivelToner;
    }

    public void setNivelToner(int nivelToner) {
        this.nivelToner = nivelToner;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Laser{" +
                "nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", nivelToner=" + nivelToner +
                ", capacidad=" + capacidad +
                '}';
    }

    @Override
    public void imprimir(Documento documento) {

    }
}
