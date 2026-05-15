package co.edu.unquindio.poo.model;

public class Mensaje implements TipoNotificacion{
    private String telefono;

    public Mensaje(String telefono){
        this.telefono= telefono;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    @Override
    public String toString() {
        return "Mensaje{" +
                "telefono='" + telefono + '\'' +
                '}';
    }
    @Override
    public EstadoNoticia enviarNotificacion() {
        return EstadoNoticia.ENVIADA;
    }
}
