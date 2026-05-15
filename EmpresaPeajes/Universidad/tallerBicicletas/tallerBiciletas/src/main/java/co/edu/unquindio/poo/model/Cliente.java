package co.edu.unquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {
    private String nombre;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String direccion;
    private int numeroBicicletas;

    private Taller ownedByTaller;
    private List<Bicicleta> listaBicicletas;
    private List<OrdenServicio> listaOrdenesServicio;

    public Cliente(String nombre, String identificacion, LocalDate fechaNacimiento, String telefono, String direccion, int numeroBicicletas, Taller ownedByTaller) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.numeroBicicletas = numeroBicicletas;
        this.ownedByTaller = ownedByTaller;
        this.listaBicicletas = new ArrayList<>();
        this.listaOrdenesServicio = new ArrayList<>();
    }
    public Cliente() {
    }
    public Cliente(String nombre,String identificacion){
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.listaBicicletas = new ArrayList<>();
    }
    public boolean añadirBicicleta(Bicicleta bicicleta) {
        if (listaBicicletas.size() < numeroBicicletas) {
            listaBicicletas.add(bicicleta);
            return true;
        }
        return false;
    }
    public String obtenerResumen() {
        return "Cliente: " + nombre + " | ID: " + identificacion + " | Bicis: " + listaBicicletas.size() + "/" + numeroBicicletas;
    }
    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    public String getnombre(){
        return nombre;
    }
    public void setidentificacion(String identificacion){
        this.identificacion = identificacion;
    }
    public String getidentificacion(){
        return identificacion;
    }
    public void setfechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public LocalDate getfechaNacimiento(){
        return fechaNacimiento;
    }
    public void settelefono(String telefono){
        this.telefono = telefono;
    }
    public String gettelefono(){
        return telefono;
    }
    public void setdireccion(String direccion){
        this.direccion = direccion;
    }
    public String getdireccion(){
        return direccion;
    }
    public void setnumeroBicicletas(int numeroBicicletas){
        this.numeroBicicletas = numeroBicicletas;
    }
    public int getnumeroBicicletas(){
        return numeroBicicletas;
    }
    public void setownedByTaller(Taller ownedByTaller){
        this.ownedByTaller = ownedByTaller;
    }
    public Taller getownedByTaller(){
        return ownedByTaller;
    }
    public void setListaBicicletas(List<Bicicleta> listaBicicletas){
        this.listaBicicletas = listaBicicletas;
    }
    public List<Bicicleta> getListaBicicletas(){
        return listaBicicletas;
    }
    public void setListaOrdenesServicio(List<OrdenServicio> listaOrdenesServicio){
        this.listaOrdenesServicio = listaOrdenesServicio;
    }
    public List<OrdenServicio> getListaOrdenesServicio(){
        return listaOrdenesServicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(identificacion, cliente.identificacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion);
    }

    @Override
    public String toString() {
        return obtenerResumen();
    }
}