package co.edu.unquindio.poo.model;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String identificacion;
    private String telefono;
    private ArrayList<Bicicleta>listaBicicletas;

    public Cliente (String nombre, String identificacion, String telefono) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.listaBicicletas = new ArrayList<Bicicleta>();
    }
    public void addBicicleta(Bicicleta b) {
        this.listaBicicletas.add(b);
    }
    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString() {
        return "Cliente: " + nombre + "\nID: " + identificacion + "\nBicis: " + listaBicicletas.size();
    }
}
