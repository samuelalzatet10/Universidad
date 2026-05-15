package co.edu.unquindio.poo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Recaudador implements Cobrador {
    private String nombre;
    private String apellidos;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private double sueldoMensual;
    private List<RegistroPeaje> listaRegistroPeaje;

    public Recaudador(String nombre, String apellidos, String identificacion,
                    LocalDate fechaNacimiento, double sueldoMensual) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.sueldoMensual = sueldoMensual;
        this.listaRegistroPeaje = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getIdentificacion(){
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public List<RegistroPeaje> getListaRegistroPeaje() {
        return listaRegistroPeaje;
    }

    public void setListaRegistroPeaje(List<RegistroPeaje> listaRegistroPeaje) {
        this.listaRegistroPeaje = listaRegistroPeaje;
    }

    @Override
    public String toString() {
        return "Recaudador{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sueldoMensual=" + sueldoMensual +
                ", listaRegistroPeaje=" + listaRegistroPeaje +
                '}';
    }
    @Override
    public RegistroPeaje cobrarPeaje(Vehiculo vehiculo) {
        double valor = vehiculo.calcularTarifa();
        vehiculo.setNumeroPeajes(vehiculo.getNumeroPeajes() + 1);
        RegistroPeaje registro = new RegistroPeaje(valor, LocalDate.now(), LocalTime.now(), vehiculo, this);
        listaRegistroPeaje.add(registro);
        return registro;
    }

}
