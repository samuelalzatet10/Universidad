package co.edu.unquindio.poo.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo {
    private String placa;
    private int numeroPeajes;
    private Conductor propietario;
    private List<RegistroPeaje>listaRegistroPeaje;

    public Vehiculo(String placa, int numeroPeajes, Conductor propietario){
        this.placa= placa;
        this.numeroPeajes= numeroPeajes;
        this.propietario= propietario;
        this.listaRegistroPeaje= new ArrayList<>();

    }
    public String getPlaca(){
        return placa;
    }
    public void setPlaca(String placa){
        this.placa= placa;
    }

    public int getNumeroPeajes() {
        return numeroPeajes;
    }

    public void setNumeroPeajes(int numeroPeajes) {
        this.numeroPeajes = numeroPeajes;
    }
    public Conductor getPropietario(){
        return propietario;
    }
    public void setPropietario(Conductor propietario){
        this.propietario= propietario;
    }
    public List<RegistroPeaje>getListaRegistroPeaje(){
        return listaRegistroPeaje;
    }

    public void setListaRegistroPeaje(List<RegistroPeaje> listaRegistroPeaje) {
        this.listaRegistroPeaje = listaRegistroPeaje;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", numeroPeajes=" + numeroPeajes +
                ", propietario=" + propietario +
                ", listaRegistroPeaje=" + listaRegistroPeaje +
                '}';
    }
    public abstract double calcularTarifa();

}
