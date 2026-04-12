package co.edu.unquindio.poo.model;
import java.util.ArrayList;
import java.time.LocalTime;

public class Empresa {
    private String nombre;

    private ArrayList<Empleado> listaEmpleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public ArrayList<Empleado> consultarEmpleadosTarde(LocalTime horaEntradaEmpresa) {
        ArrayList<Empleado> resultado = new ArrayList<>();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            Empleado empleadoAux = listaEmpleados.get(i);

            if (empleadoAux.llegoTarde(horaEntradaEmpresa)) {
                resultado.add(empleadoAux);
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        return "Empresa [nombre=" + nombre + ", listaEmpleados=" + listaEmpleados + "]";
    }

// CRUD Empleado

    /**
     *
     * @param nombre
     * @param cedula
     * @param horaEntrada
     * @param horaSalida
     * @return
     */

    public String registrarEmpleado(String nombre, String cedula, LocalTime horaEntrada, LocalTime horaSalida) {
        String resultado = "";
        Empleado empleadoEncontrado = buscarEmpleado(cedula);

        if (empleadoEncontrado != null) {
            resultado = "El empleado ya está registrado";
        } else {
            // Se crea con todo de una vez
            Empleado nuevoEmpleado = new Empleado(nombre, cedula, horaEntrada, horaSalida);
            listaEmpleados.add(nuevoEmpleado);
            resultado = "El empleado se ha registrado";
        }
        return resultado;
    }

    /**
     *
     * @param cedula
     * @return
     */

    private Empleado buscarEmpleado(String cedula) {
        Empleado empleadoEncontrado = null;

        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCedula().equals(cedula)) {
                empleadoEncontrado = empleado;
                break; // Lo encontramos, no necesitamos seguir recorriendo
            }
        }
        return empleadoEncontrado;
    }

    /**
     *
     * @param nombre
     * @param cedula
     * @param horaEntrada
     * @param horaSalida
     * @return
     */

    public String actualizarEmpleado(String nombre, String cedula, LocalTime horaEntrada, LocalTime horaSalida) {
        String resultado = "";
        Empleado empleadoEncontrado = buscarEmpleado(cedula);
        if (empleadoEncontrado == null) {
            resultado = "No existe el empleado con la cédula: " + cedula;
        } else {
            empleadoEncontrado.setNombre(nombre);
            empleadoEncontrado.setCedula(cedula);
            empleadoEncontrado.setHoraEntrada(horaEntrada);
            empleadoEncontrado.setHoraSalida(horaSalida);
            resultado = "El empleado se ha actualizado correctamente";
        }
        return resultado;
    }

    /**
     *
     * @param cedula
     * @return
     */
    public String eliminarEmpleado(String cedula) {
        Empleado empleadoEncontrado = buscarEmpleado(cedula);

        if (empleadoEncontrado == null) {
            return "Error: El empleado no existe.";
        }
        listaEmpleados.remove(empleadoEncontrado);
        return "Empleado con cédula " + cedula + " ha sido eliminado.";
    }
}


