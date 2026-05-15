package co.edu.unquindio.poo.model;

import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private List<Recaudador>listaRecaudadores;
    private List<Conductor>listaConductores;
    private List<Vehiculo>listaVehiculos;

    public Empresa(String nombre){
        this.nombre= nombre;
        this.listaRecaudadores= new ArrayList<>();
        this.listaConductores= new ArrayList<>();
        this.listaVehiculos= new ArrayList<>();


    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre= nombre;
    }

    public List<Recaudador> getListaRecaudadores() {
        return listaRecaudadores;
    }

    public void setListaRecaudadores(List<Recaudador> listaRecaudadores) {
        this.listaRecaudadores = listaRecaudadores;
    }

    public List<Conductor> getListaConductores() {
        return listaConductores;
    }

    public void setListaConductores(List<Conductor> listaConductores) {
        this.listaConductores = listaConductores;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", listaRecaudadores=" + listaRecaudadores +
                ", listaConductores=" + listaConductores +
                ", listaVehiculos=" + listaVehiculos +
                '}';
    }
    //CRUD RECAUDADOR
    public boolean agregarRecaudador(Recaudador recaudador) {
        if (recaudador == null || listaRecaudadores.contains(recaudador)) return false;
        return listaRecaudadores.add(recaudador);
    }

    public boolean eliminarRecaudador(Recaudador recaudador) {
        return listaRecaudadores.remove(recaudador);
    }

    public Recaudador buscarRecaudador(String identificacion) {
        return listaRecaudadores.stream()
                .filter(c -> c.getIdentificacion().equals(identificacion))
                .findFirst().orElse(null);
    }
    // ─── CRUD CONDUCTORES ──────────────────────────────────────────
    public boolean agregarConductor(Conductor conductor) {
        if (conductor == null || listaConductores.contains(conductor)) return false;
        return listaConductores.add(conductor);
    }

    public boolean eliminarConductor(Conductor conductor) {
        return listaConductores.remove(conductor);
    }

    public Conductor buscarConductor(String identificacion) {
        return listaConductores.stream()
                .filter(c -> c.identificacion().equals(identificacion))
                .findFirst().orElse(null);
    }

    // ─── CRUD VEHÍCULOS ────────────────────────────────────────────
    public boolean agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null || listaVehiculos.contains(vehiculo)) return false;
        return listaVehiculos.add(vehiculo);
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) {
        return listaVehiculos.remove(vehiculo);
    }

    public Vehiculo buscarVehiculo(String placa) {
        return listaVehiculos.stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst().orElse(null);
    }
    public List<Camion> getCamionesCargaMayorYMasDeCincoPeajes() {
        return listaVehiculos.stream()
                .filter(v -> v instanceof Camion)
                .map(v -> (Camion) v)
                .filter(c -> c.getCarga() > 10 && c.getNumeroPeajes() > 5)
                .toList();
    }


}
