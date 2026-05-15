package co.edu.unquindio.poo.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Taller {
    private String nombre;
    private String direccion;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<Bicicleta> listaBicicletas;
    private ArrayList<OrdenServicio> listaOrdenesServicio;
    private ArrayList<Mecanico> listaMecanicos;
    private ArrayList<Repuesto> listaRepuestos;

    public Taller(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        listaClientes = new ArrayList<>();
        listaBicicletas = new ArrayList<>();
        listaOrdenesServicio = new ArrayList<>();
        listaMecanicos = new ArrayList<>();
        listaRepuestos = new ArrayList<>();

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaBiciceltas(ArrayList<Bicicleta> listaBiciceltas) {
        this.listaBicicletas = listaBiciceltas;
    }

    public ArrayList<Bicicleta> getListaBiciceltas() {
        return listaBicicletas;
    }

    public void setListaOrdenesServicio(ArrayList<OrdenServicio> listaOrdenesServicio) {
        this.listaOrdenesServicio = listaOrdenesServicio;
    }

    public ArrayList<OrdenServicio> getListaOrdenesServicio() {
        return listaOrdenesServicio;
    }

    public void setListaMecanicos(ArrayList<Mecanico> listaMecanicos) {
        this.listaMecanicos = listaMecanicos;
    }

    public ArrayList<Mecanico> getListaMecanicos() {
        return listaMecanicos;
    }

    public void setListaRepuestos(ArrayList<Repuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public ArrayList<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listaClientes=" + listaClientes +
                ", listaBiciceltas=" + listaBicicletas +
                ", listaOrdenesServicio=" + listaOrdenesServicio +
                ", listaMecanicos=" + listaMecanicos +
                ", listaRepuestos=" + listaRepuestos +
                '}';
    }
    public Bicicleta buscarBicicleta(String idBici) {
        for (Bicicleta b : listaBicicletas) {
            if (b.getId().equals(idBici)) {
                return b;
            }
        }
        return null;
    }

    //CRUD Cliente

    /**
     * Este metodo permite registrar un cliente nuevo
     *
     * @param nombre
     * @param identificacion
     * @param fechaNacimiento
     * @param telefono
     * @param direccion
     * @param numeroBicicletas
     * @return
     */
    public String registrarCliente(String nombre, String identificacion, String direccion, String telefono, LocalDate fechaNacimiento, int numeroBicicletas) {
        String resultado = "";
        Cliente clienteEncontrado = buscarCliente(identificacion);
        if (clienteEncontrado != null) {
            resultado = "El cliente ya está registrado";
        } else {
            Cliente cliente = new Cliente(nombre, identificacion, fechaNacimiento, telefono, direccion, numeroBicicletas, this);
            listaClientes.add(cliente);
            resultado = "El cliente se ha registrado";
        }
        return resultado;
    }

    /**
     *
     * @param identificacion
     * @return
     */
    private Cliente buscarCliente(String identificacion) {
        Cliente clienteAux = null;
        for (Cliente aux : listaClientes) {
            if (aux.getidentificacion().equals(identificacion)) {
                return clienteAux;
            }
        }
        return clienteAux;
    }

    ;

    /**
     *
     * @param nuevoNombre
     * @param identificacion
     * @param nuevaDireccion
     * @param nuevoTelefono
     * @param nuevoNumeroBicicletas
     * @return
     */
    public String actualizarCliente(String nuevoNombre, String identificacion, String nuevaDireccion, String nuevoTelefono, int nuevoNumeroBicicletas) {
        String resultado = "";
        Cliente clienteEncontrado = buscarCliente(identificacion);
        if (clienteEncontrado == null) {
            resultado = "No existe el cliente";
        } else {
            clienteEncontrado.setnombre(nuevoNombre);
            clienteEncontrado.setidentificacion(identificacion);
            clienteEncontrado.setdireccion(nuevaDireccion);
            clienteEncontrado.settelefono(nuevoTelefono);
            clienteEncontrado.setnumeroBicicletas(nuevoNumeroBicicletas);
            resultado = "El cliente se ha actualizado";
        }
        return resultado;
    }

    /**
     *
     * @param identificacion
     * @return
     */
    public String eliminarCliente(String identificacion) {
        Cliente clienteEncontrado = buscarCliente(identificacion);

        if (clienteEncontrado == null) {
            return "Error: El cliente no existe.";
        }
        listaClientes.remove(clienteEncontrado);
        return "Cliente con ID " + identificacion + " ha sido eliminado.";
    }

    /**
     *
     * @param nombre
     * @param identificacion
     * @param telefono
     * @return
     */

    public String registrarMecanico(String nombre, String identificacion, String telefono) {
        if (buscarMecanico(identificacion) != null) {
            return "Error: Ya existe un mecánico con esa identificación.";
        }
        Mecanico nuevoMecanico = new Mecanico(nombre, identificacion, telefono);
        listaMecanicos.add(nuevoMecanico);
        return "Mecánico " + nombre + " registrado con éxito.";
    }

    /**
     *
     * @param identificacion
     * @return
     */

    public Mecanico buscarMecanico(String identificacion) {
        for (Mecanico m : listaMecanicos) {
            if (m.getIdentificacion().equals(identificacion)) {
                return m;
            }
        }
        return null;
    }

    /**
     *
     * @param identificacion
     * @return
     */
    public String eliminarMecanico(String identificacion) {
        Mecanico m = buscarMecanico(identificacion);
        if (m == null) return "Error: El mecánico no existe.";

        listaMecanicos.remove(m);
        return "Mecánico eliminado del taller.";
    }

    /**
     *
     * @param identificacion
     * @param nuevoNombre
     * @param nuevoTelefono
     * @return
     */

    public String actualizarMecanico(String identificacion, String nuevoNombre, String nuevoTelefono) {
        Mecanico mecanicoEncontrado = buscarMecanico(identificacion);

        if (mecanicoEncontrado == null) {
            return "Error: No se encontró un mecánico con la identificación " + identificacion;
        }
        mecanicoEncontrado.setNombre(nuevoNombre);
        mecanicoEncontrado.setTelefono(nuevoTelefono);

        return "Los datos del mecánico " + nuevoNombre + " han sido actualizados correctamente.";
    }
    // --- GESTIÓN DE ÓRDENES DE SERVICIO (CRUD) ---

    /**
     *
     * @param codigo
     * @return
     */
    public OrdenServicio buscarOrden(String codigo) {
        for (OrdenServicio os : listaOrdenesServicio) {
            if (os.getCodigo().equals(codigo)) return os;
        }
        return null;
    }

    /**
     *
     * @param codigo
     * @param problema
     * @param idBici
     * @param identificacionMecanico
     * @return
     */
    public String registrarOrden(String codigo, String problema, String idBici, String identificacionMecanico) {
        if (buscarOrden(codigo) != null) return "Error: El código de orden ya existe.";
        Bicicleta b = buscarBicicleta(idBici);
        Mecanico m = buscarMecanico(identificacionMecanico);

        if (b == null) return "Error: La bicicleta no está registrada.";
        if (m == null) return "Error: El mecánico no está registrado.";

        // Se crea con la fecha y hora actual
        OrdenServicio nueva = new OrdenServicio(codigo, problema, LocalDateTime.now(), b, m);
        listaOrdenesServicio.add(nueva);
        return "Orden " + codigo + " creada exitosamente.";
    }

    /**
     *
     * @param codigo
     * @param nuevoEstado
     * @return
     */
    public String actualizarEstadoOrden(String codigo, OrdenServicio.EstadoOrden nuevoEstado) {
        OrdenServicio os = buscarOrden(codigo);
        if (os == null) return "Error: Orden no encontrada.";

        os.setEstado(nuevoEstado);

        // Si se entrega, se pone automáticamente la fecha de salida
        if (nuevoEstado == OrdenServicio.EstadoOrden.ENTREGADA) {
            os.setFechaSalida(LocalDateTime.now());
        }

        return "La orden " + codigo + " ahora está en estado: " + nuevoEstado;
    }

    /**
     *
     * @param codigo
     * @return
     */
    public String eliminarOrden(String codigo) {
        OrdenServicio os = buscarOrden(codigo);
        if (os == null) return "Error: No se puede eliminar una orden inexistente.";

        listaOrdenesServicio.remove(os);
        return "Orden " + codigo + " eliminada del registro.";
    }
}