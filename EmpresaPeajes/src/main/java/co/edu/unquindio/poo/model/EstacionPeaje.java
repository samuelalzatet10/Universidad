package co.edu.unquindio.poo.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionPeaje implements Cobrador {
    private String nombre;
    private String departamento;
    private double valorTotal;
    private List<RegistroPeaje>listaRegistroPeaje;

    public EstacionPeaje(String nombre, String departamento){
        this.nombre= nombre;
        this.departamento= departamento;
        this.valorTotal=0;
        this.listaRegistroPeaje= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDepartamento(){
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public List<RegistroPeaje>getListaRegistroPeaje(){
        return listaRegistroPeaje;
    }
    public void setListaRegistroPeaje(List<RegistroPeaje>listaRegistroPeaje){
        this.listaRegistroPeaje= listaRegistroPeaje;
    }

    @Override
    public String toString() {
        return "EstacionPeaje{" +
                "nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", valorTotal=" + valorTotal +
                ", listaRegistroPeaje=" + listaRegistroPeaje +
                '}';
    }
    @Override
    public RegistroPeaje cobrarPeaje(Vehiculo vehiculo) {
        double valor = vehiculo.calcularTarifa();
        valorTotal += valor;
        vehiculo.setNumeroPeajes(vehiculo.getNumeroPeajes() + 1);
        RegistroPeaje registro= new RegistroPeaje(valor, LocalDate.now(), LocalTime.now(), vehiculo, this);
        listaRegistroPeaje.add(registro);
        return registro;
    }
}
