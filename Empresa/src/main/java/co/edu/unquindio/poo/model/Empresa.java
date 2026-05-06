package co.edu.unquindio.poo.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Empresa {
    private String nombre;
    private List<Empleado> listaEmpleados;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public String registrarEmpleado(Empleado empleado) {
        if (buscarEmpleado(empleado.getDocumento()).isPresent()) {
            return "Error: El empleado con documento " + empleado.getDocumento() + " ya existe.";
        }
        listaEmpleados.add(empleado);
        return "Empleado " + empleado.getNombre() + " registrado exitosamente.";
    }
    public String listarEmpleadosTipoPlanta() {
        String lista = "";
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoPlanta) {
                lista += e.mostrarInformacion() + "\n";
            }
        }
        return lista.isEmpty() ? "No hay empleados de planta." : lista;
    }

    public String listarEmpleadosTipoTemporal() {
        String lista = "";
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoTemporal) {
                lista += e.mostrarInformacion() + "\n";
            }
        }
        return lista.isEmpty() ? "No hay empleados temporales." : lista;
    }

    public String listarEmpleadosTipoVentas() {
        String lista = "";
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoVentas) {
                lista += e.mostrarInformacion() + "\n";
            }
        }
        return lista.isEmpty() ? "No hay empleados de ventas." : lista;
    }

    private Optional<Empleado> buscarEmpleado(String documento) {
        return listaEmpleados.stream()
                .filter(e -> e.getDocumento().equals(documento))
                .findAny();
    }

    public Empleado buscarEmpleadoPorDocumento(String documento) {
        return buscarEmpleado(documento).orElse(null);
    }

    public String mostrarEmpleadoMayorSalario() {
        if (listaEmpleados.isEmpty()) return "No hay empleados registrados.";

        Empleado mayor = listaEmpleados.get(0);
        for (Empleado e : listaEmpleados) {
            if (e.calcularSalarioNeto() > mayor.calcularSalarioNeto()) {
                mayor = e;
            }
        }

        return "EMPLEADO CON MAYOR SALARIO NETO:\n" + mayor.mostrarInformacion();
    }

    public String calcularNominaTotal() {
        if (listaEmpleados.isEmpty()) return "Nómina total: $0.00";

        float total = 0;
        for (Empleado e : listaEmpleados) {
            total += e.calcularSalarioNeto();
        }
        return "La nómina total de '" + nombre + "' es: $" + String.format("%.2f", total);
    }
    public List<Empleado> empleadosConSalarioMayorA(float valor) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado e : listaEmpleados) {
            if (e.calcularSalarioNeto() > valor) {
                resultado.add(e);
            }
        }
        return resultado;
    }
    public List<EmpleadoTemporal> empleadosTemporalesMasDe100Dias() {
        List<EmpleadoTemporal> resultado = new ArrayList<>();
        for (Empleado e : listaEmpleados) {
            if (e instanceof EmpleadoTemporal) {
                EmpleadoTemporal et = (EmpleadoTemporal) e;
                if (et.getDiasTrabajados() > 100) {
                    resultado.add(et);
                }
            }
        }
        return resultado;
    }

    public List<ResumenPago> obtenerResumenesPagos() {
        List<ResumenPago> listaResumenes = new ArrayList<>();
        for (Empleado e : listaEmpleados) {
            listaResumenes.add(e.generarResumenPago());
        }
        return listaResumenes;
    }
}